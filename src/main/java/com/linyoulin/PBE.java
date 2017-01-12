package com.linyoulin;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.util.Random;

/**
 * Created by linyo_000 on 2017/1/12.
 */
public class PBE {
    /**
     * 支持以下任意一种算法
     *
     * <pre>
     * PBEWithMD5AndDES
     * PBEWithMD5AndTripleDES
     * PBEWithSHA1AndDESede
     * PBEWithSHA1AndRC2_40
     * </pre>
     */
    public static final String ALGORITHM = "PBEWITHMD5andDES";

    /**
     * 盐初始化
     * @return
     * @throws Exception
     */
    public static byte[] initSalt() throws Exception {
        byte[] salt = new byte[8];
        Random random = new Random();
        random.nextBytes(salt);
        return salt;
    }

    /**
     * 转换密钥
     * @param password
     * @return
     * @throws Exception
     */
    private static Key toKey(String password) throws Exception {
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(keySpec);
        return secretKey;
    }

    /**
     * 加密
     * @param data
     * @param password
     * @param salt
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, String password, byte[] salt) throws Exception {
        Key key = toKey(password);
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, 100);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);

        return cipher.doFinal(data);
    }

    /**
     * 解密
     * @param data
     * @param password
     * @param salt
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, String password, byte[] salt) throws Exception {
        Key key = toKey(password);
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, 100);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);

        return cipher.doFinal(data);
    }
}
