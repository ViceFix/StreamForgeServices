package com.streamforge.data.dto.twitch;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Date;

public class UserDataNativeDto {

    private String logo;

    @JsonAlias("display_name")
    private String displayName;

    @JsonAlias("updated_at")
    private Date updatedAt;

    @JsonAlias("_id")
    private Long id;

    private String email;

    private String name;

    private Date createdAt;

    private String partnered;

    private String type;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getPartnered() {
        return partnered;
    }

    public void setPartnered(String partnered) {
        this.partnered = partnered;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserDataNativeDto{" +
                "\nlogo='" + logo + '\'' +
                "\ndisplay_name='" + displayName + '\'' +
                "\nupdated_at='" + updatedAt + '\'' +
                "\n_id='" + id + '\'' +
                "\nemail='" + email + '\'' +
                "\nname='" + name + '\'' +
                "\ncreated_at='" + createdAt + '\'' +
                "\npartnered='" + partnered + '\'' +
                "\ntype='" + type + '\'' +
                '}';
    }
}

