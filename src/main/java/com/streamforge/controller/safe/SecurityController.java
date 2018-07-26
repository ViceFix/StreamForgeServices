package com.streamforge.controller.safe;

import com.streamforge.controller.common.ApiConstants;
import com.streamforge.data.entity.Session;
import com.streamforge.service.common.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.SECURITY_PATH)
public class SecurityController {

    @Autowired
    private AuthorizationService authorizationService;

    @RequestMapping(value = "/token/refresh", method = RequestMethod.GET)
    public ResponseEntity<Session> refreshToken(@RequestHeader(name = "Authorization") String token) {
        String transformedToken = authorizationService.transformToken(token);
        Session session = authorizationService.refreshSession(transformedToken);
        return ResponseEntity.ok(session);
    }

}
