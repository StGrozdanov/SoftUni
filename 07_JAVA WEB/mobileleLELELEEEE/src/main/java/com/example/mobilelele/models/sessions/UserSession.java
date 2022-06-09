package com.example.mobilelele.models.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserSession {
    private String username;
    private Boolean isLoggedIn;

    public UserSession() {
        this.isLoggedIn = false;
    }

    public UserSession setUsername(String name) {
        this.username = name;
        return this;
    }

    public UserSession setLoggedIn(boolean loggedIn) {
        this.isLoggedIn = loggedIn;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public Boolean userIsNotLoggedIn() {
        return !this.isLoggedIn;
    }

    public void clear() {
        this.setUsername(null);
        this.setLoggedIn(false);
    }
}