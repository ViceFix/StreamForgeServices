package com.streamforge.service.twitch;

public class TwitchApi {

    // Common values
    public static final String AUTHORIZATION = "Authorization";
    public static final String CLIENT_ID = "Client-ID";
    public static final String CLIENT_ID_VALUE = "55upy7njxjo97rx68f7iv8iy6ezc2s";

    // Utilities
    public static String wrapTwitchAccessToken(String token) {
        return "OAuth " + token;
    }

    // Requests' pafs
    public static class Path {
        public static final String GET_USER_BY_ACCESS_TOKEN = "https://api.twitch.tv/kraken/user";
    }
}
