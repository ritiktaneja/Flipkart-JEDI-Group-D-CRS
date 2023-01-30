package com.flipkart.bean;

public class User {

    private String userId;
    private String name;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User updateDetails(String name) {
        this.name = name;
        return this;
    }

    public boolean updatePassword(String oldPassword, String newPassword) {
        if(this.getPassword().equals(oldPassword)) {
            this.setPassword(newPassword);
            return true;
        } else {
            return false;
        }
    }
}
