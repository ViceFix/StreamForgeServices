package com.streamforge.data.entity;

import javax.persistence.*;
import java.util.Date;

@NamedQueries({
        @NamedQuery(
                name = Session.SQL_GET_BY_TOKEN,
                query = "select s from Session s where s.token = :token"
        ),
        @NamedQuery(
                name = Session.SQL_GET_BY_USER_ID,
                query = "select s from Session s where s.userId = :userId"
        )
})

@Entity
@Table(name = "session")
public class Session {

    public static final String SQL_GET_BY_TOKEN = "Session.getByToken";
    public static final String SQL_GET_BY_USER_ID = "Session.getByUserId";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private long sessionId;

    @Column(name = "user_id")
    private long userId;

    @OneToOne(fetch = FetchType.LAZY)
    private WebUser user;

    @Column(name = "token")
    private String token;

    @Column(name = "last_auth_date")
    private Date lastAuthDate;

    @Transient
    private State state;

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public WebUser getUser() {
        return user;
    }

    public void setUser(WebUser user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastAuthDate() {
        return lastAuthDate;
    }

    public void setLastAuthDate(Date lastAuthDate) {
        this.lastAuthDate = lastAuthDate;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    private Session(SessionBuilder sessionBuilder) {
        userId = sessionBuilder.userId;
        token = sessionBuilder.token;
        // @todo probably it should be changed to db sysdate
        lastAuthDate = new Date();
    }

    public Session() {}

    public static class SessionBuilder {
        private long userId;
        private String token;

        public SessionBuilder setUserId(long userId) {
            this.userId = userId;
            return this;
        }

        public SessionBuilder setToken(String token) {
            this.token = token;
            return this;
        }

        public Session build() {
            return new Session(this);
        }
    }

    public enum State {
        ACTIVE,
        EXPIRED,
        REMOVED
    }
}
