package com.streamforge.service.common.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class EncryptService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptService.class);

    public String encrypt(String toEncrypt) {
        String hash = "";
        try {
            MessageDigest hasher = MessageDigest.getInstance("SHA-256");
            byte[] digest = hasher.digest(toEncrypt.getBytes(StandardCharsets.UTF_8));
            hash = bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Unable to get SHA256 algorithm", e);
        }
        return hash;
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte aHash : hash) {
            String hex = Integer.toHexString(0xff & aHash);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
