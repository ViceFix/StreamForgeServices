package com.streamforge.service.twitch;

import com.streamforge.data.dto.twitch.UserDataNativeDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

@Service
public class TwitchUserDataService {

    public UserDataNativeDto getUserDataByToken(String accessToken) {
        TwitchCallBuilder<Object, UserDataNativeDto> callBuilder = TwitchCallBuilder.instance(UserDataNativeDto.class);
        return callBuilder
                .setMethod(HttpMethod.GET)
                .setAccessToken(accessToken)
                .setPath(TwitchApi.Path.GET_USER_BY_ACCESS_TOKEN)
                .call();
    }

}
