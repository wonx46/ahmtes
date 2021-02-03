package id.co.ahm.jx.uam.util;

import java.util.regex.Matcher;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;

public class RandomUtils {

    static Logger logger = Logger.getLogger(RandomUtils.class);
    private static final String RAND_START_STRING = "\\[";
    private static final String RAND_END_STRING = "\\]";
    private static final String REGEX_START_QUOTE = "\\Q";
    private static final String REGEX_END_QUOTE = "\\E";
    private static RandomUtils me;

    public static RandomUtils getInstance() {
        if (RandomUtils.me == null) {
            RandomUtils.me = new RandomUtils();
        }

        return RandomUtils.me;
    }

    private RandomUtils() {
    }

    public String random(String format) {
        if (format == null) {
            return null;
        }

        int randStartIndex = 0;
        int randEndIndex = 0;
        int index = 0;
        String resultDone = null;
        String resultUndone = null;
        String replaceRegex = null;
        String result = format;
        String replaceFormat = null;

        while (index >= 0) {
            // get start rand index
            randStartIndex = result.indexOf(RAND_START_STRING, index);
            index = randStartIndex;
//			System.out.println("startIndex = " + randStartIndex);

            if (index < 0) {
                break;
            }

            // get end rand index
            randEndIndex = result.indexOf(RAND_END_STRING, index);
            index = randEndIndex;
//			System.out.println("endIndex = " + randEndIndex);

            if (index < 0) {
                break;
            }

            // get replace format
            replaceFormat = result.substring(
                    randStartIndex + RAND_START_STRING.length(), randEndIndex);
//			System.out.println();
//			System.out.println(randStartIndex + "," + randEndIndex);
//			System.out.println("result = " + result);
//			System.out.println("replace format = " + replaceFormat);

            index = result.indexOf(RAND_START_STRING + replaceFormat + RAND_END_STRING);
            resultDone = result.substring(0, index);
//			System.out.println("resultDone = " + resultDone);
            resultUndone = result.substring(index, result.length());
//			System.out.println("resultUndone = " + resultUndone);
            replaceRegex = REGEX_START_QUOTE + RAND_START_STRING
                    + replaceFormat + RAND_END_STRING + REGEX_END_QUOTE;
//			System.out.println("replaced regex = " + replaceRegex);
//			System.out.println("replace with = "
//					+ RandomStringUtils.random(1, replaceFormat));
            resultUndone = resultUndone.replaceFirst(replaceRegex, Matcher
                    .quoteReplacement(RandomStringUtils
                    .random(1, replaceFormat)));
//			resultUndone = resultUndone.replaceFirst(replaceRegex, "/");
            result = resultDone + resultUndone;
            index++;
        }

        // System.out.println(String.result("","");
//		System.out.println();
//		System.out.println("result = " + result);

        return result;
    }
}