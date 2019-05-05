package com.company.main.request;

import com.company.main.model.User;

public class AuthorizationRequest extends Request{
    private User user;
    public AuthorizationRequest(User user, String name) {
        super(name);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
