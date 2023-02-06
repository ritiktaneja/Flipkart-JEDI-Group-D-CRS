package com.flipkart.bean;

public class User {
    private String userId;
    private String name;
    private String password;

    /**
     *
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return User Id
     */
    public String getUserId() {
        return userId;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @param userId
     * @param name
     * @param password
     */
    public User(String userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }

    public User() {
    }
}
