/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.security;

import java.security.Key;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author achmad.ha
 */
public class JwtKey {

    private static final String passPhrase = "6jWaODzbSzCLgdRKhfuOD4VJyhSLvL43";

    public static Key generateDefaultKey() throws Exception {
        return generateKey(passPhrase);
    }

    public static Key generateKey(String passphrase) throws Exception {
        SecretKeySpec key = new SecretKeySpec(passphrase.getBytes(), "AES");
        return key;
    }

}
