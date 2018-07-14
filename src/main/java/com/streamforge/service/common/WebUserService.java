package com.streamforge.service.common;

import com.streamforge.data.entity.WebUser;
import com.streamforge.data.repository.WebUserRepository;
import com.streamforge.service.common.security.EncryptService;
import com.streamforge.service.exception.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class WebUserService {

    @Autowired
    private WebUserRepository webUserRepository;

    @Autowired
    private EncryptService encryptService;

    public WebUser getUserByCredentials(String username, String password) {
        String encryptedPassword = encryptService.encrypt(password);
        return Optional
                .ofNullable(webUserRepository.findByCredentials(username, encryptedPassword))
                .orElseThrow(() -> new AuthorizationException("Credentials are invalid"));
    }
}
