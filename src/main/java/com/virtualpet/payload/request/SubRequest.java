package com.virtualpet.payload.request;

import com.virtualpet.models.User;

public class SubRequest {
    private String name;
    private User user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
