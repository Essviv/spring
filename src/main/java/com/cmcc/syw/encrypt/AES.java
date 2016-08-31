package com.cmcc.syw.encrypt;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * AES practise code
 * <p/>
 * Created by sunyiwei on 2016/8/28.
 */
public class AES {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();

        final String ORIGIN = "Hello world";

        //encrypt
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        String encoded = new String(Base64.encodeBase64(cipher.doFinal(ORIGIN.getBytes())));
        System.out.println("Encoded: " + encoded);

        //decrypt
        String algo = cipher.getAlgorithm();
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decoded = cipher.doFinal(Base64.decodeBase64(encoded));
        System.out.println("Decoded: " + new String(decoded));
    }
}
