package com.streamforge.controller.unsafe;

import com.streamforge.controller.common.ApiConstants;
import com.streamforge.data.entity.Session;
import com.streamforge.data.entity.WebUser;
import com.streamforge.service.common.AuthorizationService;
import com.streamforge.service.common.Constants;
import com.streamforge.service.common.WebUserService;
import com.streamforge.service.exception.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstants.UNSAFE_API)
public class AuthorizationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationController.class);

    @Autowired
    private WebUserService webUserService;

    @Autowired
    private AuthorizationService authorizationService;

    @RequestMapping(path = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> authorize(@RequestBody WebUser user) {
        try {
            WebUser authorized = webUserService.getUserByCredentials(user.getUsername(), user.getPassword());
            Session session = authorizationService.createSession(authorized);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Authorization", Constants.TOKEN_CUSTOM + Constants.WHITESPACE + session.getToken());
            return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
        } catch (AuthorizationException e) {
            LOGGER.info("Attempt to login form user = {}. Invalid credentials", user.getUsername());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}

