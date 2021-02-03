package id.co.ahm.jxf.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;

public class AhmStringUtil {

    public static final String CHAR_ALPHABET_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String CHAR_ALPHABET_LOWER = "abcdefghijklmnopqrstuvwxyz";
    public static final String CHAR_NUMERIC = "0123456789";
    public static final String CHAR_SYMBOL = "*$-+?_&=!%{}/";
    public static final int PASSWORD_MIN_LENGTH = 6;
    private static String digitsHexa = "0123456789abcdef";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\."
            + "[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*"
            + "(\\.[A-Za-z]{2,})$";
    static Logger logger = Logger.getLogger(AhmStringUtil.class);
    private static AhmStringUtil me;

    public static AhmStringUtil getInstance() {
        if (AhmStringUtil.me == null) {
            AhmStringUtil.me = new AhmStringUtil();
        }

        return AhmStringUtil.me;
    }

    private AhmStringUtil() {
    }

    public String removeAllWhitespace(String source) {
        if (source == null) {
            return null;
        }

        return source.replaceAll("\\s+",
                org.apache.commons.lang3.StringUtils.EMPTY).trim();
    }

    public boolean isValid(String source, String regex) {
        if (source == null || regex == null) {
            throw new NullPointerException("source or regex is null");
        }

        return Pattern.compile(regex).matcher(source).matches();
    }

    public String convertEntityToString(Object entity, Object secondEntity)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (entity == null) {
            return null;
        }

        Class clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();

        boolean isFirst = true;

        //should we use class names ? what if hibernate proxy object ??
        StringBuilder result = new StringBuilder("[");

        if (fields != null) {
            for (int i = 0; i < fields.length; i++) {
                String fieldName = fields[i].getName();
                Annotation[] annotations = fields[i].getDeclaredAnnotations();

                int annotationType = annotationType(annotations);
                Object value = null;
                if (annotationIsColumn(annotationType)) {
                    value = invokeGetter(fieldName, clazz, entity, secondEntity);
                } else if (annotationIsEmbeddedId(annotationType)) {
                    Object entityEmbeddedId = invokeGetter(fieldName, clazz, entity, secondEntity);
                    value = convertEntityToString(entityEmbeddedId, entityEmbeddedId);
                } else if (annotationIsJoinColumnManyToOne(annotationType)) {
                    JoinColumn joinColumn = fields[i].getAnnotation(JoinColumn.class);
                    String referencedColumnName = joinColumn.referencedColumnName();

                    Object joinColumnFieldValue = invokeGetter(fieldName, clazz, entity, secondEntity);

                    value = getValueFromIdOrReferencedColumnNames(joinColumnFieldValue, referencedColumnName);
                } else if (annotationIsJoinColumnsManyToOne(annotationType)) {
                    value = retrieveValueStringFromJoinColumnsEntity(fields[i], clazz, entity, secondEntity);
                } else if (annotationIsJoinColumnOneToMany(annotationType)) {
                    Collection collectionFieldValue = (Collection) invokeGetter(fieldName, clazz, entity, secondEntity);
                    value = convertCollectionToString(collectionFieldValue);
                }

                if (annotationIsColumn(annotationType)
                        || annotationIsEmbeddedId(annotationType)
                        || annotationIsJoinColumnManyToOne(annotationType)
                        || annotationIsJoinColumnsManyToOne(annotationType)
                        || annotationIsJoinColumnOneToMany(annotationType)) {
                    if (!isFirst) {
                        result.append(",");
                    }

                    value = convertObjectToString(value);

                    result.append(fieldName + "=" + (value == null ? "" : value));
                    isFirst = false;
                }
            }
        }

        result.append("]");

