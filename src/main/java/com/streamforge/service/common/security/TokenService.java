package com.streamforge.service.common.security;

import com.streamforge.data.entity.WebUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TokenService {

    @Autowired
    private EncryptService encryptService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenService.class);

    public String generateCustomToken(WebUser user) {
        LOGGER.debug("Generating CUSTOM token fro user {}", user.getUserId());
        Random random = new Random();
        String tokenBase = user + Long.toHexString(random.nextLong());
        return encryptService.encrypt(tokenBase);
    }
}
