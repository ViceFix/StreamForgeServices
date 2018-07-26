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
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "external_id")
    private Long externalId;

    @Column(name = "display_name")
    private String displayName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public Long getExternalId() {
        return externalId;
    }

    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
