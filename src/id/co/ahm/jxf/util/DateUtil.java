package id.co.ahm.jxf.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.Years;

/**
 *
 * @author bayutridewanto
 */
public final class DateUtil {

    public final static String TIMEZONE = "GMT+7:00";
    static final long ONE_DAY_IN_MILL_SEC = 86400000;

    /**
     * Format : "dd-MM-yyyy" or "HH : mm" or others
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToString(Date date, String format) {
        if (date == null) {
            return "-";
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * Format : "dd-MM-yyyy" or "HH : mm" or others
     *
     * @param dateString
     * @param format
     * @return
     */
    public static Date stringToDate(String dateString, String format) {
        if (StringUtils.isBlank(dateString)) {
            return null;
        }
        try {
            return (new SimpleDateFormat(format)).parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * DatewithoutTime : "21-Oct-2015 12:34:12" : "21-Oct-2015 00:00:00"
     *
     * @param date
     * @return
     */
    public static Date getDateWithoutTime(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    /**
     * EndOfDay : "21-Oct-2015 23:59:59"
     *
     * @param date
     * @return
     */
    public static Date getEndOfDay(Date date) {
        if (date == null) {
            return null;
        }

        Calendar cal = GregorianCalendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DATE, 1);
        cal.add(Calendar.MILLISECOND, -1);

        return cal.getTime();
    }

    /**
     * addDay (1) : "31-Oct-2015 12:34:12" : "01-Nov-2015 12:34:12" minusDay
     * (-1) : "01-Oct-2015 12:34:12" : "30-Sep-2015 12:34:12"
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    /**
     * @param date
     * @param months
     * @return
     */
    public static Date addMonths(Date date, int months) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months); //minus number would decrement the days
        return cal.getTime();
    }

