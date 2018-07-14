package com.streamforge.data.entity;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = WebUser.SQL_FIND_BY_CREDENTIALS,
                query = "select wu from WebUser wu where wu.username = :username and wu.password = :password"
        ),
        @NamedQuery(
                name = WebUser.SQL_FIND_BY_USERNAME,
                query = "select wu from WebUser wu where wu.username = :username"
        )
})

@Entity
@Table(name = "web_user")
public class WebUser {

    public static final String SQL_FIND_BY_USERNAME = "WebUser.findByUsername";
    public static final String SQL_FIND_BY_CREDENTIALS = "WebUser.findByCredentials";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
