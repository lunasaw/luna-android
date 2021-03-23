package com.luna.application.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import com.google.common.hash.Hashing;

/**
 * @author Luna
 */
public class HashUtils {

    /**
     * SHA512
     *
     * @param plain
     * @return
     */
    public static String SHA512(String plain) {
        return Hashing.sha512().hashString(plain, Charset.defaultCharset()).toString();
    }

    public static String randomHex32() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
