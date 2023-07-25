package com.jjh.cleverai.utils;


import com.jjh.cleverai.common.ORuntimeException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AESDecryptUtils {

    private static final String KEY = "55c72a45f613af9e";


    private static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";

    public static String encrypt(String plaintext){
        SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");
        byte[] encryptedBytes;
        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new ORuntimeException(e.getMessage());
        }
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String ciphertext) {
        SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");
        byte[] decryptedBytes;
        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        } catch (Exception e) {
            throw new ORuntimeException(e.getMessage());
        }

        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
    public static void main(String[] args) {
        String plaintext = "1";

        String ciphertext = encrypt(plaintext);
        System.out.println("加密后的字符串：" + ciphertext);

        String decryptedText = decrypt(ciphertext);
        System.out.println("解密后的字符串：" + decryptedText);
    }
}
