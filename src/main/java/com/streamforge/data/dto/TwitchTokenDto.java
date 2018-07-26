package com.streamforge.data.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class TwitchTokenDto {

    @JsonAlias("access_token")
    private String accessToken;

    @JsonAlias("expires_in")
    private int expiration;

    @JsonAlias("refresh_token")
    private String refreshToken;

    private List<String> scope;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiration() {
        return expiration;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public List<String> getScope() {
        return scope;
    }

    public void setScope(List<String> scope) {
        this.scope = scope;
    }
}
