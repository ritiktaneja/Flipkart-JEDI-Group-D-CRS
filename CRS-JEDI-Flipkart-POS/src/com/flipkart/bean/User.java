package com.flipkart.bean;

public class User {
    private String userId;
    private String name;
    private String password;
    public String getPassword() {
        return password;
    }
    public String getUserId() {
        return userId;
    }
    public String getName() {
        return name;
    }

    public User(String userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }

}
