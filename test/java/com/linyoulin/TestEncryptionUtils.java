package com.linyoulin;

import com.linyoulin.EncryptionUtils;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by linyo_000 on 2017/1/9.
 */
public class TestEncryptionUtils {

    @Test
    public void testBASE64() throws Exception {
        String a = "简单加密";
        String b = EncryptionUtils.encryptBASE64(a.getBytes());
        System.out.println(b);

        byte[] c = EncryptionUtils.decryptBASE64(b);
        printlnBytes(c);
        String d = new String(c);
        System.out.println(d);

        assertEquals(a, d);
    }

    private void printlnBytes(byte[] bytes) {
        for (int i = 0; i < bytes.length; i ++) {
            System.out.printf("0x%02X ", bytes[i]);
        }
        System.out.println();
    }

    @Test
    public void testMD5() throws Exception {
        String a = "简单加密";
        byte[] bytes = EncryptionUtils.encryptMD5(a.getBytes());
        printlnBytes(bytes);
        System.out.println(new String(bytes,"gb2312"));

        BigInteger md5 = new BigInteger(bytes);
        System.out.println(md5.toString(16));
        System.out.println(md5.toString(2));
        System.out.println(md5.toString(4));
        System.out.println(md5.toString(8));

    }

    @Test
    public void testSHA() throws Exception {
        String a = "简单加密";
        byte[] bytes = EncryptionUtils.encryptSHA(a.getBytes());
        printlnBytes(bytes);
        System.out.println(new String(bytes,"utf-8"));

        BigInteger sha = new BigInteger(bytes);
        System.out.println(sha.toString(16));
        System.out.println(sha.toString(2));
        System.out.println(sha.toString(4));
        System.out.println(sha.toString(8));
        System.out.println(sha.toString(32));
    }

    @Test
    public void testHMAC() throws Exception {
        String a = "简单加密";
        String key = EncryptionUtils.initMacKey();
        System.out.println("密钥:" + key + "\n\n");

        byte[] bytes = EncryptionUtils.encryptHMAC(a.getBytes(), key);
        BigInteger mac = new BigInteger(bytes);
        System.out.println(mac.toString(16));

    }
}
