package com.linyoulin;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by linyo_000 on 2017/1/12.
 */
public class TestPBE {
    @Test
    public void test() throws Exception {
        String inputStr = "PBE";
        System.out.println("原文:\t" + inputStr);

        String pwd = "password";
        System.out.println("密码:\t" + pwd);

        byte[] inputData = inputStr.getBytes();

        byte[] salt = PBE.initSalt();
        System.out.println("盐值:\t" + new String(salt));

        byte[] data = PBE.encrypt(inputData, pwd, salt);

        String enStr = EncryptionUtils.encryptBASE64(data);

        System.out.println("加密后:\t" + new String(data));
        System.out.println("加密后:\t" + enStr);

        byte[] outputData = PBE.decrypt(data, pwd, salt);
        String outputStr = new String(outputData);

        System.out.println("解密后:\t" + outputStr);

        assertEquals(inputStr, outputStr);
    }
}
