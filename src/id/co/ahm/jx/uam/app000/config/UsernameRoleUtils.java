package id.co.ahm.jx.uam.app000.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class UsernameRoleUtils {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        testFormatUsername();
    }
    private static final String STRING_SPACE = " ";
    private static final String STRING_POINT = ".";

    private static void testFormatUsername() {
        String fullname = "    Andi Budi Chandra Edi ";
        String result = null;
        String userType = "M";

//        System.out.println("fullname = " + fullname);

        result = getUsernameRole1(fullname, userType);
//        System.out.println("Role 1 result = " + result);

        result = getUsernameRole2(fullname, userType);
//        System.out.println("Role 2 result = " + result);

        int i = 0;
        for (i = 0; i == 0 || result != null; i++) {
            result = getUsernameRole3(fullname, userType, i);
//            System.out.println("Role 3 result = " + result);
        }

        i = 0;
        for (i = 0; i < 100; i++) {
            result = getUsernameRole4(fullname, userType, i);
//            System.out.println("Role 4 result = " + result);
        }
    }

    public static String getUsernameRole1(String fullname, String userType) {
        /*
         * Role 1 : [USER_TYPE].[FIRST_NAME]
         */

        if (fullname == null) {
            return null;
        }

        // remove all space more than one
        fullname = fullname.replaceAll("\\s+", STRING_SPACE).trim();

        String[] arrWord = fullname.split(STRING_SPACE);
        String result = null;

        if (arrWord.length >= 1) {
//			result = arrWord[0];
            result = getUserTypeFirstNameCombination(arrWord[0], userType);

//			System.out.println("result = " + result);
            // get only 12 chars
            result = String.format("%1$.12s", result);

//			System.out.println("result = " + result);
            // all lower case
            result = result.toLowerCase();
        }

        return result;
    }

    public static String getUsernameRole2(String fullname, String userType) {
        /*
         * Role 2 : [USER_TYPE].[FIRST_NAME].[HURUF_PERTAMA_KATA_KEDUA][HURUF_PERTAMA_KATA_TERAKHIR]
         */

        if (fullname == null) {
            return null;
        }

        // remove all space more than one
        fullname = fullname.replaceAll("\\s+", STRING_SPACE).trim();

        String[] arrWord = fullname.split(STRING_SPACE);
        String result = null;

        if (arrWord.length >= 3) {
            // first word trim max chars is 9
//			arrWord[0] = String.format("%1$.9s", arrWord[0]);
            arrWord[0] = String.format("%1$.9s", getUserTypeFirstNameCombination(arrWord[0], userType));

            result = arrWord[0] + STRING_POINT + arrWord[1].charAt(0)
                    + arrWord[arrWord.length - 1].charAt(0);

//			System.out.println("result = " + result);
            // get only 12 chars
            result = String.format("%1$.12s", result);

//			System.out.println("result = " + result);
            // all lower case
            result = result.toLowerCase();
        }

        return result;
    }

