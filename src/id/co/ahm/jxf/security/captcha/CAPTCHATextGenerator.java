/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.security.captcha;

import java.util.Random;

/**
 *
 * @author achmad.ha
 */
public class CAPTCHATextGenerator {
    /* CAPTCHA possible characters */
    private final static char[] CAPTCHA_POSSIBLE_CHARS = new char[] { 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2',
            '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    /**
     * generateRandomText() generates the CAPTCHA text
     * @return the random generated text. 
     */
    public static String generateRandomText()
    {

        int totalPossibleCharacters = CAPTCHA_POSSIBLE_CHARS.length - 1;
        String captchaText = "";
        Random random = new Random();
        int captchaTextLength = 5;
        int randomNumber = random.nextInt(10);

        // Determine the CAPTCHA Length whether it is five or six.
        if (randomNumber >= 5)
        {
            captchaTextLength = 6;
        }

        // Generate the random String.
        for (int i = 0; i < captchaTextLength; ++i)
        {
            captchaText += CAPTCHA_POSSIBLE_CHARS[random
                    .nextInt(totalPossibleCharacters) + 1];
        }

        return captchaText;
    }
}
