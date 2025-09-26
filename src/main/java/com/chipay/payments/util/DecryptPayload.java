package com.chipay.payments.util;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

public class DecryptPayload {

    public static String decrypt(String encryptedPayload, String encryptionKey) {
         //encryptionKey = "TBD";
        Cipher cipher = null;
        String decryptedTextJSON;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKey secretKey = new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8), "AES");
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
            byte[] decryptedBytes = cipher.doFinal(Base64.getUrlDecoder().decode(encryptedPayload.getBytes(StandardCharsets.UTF_8)));
            decryptedTextJSON = new String(decryptedBytes, StandardCharsets.UTF_8);
            System.out.println(decryptedTextJSON);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
        return decryptedTextJSON;
    }
}