//	public static String getTwoCharCombination(List <String> listChar, int idx) {
//		int count = -1;
//		
//		for (int i = 0 ; i < listChar.size() ; i++) {
//			for (int j = i+1 ; j < listChar.size() ; j++) {
//				count++;				
//				if (count == idx) {
//					return listChar.get(i) + listChar.get(j);
//				}
//			}
//		}
//
//		return null;
//	}
    public static String getTwoCharCombination(String secondToLastName[], int idx) {
        int count = -1;
        if (idx < 0) {
            return null;
        }

        for (int i = 0; i < secondToLastName.length; i++) {
            List<String> listDistinctChar = distinctCharWithoutSpace(secondToLastName[i]);

            for (int j = 1; j < listDistinctChar.size(); j++) {
                count++;
                if (count == idx) {
                    return listDistinctChar.get(0).toLowerCase()
                            + listDistinctChar.get(j).toLowerCase();
                }
            }

            String secondWord = null;
            if (i + 1 >= secondToLastName.length) {
                secondWord = null;
                return null;
            } else {
                secondWord = secondToLastName[i + 1].replaceAll("\\s+", "");
                count++;
                if (count == idx) {
                    return listDistinctChar.get(0).toLowerCase()
                            + secondWord.substring(0, 1).toLowerCase();
                }
            }


        }

        return null;
    }

    public static String getUsernameRole3(String fullname, String userType, int index) {
        /*
         * Role 3 : [USER_TYPE].[FIRST_NAME].[HURUF_PERTAMA_KATA_KEDUA][HURUF_KEDUA_KATA_KEDUA..++..HURUF_TERAKHIR_KATA_TERAKHIR]
         */
        if (fullname == null) {
            return null;
        }

        // remove all space more than one
        fullname = fullname.replaceAll("\\s+", STRING_SPACE).trim();

        String[] arrWord = fullname.split(STRING_SPACE);
        String result = null;


        if (arrWord.length >= 2) {

            String[] arrWordWithoutFirstName = ArrayUtils.subarray(arrWord, 1, arrWord.length);
            String twoCharsName = getTwoCharCombination(arrWordWithoutFirstName, index);
            if (StringUtils.isBlank(twoCharsName)) {
                return null;
            } else {
//				arrWord[0] = String.format("%1$.9s", arrWord[0]);
                arrWord[0] = String.format("%1$.9s", getUserTypeFirstNameCombination(arrWord[0], userType));

                result = arrWord[0] + STRING_POINT + twoCharsName;
                result = String.format("%1$.12s", result);
                result = result.toLowerCase();
                return result;
            }

        }

        return result;
    }

    public static List<String> distinctCharWithoutSpace(String fullname) {
        List<String> list = new ArrayList<String>();

        String s = fullname.replaceAll("\\s+", "");
        for (int i = 0; i < s.length(); i++) {
            String onechar = s.substring(i, i + 1);
            if (!list.contains(onechar)) {
                list.add(onechar.toLowerCase());
            }
        }

        return list;
    }

    public static String getUsernameRole3a(String fullname, String userType, int index) {
        /*
         * Role 3 : [USER_TYPE].[FIRST_NAME].[HURUF_PERTAMA_KATA_KEDUA][HURUF_KEDUA_KATA_KEDUA..++..HURUF_TERAKHIR_KATA_TERAKHIR]
         */

        if (fullname == null) {
            return null;
        }

        // remove all space more than one
        fullname = fullname.replaceAll("\\s+", STRING_SPACE).trim();

        String[] arrWord = fullname.split(STRING_SPACE);
        String result = null;

        if (arrWord.length >= 2) {
            // create var fullname Exclude Firstname
            String fullnameExcFirstname = StringUtils.EMPTY;
            for (int i = 1; i < arrWord.length; i++) {
                fullnameExcFirstname += arrWord[i];
            }

            if (index + 1 >= fullnameExcFirstname.length()) {
                return null;
            }

            // first word trim max chars is 9
//			arrWord[0] = String.format("%1$.9s", arrWord[0]);
            arrWord[0] = String.format("%1$.9s", getUserTypeFirstNameCombination(arrWord[0], userType));

            result = arrWord[0] + STRING_POINT + arrWord[1].charAt(0)
                    + fullnameExcFirstname.charAt(index + 1);

//			System.out.println("result = " + result);
            // get only 12 chars
            result = String.format("%1$.12s", result);

//			System.out.println("result = " + result);
            // all lower case
            result = result.toLowerCase();
        }

        return result;
    }

    public static String getUsernameRole4(String fullname, String userType, int index) {
        /*
         * Role 4 : [USER_TYPE].[FIRST_NAME].[ANGKA++]
         * 			ANGKA start from 2
         */

        if (fullname == null) {
            return null;
        }

        // number range 2 - 99
        if (index > 97) {
            return null;
        }

        // remove all space more than one
        fullname = fullname.replaceAll("\\s+", STRING_SPACE).trim();

        String[] arrWord = fullname.split(STRING_SPACE);
        String result = null;

        if (arrWord.length >= 1) {
            // first word trim max chars is 9
//			arrWord[0] = String.format("%1$.9s", arrWord[0]);
            arrWord[0] = String.format("%1$.9s", getUserTypeFirstNameCombination(arrWord[0], userType));

            result = arrWord[0] + STRING_POINT + (index + 2);

//			System.out.println("result = " + result);
            // get only 12 chars
            result = String.format("%1$.12s", result);

//			System.out.println("result = " + result);
            // all lower case
            result = result.toLowerCase();
        }

        return result;
    }
    
    public static String getUsernameRole5(String fullname, String userType) {
        /*
         * Role 5 : [USER_TYPE].[FIRST_NAME].[RANDOMALFANUMERIC][RANDOMALFANUMERIC]
         */
        
        if (fullname == null) {
            return null;
        }
        
        // remove all space more than one
        fullname = fullname.replaceAll("\\s+", STRING_SPACE).trim();

        String[] arrWord = fullname.split(STRING_SPACE);
        String result = null;
        
        if (arrWord.length >= 1) {
            // first word trim max chars is 9
            arrWord[0] = String.format("%1$.9s", getUserTypeFirstNameCombination(arrWord[0], userType));
            
            String alphabet = "0123456789abcdefghijklmnopqrstuvwxyz"; // Random char
            Random r = new Random();
            String randChar = "" + ((char) alphabet.charAt(r.nextInt(alphabet.length()))) + ((char) alphabet.charAt(r.nextInt(alphabet.length())));
            while(randChar.matches("\\d+")) { // If 2 char is numeric
                randChar = "" + ((char) alphabet.charAt(r.nextInt(alphabet.length()))) + ((char) alphabet.charAt(r.nextInt(alphabet.length())));
            }
            result = arrWord[0] + STRING_POINT + randChar;
            
            // get only 12 chars
            result = String.format("%1$.12s", result);
            // all lower case
            result = result.toLowerCase();
        }
        
        return result;
    }

    private static String getUserTypeFirstNameCombination(String firstName, String userType) {
        boolean isFirstNameEmpty = StringUtils.isEmpty(firstName);
        boolean isUserTypeEmpty = StringUtils.isEmpty(userType);

        if (!isFirstNameEmpty && !isUserTypeEmpty) {
            return userType.charAt(0) + STRING_POINT + firstName;
        } else if (!isFirstNameEmpty && isUserTypeEmpty) {
            return firstName;
        } else {
            return null;
        }
    }
}