        return result.toString();
    }

    private String convertObjectToString(Object obj) {
        Object value = obj;
        // date need to used number of milliseconds, because
        // java.util.Date and java.sql.Date have different string
        // format when get string value using toString
        if (value instanceof java.util.Date) {
            value = ((java.util.Date) value).getTime();
        } else if (value instanceof byte[]) {
            org.apache.commons.codec.binary.Base64.encodeBase64((byte[]) value);
        }
        if (value != null) {
            return value.toString();
        } else {
            return null;
        }
    }

    private String retrieveValueStringFromJoinColumnsEntity(
            Field field, Class clazz, Object entity, Object secondEntity)
            throws SecurityException, IllegalArgumentException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException {
        String fieldName = field.getName();
        Object joinColumnFieldValueEntity = invokeGetter(fieldName, clazz, entity, secondEntity);

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        JoinColumns joinColumns = field.getAnnotation(JoinColumns.class);
        for (JoinColumn jc : joinColumns.value()) {
            String referencedColumnName = jc.referencedColumnName();

            Class clazz2 = joinColumnFieldValueEntity.getClass();
            Field[] fields2 = clazz2.getDeclaredFields();

            try {
                Object value = retrieveReferencedColumnNameValueFromEntity(
                        referencedColumnName, clazz2, fields2, joinColumnFieldValueEntity);
                sb.append(value).append(",");
            } catch (Exception e) {
//				logger.info("Not found referenced column name [" + referencedColumnName + "] " +
//						"in class " + clazz2.getSimpleName() + ", trying embeddedId ");
                Object value = retrieveReferencedColumnNameValueFromEntityEmbeddedId(
                        referencedColumnName, clazz2, fields2, joinColumnFieldValueEntity);
                sb.append(value).append(",");
            }
        }

        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

    private Object retrieveReferencedColumnNameValueFromEntityEmbeddedId(
            String referencedColumnName, Class clazz2, Field[] fields2, Object joinColumnFieldValue)
            throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        for (int j = 0; j < fields2.length; j++) {
            Annotation[] annotations2 = fields2[j]
                    .getDeclaredAnnotations();
            for (int k = 0; k < annotations2.length; k++) {
                if (annotations2[k].annotationType()
                        .equals(EmbeddedId.class)) {

                    Object embeddedIdValue
                            = invokeGetter(fields2[j].getName(), clazz2, joinColumnFieldValue, joinColumnFieldValue);

                    return retrieveReferencedColumnNameValueFromEntity(
                            referencedColumnName, embeddedIdValue.getClass(),
                            embeddedIdValue.getClass().getDeclaredFields(), embeddedIdValue);

                }
            }
        }

        throw new RuntimeException("No annotations with column [" + referencedColumnName + " found for class [" + clazz2 + "]");
    }

    public String convertDateToString(Date date, SimpleDateFormat sdf) {
        if (date == null) {
            return "";
        } else {
            return sdf.format(date);
        }
    }

    private Object convertCollectionToString(Collection collectionFieldValue)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (collectionFieldValue == null) {
            return "[]";
        } else {
            boolean isFirst = true;
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (Object item : collectionFieldValue) {
                if (!isFirst) {
                    sb.append(",");
                }
                Object val = convertEntityToString(item, item);
                sb.append(val);
                isFirst = false;
            }

            sb.append("]");
            return sb.toString();
        }
    }

    private Object getValueFromIdOrReferencedColumnNames(Object joinColumnFieldValue, String referencedColumnName)
            throws SecurityException, IllegalArgumentException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException {
        Object value = null;
        // get id value of join column class
        if (joinColumnFieldValue != null) {
            Class clazz2 = joinColumnFieldValue.getClass();
            Field[] fields2 = clazz2.getDeclaredFields();

            if (fields2 != null) {
                if (org.apache.commons.lang3.StringUtils
                        .isEmpty(referencedColumnName)) {
                    value = retrieveIdValueFromEntity(clazz2, fields2, joinColumnFieldValue);
                } else {
                    value = retrieveReferencedColumnNameValueFromEntity(
                            referencedColumnName, clazz2, fields2, joinColumnFieldValue);
                }
            }
        }

        return value;
    }

    private Object retrieveReferencedColumnNameValueFromEntity(String referencedColumnName,
            Class clazz2, Field[] fields2, Object joinColumnFieldValue)
            throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        for (int j = 0; j < fields2.length; j++) {
            Annotation[] annotations2 = fields2[j].getDeclaredAnnotations();
            for (int k = 0; k < annotations2.length; k++) {
                if (annotations2[k].annotationType().equals(Column.class)) {
                    Column column = fields2[j].getAnnotation(Column.class);
                    if (column.name().equals(referencedColumnName)) {
                        return invokeGetter(fields2[j].getName(), clazz2, joinColumnFieldValue, joinColumnFieldValue);
                    }
                }
            }
        }

        throw new RuntimeException("No annotations with column [" + referencedColumnName + " found for class [" + clazz2 + "]");
    }

    private Object retrieveIdValueFromEntity(Class clazz2, Field[] fields2, Object joinColumnFieldValue)
            throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        for (int j = 0; j < fields2.length; j++) {
            Annotation[] annotations2 = fields2[j].getDeclaredAnnotations();
            for (int k = 0; k < annotations2.length; k++) {
                if (annotations2[k].annotationType().equals(Id.class)) {
                    return invokeGetter(fields2[j].getName(), clazz2, joinColumnFieldValue, joinColumnFieldValue);
                }
            }
        }

        throw new RuntimeException("No ID annotations found for class [" + clazz2 + "]");
    }
    private static final int TYPE_COLUMN = 1;
    private static final int TYPE_JOINCOLUMN_MANYTOONE = 2;
    private static final int TYPE_JOINCOLUMN_ONETOMANY = 3;
    private static final int TYPE_JOINCOLUMNS_MANYTOONE = 4;
    private static final int TYPE_EMBEDDED_ID = 5;

    private int annotationType(Annotation annotations[]) {
        if (annotations == null) {
            return -1;
        }
        boolean hasColumnAnnotation = false;
        boolean hasJoinColumnAnnotation = false;
        boolean hasJoinColumnsAnnotation = false;
        boolean hasManyToOneAnnotation = false;
        boolean hasOneToManyAnnotation = false;
        boolean hasEmbeddedId = false;

        for (int j = 0; j < annotations.length; j++) {
//			logger.debug("annotation = "
//					+ annotations[j].annotationType().getName());
            if (annotations[j].annotationType().equals(EmbeddedId.class)) {
                hasEmbeddedId = true;
            } else if (annotations[j].annotationType().equals(Column.class)) {
                hasColumnAnnotation = true;
            } else if (annotations[j].annotationType().equals(JoinColumn.class)) {
                hasJoinColumnAnnotation = true;
            } else if (annotations[j].annotationType().equals(JoinColumns.class)) {
                hasJoinColumnsAnnotation = true;
            } else if (annotations[j].annotationType().equals(ManyToOne.class)) {
                hasManyToOneAnnotation = true;
            } else if (annotations[j].annotationType().equals(OneToMany.class)) {
                hasOneToManyAnnotation = true;
            }
        }

        if (hasEmbeddedId) {
            return TYPE_EMBEDDED_ID;
        } else if (hasJoinColumnsAnnotation && hasManyToOneAnnotation) {
            return TYPE_JOINCOLUMNS_MANYTOONE;
        } else if (hasJoinColumnAnnotation && hasManyToOneAnnotation) {
            return TYPE_JOINCOLUMN_MANYTOONE;
        } else if (hasOneToManyAnnotation) {
            return TYPE_JOINCOLUMN_ONETOMANY;
        } else if (hasColumnAnnotation) {
            return TYPE_COLUMN;
        }

        return -1;
    }

    private boolean annotationIsColumn(int type) {
        return type == TYPE_COLUMN;
    }

    private boolean annotationIsEmbeddedId(int type) {
        return type == TYPE_EMBEDDED_ID;
    }

    private boolean annotationIsJoinColumnManyToOne(int type) {
        return type == TYPE_JOINCOLUMN_MANYTOONE;
    }

    private boolean annotationIsJoinColumnsManyToOne(int type) {
        return type == TYPE_JOINCOLUMNS_MANYTOONE;
    }

    private boolean annotationIsJoinColumnOneToMany(int type) {
        return type == TYPE_JOINCOLUMN_ONETOMANY;
    }

    private Object invokeGetter(String fieldName, Class clazz, Object entity, Object secondEntity)
            throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        String methodName = getEntityGetterMethodName(fieldName);
        Method method = clazz.getDeclaredMethod(methodName);
        Object value = null;
        try {
            value = method.invoke(entity);
            if (value instanceof Collection) {
                Collection c = (Collection) value;
                c.size();
            }
        } catch (Exception ex) {
            value = method.invoke(secondEntity);
        }
        return value;
    }

    public String getEntityGetterMethodName(String fieldName) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(fieldName)) {
            return null;
        }

        String firstChar = getEntityMethodNameFirstChar(fieldName);

        return "get" + firstChar + fieldName.substring(1);
    }

    public String getEntitySetterMethodName(String fieldName) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(fieldName)) {
            return null;
        }

        String firstChar = getEntityMethodNameFirstChar(fieldName);

        return "set" + firstChar + fieldName.substring(1);
    }

    private String getEntityMethodNameFirstChar(String fieldName) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(fieldName)) {
            return null;
        }

        if (fieldName.length() == 1 || org.apache.commons.lang3.StringUtils
                .isAllUpperCase(String.valueOf(fieldName.charAt(1)))) {
            return fieldName.substring(0, 1);
        } else {
            return fieldName.substring(0, 1).toUpperCase();
        }
    }

    public Pattern buildEmailPattern() {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        return pattern;
    }

    public boolean validEmail(String emailStr) {
        Pattern pattern = buildEmailPattern();
        Matcher matcher = pattern.matcher(emailStr);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean urlContainPort(String url) {
        if (url.matches(".*:[0-9].*")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean urlIsHttp(String url) {
        if (org.apache.commons.lang3.StringUtils.startsWithIgnoreCase(url, "http://")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean urlIsHttps(String url) {
        if (org.apache.commons.lang3.StringUtils.startsWithIgnoreCase(url, "https://")) {
            return true;
        } else {
            return false;
        }
    }

    public String addDefaultPortToUrlIfNotExists(String url) {
        if (urlContainPort(url)) {
            return url;
        } else {
            final String PROTOCOL_SLASH = "://";
            int idxOfDoubleSlashProtocol = url.indexOf(PROTOCOL_SLASH);
            int idxOfFirstSlashAfterProtocol = url.indexOf("/", idxOfDoubleSlashProtocol + PROTOCOL_SLASH.length());

            if (idxOfFirstSlashAfterProtocol > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(url.substring(0, idxOfFirstSlashAfterProtocol));
                if (urlIsHttp(url)) {
                    sb.append(":80");
                } else if (urlIsHttps(url)) {
                    sb.append(":443");
                }
                sb.append(url.substring(idxOfFirstSlashAfterProtocol, url.length()));
                return sb.toString();

            } else {
                if (urlIsHttp(url)) {
                    return url + ":80";
                } else if (urlIsHttps(url)) {
                    return url + ":443";
                } else {
                    return url;
                }
            }
        }
    }

    public static String[] splitBySpace(String value) {
        return value.split("\\s+");
    }

    public static String encodeCharLtAndGt(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        String res = sb.toString();

        while (true) {
            int idx = org.apache.commons.lang3.StringUtils.indexOf(res, "<#");
            if (idx < 0) {
                break;
            }
            int idxOfEndTagGt = org.apache.commons.lang3.StringUtils.indexOf(res, ">", idx);

            res = org.apache.commons.lang3.StringUtils.overlay(res, "&gt;", idxOfEndTagGt, idxOfEndTagGt + 1);
            res = org.apache.commons.lang3.StringUtils.overlay(res, "&lt;#", idx, idx + 2);
        }

        while (true) {
            int idx = org.apache.commons.lang3.StringUtils.indexOf(res, "</#");
            if (idx < 0) {
                break;
            }
            int idxOfEndTagGt = org.apache.commons.lang3.StringUtils.indexOf(res, ">", idx);

            res = org.apache.commons.lang3.StringUtils.overlay(res, "&gt;", idxOfEndTagGt, idxOfEndTagGt + 1);
            res = org.apache.commons.lang3.StringUtils.overlay(res, "&lt;/#", idx, idx + 3);
        }

        return res;
    }

    public void addGeneratedUrl(Set<String> allowedPages, String server, String appUrl) {
        allowedPages.add(server + appUrl);
        if (!urlContainPort(server)) {
            if (urlIsHttp(server)) {
                allowedPages.add(server + ":80" + appUrl);
            } else if (urlIsHttps(server)) {
                allowedPages.add(server + ":443" + appUrl);
            }
        }
    }

    public boolean isStrongPassword(String password) {
        if (password.length() < PASSWORD_MIN_LENGTH) {
            return false;
        }
        if (!org.apache.commons.lang3.StringUtils.containsAny(password, CHAR_ALPHABET_LOWER)) {
            return false;
        }
        if (!org.apache.commons.lang3.StringUtils.containsAny(password, CHAR_ALPHABET_UPPER)) {
            return false;
        }
        if (!org.apache.commons.lang3.StringUtils.containsAny(password, CHAR_NUMERIC)) {
            return false;
        }
        if (!org.apache.commons.lang3.StringUtils.containsAny(password, CHAR_SYMBOL)) {
            return false;
        }

        return true;
    }

    public static String toHex(byte[] data) {
        return toHex(data, data.length);
    }

    public static String toHex(byte[] data, int length) {
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i != length; i++) {
            int v = data[i] & 0xFF;

            buf.append(digitsHexa.charAt(v >> 4));
            buf.append(digitsHexa.charAt(v & 0xF));
        }
        return buf.toString();
    }

    public static byte[] toByte(String hex) {
        StringBuffer buf = new StringBuffer();

        byte[] bts = new byte[hex.length() / 2];
        for (int i = 0; i < bts.length; i++) {
            bts[i] = ((byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16));
        }
        return bts;
    }

    public static boolean hasValue(Object o) {
        if (o == null) {
            return false;
        } else if (o.toString().trim().equals("")) {
            return false;
        } else if (o.toString().isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean isNumeric(String str) {
        try {
            new Double(nullFloat(str.trim()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String nullFloat(String str) {
        return (!hasValue(str)) ? "0" : str;
    }

    public static String generateID(String value, Integer id) {
        if (AhmStringUtil.hasValue(id)) {
            return AhmStringUtil.hasValue(value)
                    ? value.concat(StringUtils.leftPad(id.toString(), 8, '0'))
                    : StringUtils.leftPad(id.toString(), 15, '0');
        }
        return AhmStringUtil.hasValue(value)
                ? value.concat(StringUtils.leftPad("1", 8, '0'))
                : value.concat(StringUtils.leftPad("1", 15, '0'));
    }

    public static String generateFPTKID(String value, Integer id) {
        String month = getRoman(new LocalDate().getMonthOfYear());
        String result = value.replaceAll("" + new LocalDate().getYear(), "");
        value = result + month + "/" + new LocalDate().getYear();
        return StringUtils.leftPad(id.toString(), 3, '0').concat(value);
    }

    public static String concatArrayString(String... params) {
        String result = "";
        for (String param : params) {
            result += param;
        }
        return result;
    }

    public static String getRoman(int number) {
        String riman[] = {"M", "XM", "CM", "D", "XD", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int arab[] = {1000, 990, 900, 500, 490, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (number > 0 || arab.length == (i - 1)) {
            while ((number - arab[i]) >= 0) {
                number -= arab[i];
                result.append(riman[i]);
            }
            i++;
        }
        return result.toString();
    }

    public static String ConvertStrToJSon(String toString) {
        String convert = toString.replaceAll("\\{", "{\"");
//        System.out.println("convert1 : " + convert);
        convert = convert.replaceAll("\\}", "\"}");
//        System.out.println("convert2 : " + convert);
        convert = convert.replaceAll(",\\s\\{", ",{");
//        System.out.println("convert3 : " + convert);
        convert = convert.replaceAll(",\\s", "\",\"");
//        System.out.println("convert4 : " + convert);
        convert = convert.replaceAll("\\=", "\":\"");
//        System.out.println("convert5 : " + convert);
        convert = convert.replaceAll("\"null\"", "null");
//        System.out.println("convert6 : " + convert);

        return convert;
    }

    public static String concatWithDate(String name) {
        return concatWithDate(name, "");
    }

    public static String concatWithDate(String name, String extention) {
        if (null == name) {
            name = "";
        }

        name = name.replace(" ", "_");
        if (null == extention || "".equalsIgnoreCase(extention.trim())) {
            return (name + "_" + new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss", Locale.US).format(new Date()));
        } else {
            if (!extention.startsWith(".")) {
                extention = "." + extention;
            }
            return (name + "_" + new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss", Locale.US).format(new Date()) + extention).trim();
        }
    }
    
    public static String convertListToString(List<String> list,char separator) {
        if (list == null) {
            return "";
        } else {
            boolean isFirst = true;
            StringBuilder sb = new StringBuilder();
            for (String s : list) {
                if (isFirst) {
                    sb.append(s);
                }else{
                    sb.append(separator+s);
                }
                isFirst = false;
            }

            return sb.toString();
        }
    }

}