    /**
     * @param date
     * @param years
     * @return
     */
    public static Date addYears(Date date, int years) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, years); //minus number would decrement the days
        return cal.getTime();
    }

    /**
     *
     * @param date : date of birth
     * @return
     */
    public static Integer getAge(Date date) {
        LocalDate birthdate = new LocalDate(date);
        return Years.yearsBetween(birthdate, new LocalDate()).getYears();
    }

    public static Date getDateByAge(Integer age) {
        Integer day = new LocalDate().getDayOfMonth();
        Integer month = new LocalDate().getMonthOfYear();
        Integer year = new LocalDate().getYear() - age;
        /* cabisat mode */
        if (year % 4 != 0) {
            /* in february */
            if (month == 2) {
                if (day > 27) {
                    day = new LocalDate().getDayOfMonth() - 1;
                }
            }
        }

        String dayOfMonth = Integer.toString(day);
        dayOfMonth = dayOfMonth.length() == 1 ? "0" + dayOfMonth : dayOfMonth;
        String monthOfYear = Integer.toString(month);
        monthOfYear = monthOfYear.length() == 1 ? "0" + monthOfYear : monthOfYear;
        return DateUtil.stringToDate(dayOfMonth.concat(monthOfYear).concat(Integer.toString(year)), "ddMMyyyy");
    }

    /**
     * different between two Date/Time
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getDays(Date startDate, Date endDate) {
        int diff = (int) ((DateUtil.getDateWithoutTime(endDate).getTime() - DateUtil.getDateWithoutTime(startDate).getTime()) / (1000 * 60 * 60 * 24));
        return diff;
    }

    public static Boolean isDateValid(Date startDate, Date endDate) {
        return startDate.before(new Date()) && endDate.after(new Date());
    }

    public static Date getDate(Date dates) {
        Calendar cal = GregorianCalendar.getInstance();
        if (dates == null) {
            return new Date();
        } else {
            cal.setTime(dates);
            return cal.getTime();
        }
    }

    public static Date toDate(final String date) {
        return toDate(date, "00:00.00.000");
    }

    public static Date toDate(final String date, final String time) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date + " " + time);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date add(final Date a, int b) {
        Calendar cal_a = GregorianCalendar.getInstance();
        cal_a.setTime(a);
        cal_a.add(Calendar.DAY_OF_MONTH, b);
        String s = dateToString(cal_a.getTime(), "yyyy-MM-dd");
        return toDate(s);
    }

    public static boolean isAfter(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        int day1 = cal1.get(Calendar.DAY_OF_MONTH);
        int month1 = cal1.get(Calendar.MONTH);
        int year1 = cal1.get(Calendar.YEAR);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day2 = cal2.get(Calendar.DAY_OF_MONTH);
        int month2 = cal2.get(Calendar.MONTH);
        int year2 = cal2.get(Calendar.YEAR);

        if (year1 < year2) { // check year
            return true;
        } else if (year1 == year2) { // if year is equal
            if (month1 < month2) { // check month
                return true;
            } else if (month1 == month2) { // if month is equal
                if (day1 < day2) { // check day
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isEqual(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        int day1 = cal1.get(Calendar.DAY_OF_MONTH);
        int month1 = cal1.get(Calendar.MONTH);
        int year1 = cal1.get(Calendar.YEAR);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day2 = cal2.get(Calendar.DAY_OF_MONTH);
        int month2 = cal2.get(Calendar.MONTH);
        int year2 = cal2.get(Calendar.YEAR);

        return ((day1 == day2) && (month1 == month2) && (year1 == year2));
    }

    public static Date getDateMinusDay(Date d, int days) {
        return (new Date(d.getTime() - days * ONE_DAY_IN_MILL_SEC));
    }

    public static Date getNextDate(Date date, int days) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     *
     * @param initialTime "18:00:00"
     * @param finalTime "06:00:00"
     * @param currentTime
     * @return
     */
    public static boolean isTimeBetweenTwoTime(String initialTime, String finalTime, Date currentTime) {
        String reg = "^([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
        try {
            if (initialTime.matches(reg) && finalTime.matches(reg)) {
                boolean valid = false;
                //Start Time
                java.util.Date inTime = new SimpleDateFormat("HH:mm:ss").parse(initialTime);
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(inTime);

                //Current Time
                Calendar calendar3 = Calendar.getInstance();
                calendar3.setTime(currentTime);

                //End Time
                java.util.Date finTime = new SimpleDateFormat("HH:mm:ss").parse(finalTime);
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(finTime);

                if (finalTime.compareTo(initialTime) < 0) {
                    calendar2.add(Calendar.DATE, 1);
                    calendar3.add(Calendar.DATE, 1);
                }

                java.util.Date actualTime = calendar3.getTime();
                if ((actualTime.after(calendar1.getTime()) || actualTime.compareTo(calendar1.getTime()) == 0)
                        && actualTime.before(calendar2.getTime())) {
                    valid = true;
                }
                return valid;
            }
        } catch (Exception e) {

        }
        return Boolean.FALSE;
    }

    public static Period getDateDiff(Date arg0, Date arg1) {
        return new Period(arg0.getTime(), arg1.getTime());
    }

    public static Integer getDateDiffInDays(Date arg0, Date arg1) {
        return (int) (-(arg0.getTime() - arg1.getTime()) / (1000 * 60 * 60 * 24) + 1);
    }

    public static int getDateDiffInYears(Date arg0, Date arg1) {
        Calendar a = getCalendar(arg0);
        Calendar b = getCalendar(arg1);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH)
                || (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }

    public static String getDayOfMonth(Date aDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        int dayOfMonth = cal.get(Calendar.DAY_OF_WEEK);
        String dayOfMonthStr = null;
        switch (dayOfMonth) {
            case 1:
                dayOfMonthStr = "Minggu";
                break;
            case 2:
                dayOfMonthStr = "Senin";
                break;
            case 3:
                dayOfMonthStr = "Selasa";
                break;
            case 4:
                dayOfMonthStr = "Rabu";
                break;
            case 5:
                dayOfMonthStr = "Kamis";
                break;
            case 6:
                dayOfMonthStr = "Jumat";
                break;
            case 7:
                dayOfMonthStr = "Sabtu";
        }
        return dayOfMonthStr;
    }

    /**
     * Convert Microsoft un OLE Automation - OADate to Java Date.
     *
     * @param dateInDouble
     * @return
     */
    public static Date convertFromOADate(double dateInDouble) {
        double mantissa = dateInDouble - (long) dateInDouble;
        double hour = mantissa * 24;
        double min = (hour - (long) hour) * 60;
        double sec = (min - (long) min) * 60;

        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        Date baseDate;
        try {
            baseDate = myFormat.parse("30 12 1899");
            Calendar c = Calendar.getInstance();
            c.setTime(baseDate);
            c.add(Calendar.DATE, (int) dateInDouble);
            c.add(Calendar.HOUR, (int) hour);
            c.add(Calendar.MINUTE, (int) min);
            c.add(Calendar.SECOND, (int) sec);

            return c.getTime();
        } catch (ParseException ex) {
            Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Convert Date to Microsoft OLE Automation - OADate type
     *
     * @param date
     * @return
     */
    public static String convertToOADate(Date date) {
        double oaDate;
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        Date baseDate;
        try {
            baseDate = myFormat.parse("30 12 1899");
            Long days = TimeUnit.DAYS.convert(date.getTime() - baseDate.getTime(), TimeUnit.MILLISECONDS);

            oaDate = (double) days + ((double) date.getHours() / 24) + ((double) date.getMinutes() / (60 * 24)) + ((double) date.getSeconds() / (60 * 24 * 60));
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            Number number = format.parse(String.valueOf(oaDate));
            double d = number.doubleValue();
            return String.valueOf(d);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }    
}
