package com.flipkart.bean;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.security.Principal;

/**
 * User Class
 */
public class User implements Principal {

    @NotBlank
    private String userId;
    @NotBlank
    private String name;
    @NotBlank
    private String password;

    /**
     * Method to get password for the current object
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method to get user id of the current object
     * @return User Id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Method to get name of the current object
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to set user id to the current object
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Method to set name for the current object
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to set password for the current object
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Default Constructor for user
     * @param userId
     * @param name
     * @param password
     */
    public User(String userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        if(password != null)
            this.password = password;
        else
            this.password = "REDACTED";
    }

    /**
     * User constructor
     */
    public User() {
    }
}
