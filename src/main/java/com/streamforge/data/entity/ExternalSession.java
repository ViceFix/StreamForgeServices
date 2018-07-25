package com.streamforge.data.entity;

import javax.persistence.*;
import java.util.Date;

@NamedQueries({
        @NamedQuery(
                name = ExternalSession.SQL_GET_BY_SERVICE_TOKEN,
                query = "select es from ExternalSession es where es.serviceToken = :token"
        ),
        @NamedQuery(
                name = ExternalSession.SQL_GET_BY_USER_ID,
                query = "select es from ExternalSession es where es.userId = :userId"
        )
})

@Entity
@Table(name = "external_session")
public class ExternalSession {

    public static final String SQL_GET_BY_SERVICE_TOKEN = "externalSession.getByServiceToken";
    public static final String SQL_GET_BY_USER_ID = "externalSession.getByUserId";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "external_session_id")
    private Long externalSessionId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "service_token")
    private String serviceToken;

    public Long getExternalSessionId() {
        return externalSessionId;
    }

    public void setExternalSessionId(Long externalSessionId) {
        this.externalSessionId = externalSessionId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getServiceToken() {
        return serviceToken;
    }

    public void setServiceToken(String serviceToken) {
        this.serviceToken = serviceToken;
    }

    public ExternalSession() {}

    private ExternalSession(ExternalSessionBuilder builder) {
        this.userId = builder.userId;
        this.accessToken = builder.accessToken;
        this.refreshToken = builder.refreshToken;
        this.expirationDate = builder.expirationDate;
        this.serviceToken = builder.serviceToken;
    }

    public static class ExternalSessionBuilder {
        private Long externalSessionId;
        private Long userId;
        private String accessToken;
        private String refreshToken;
        private Date expirationDate;
        private String serviceToken;

        public ExternalSessionBuilder setExternalSessionId(Long externalSessionId) {
            this.externalSessionId = externalSessionId;
            return this;
        }

        public ExternalSessionBuilder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public ExternalSessionBuilder setAccessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public ExternalSessionBuilder setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public ExternalSessionBuilder setExpirationDate(Date expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public ExternalSessionBuilder setServiceToken(String serviceToken) {
            this.serviceToken = serviceToken;
            return this;
        }

        public ExternalSession build() {
            return new ExternalSession(this);
        }
    }

}
