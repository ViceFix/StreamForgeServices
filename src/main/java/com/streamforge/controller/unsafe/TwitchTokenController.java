package com.streamforge.controller.unsafe;

import com.streamforge.controller.common.ApiConstants;
import com.streamforge.data.dto.TwitchTokenDto;
import com.streamforge.service.twitch.TwitchTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping(ApiConstants.UNSAFE_API)
public class TwitchTokenController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwitchTokenController.class);
    private static final String TOKEN_TYPE = "Custom ";

    private final TwitchTokenService twitchTokenService;

    @Autowired
    public TwitchTokenController(TwitchTokenService twitchTokenService) {
        this.twitchTokenService = twitchTokenService;
    }

    @PostMapping("externaltoken")
    public ResponseEntity<?> saveTwitchToken(@RequestBody TwitchTokenDto tokenDto) {
        String token = TOKEN_TYPE + twitchTokenService.saveOrUpdateTwitchSession(tokenDto);

        HttpHeaders headers = new HttpHeaders();
        headers.put(ApiConstants.AUTHORIZATION, Collections.singletonList(token));
        return ResponseEntity.ok().headers(headers).build();
    }
}
