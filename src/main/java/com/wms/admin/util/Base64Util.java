package com.wms.admin.util;

import org.apache.tomcat.util.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

public class Base64Util {
    private static Base64 base64 = new Base64();

    public static String encode(String plain) {
        return new String(base64.encode(plain.getBytes(StandardCharsets.UTF_8)));

    }

    public static String decode(String encrypt) {
        return new String(base64.decode(encrypt.getBytes(StandardCharsets.UTF_8)));
    }
}
