package com.lotus.admin.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ShiroCrypt {

    //与配置信息中一致的加密方式
    public static String encrypt(String password) {
        String algorithmName = "md5";//加密算法
        int hashIterations = 2;//迭代次数
        String newPassword = new SimpleHash(algorithmName, password,
                null, hashIterations).toHex();
        return newPassword;
    }

    public static String sha1(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
            sha.update(str.getBytes("utf8"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        byte[] bytes = sha.digest();
        StringBuilder ret = new StringBuilder(bytes.length << 1);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(Character.forDigit((bytes[i] >> 4) & 0xf, 16));
            ret.append(Character.forDigit(bytes[i] & 0xf, 16));
        }
        return ret.toString();
    }

    /**
     * MD5加密
     *
     * @param str 明文字符串
     * @return 加密后字符串
     * @throws Exception
     */
    public static String md5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}