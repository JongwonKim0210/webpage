package com.webpage.global.utils;

import jakarta.xml.bind.DatatypeConverter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public enum HashUtils {

    SHA_256("SHA-256"),
    SHA_512("SHA-512"),
    SHA_3_256("SHA3-256"),
    SHA_3_512("SHA3-512");

    private String algorithm;
    HashUtils(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getHashValue(String s) {
        if (null == s || "" == s) {
            return "";
        }

        try {
            MessageDigest digest = MessageDigest.getInstance(this.algorithm);
            digest.update(s.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
