package com.streamforge.service.twitch;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class TwitchCallBuilder<B, R> {

    private Class<R> responseClass;
    private HttpHeaders httpHeaders;
    private HttpMethod method;
    private String accessToken;
    private String path;
    private B body;

    private TwitchCallBuilder(Class<R> responseClass) {
        this.responseClass = responseClass;
        httpHeaders = new HttpHeaders();
    }


    public TwitchCallBuilder<B, R> setMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    public TwitchCallBuilder<B, R> setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public TwitchCallBuilder<B, R> setBody(B body) {
        this.body = body;
        return this;
    }

    public TwitchCallBuilder<B, R> addHeader(String name, String value) {
        httpHeaders.add(name, value);
        return this;
    }

    public TwitchCallBuilder<B, R> setPath(String path) {
        this.path = path;
        return this;
    }

    public R call() {
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set(TwitchApi.AUTHORIZATION, TwitchApi.wrapTwitchAccessToken(accessToken));
        httpHeaders.set(TwitchApi.CLIENT_ID, TwitchApi.CLIENT_ID_VALUE);
        HttpEntity<B> entity = new HttpEntity<>(body, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<R> response =
                restTemplate.exchange(path, method, entity, responseClass);

        return response.getBody();
    }

    public static <B, R> TwitchCallBuilder<B, R> instance(Class<B> bodyClass, Class<R> responseClass) {
        return new TwitchCallBuilder<>(responseClass);
    }

    public static <R> TwitchCallBuilder<Object, R> instance(Class<R> responseClass) {
        return new TwitchCallBuilder<>(responseClass);
    }

}
