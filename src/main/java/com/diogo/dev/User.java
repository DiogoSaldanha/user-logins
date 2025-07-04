package com.diogo.dev;

import java.util.Objects;

public class User {
    private String username;
    private String password_hash;

    public User(String username, String password_hash) {
        this.username = username;
        this.password_hash = password_hash;
    }

    public String getPasswordHash() {
        return password_hash;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; //same instance? return true
        if (!(obj instanceof User)) return false;
        User user = (User) obj;
        return Objects.equals(username, user.username) && Objects.equals(password_hash, user.password_hash);
    }
}
