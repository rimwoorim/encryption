package com.linyoulin;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by linyo_000 on 2017/1/12.
 */
public class TestDES {
    @Test
    public void test() throws Exception {
        String inputStr = "DES";
        String key = DES.initKey();
        System.out.println("原文:\t" + inputStr);
        System.out.println("密钥:\t" + key);

        byte[] inputData = inputStr.getBytes();
        inputData = DES.encrypt(inputData, key);
        System.out.println("加密后:\t" + inputData);
        System.out.println("加密后:\t" + EncryptionUtils.encryptBASE64(inputData));

        byte[] outputData = DES.decrypt(inputData, key);
        String outputStr = new String(outputData);

        System.out.println("解密后:\t" + outputStr);

        assertEquals(inputStr, outputStr);
    }
}
