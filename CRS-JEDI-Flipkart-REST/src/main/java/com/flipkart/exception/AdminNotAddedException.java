package com.flipkart.exception;

/**
 * Admin Not added exception class
 */
public class AdminNotAddedException extends Exception{
    String adminId, adminName, password;

    /**
     * Admin Not added exception method
     * @param id
     * @param name
     * @param password
     */
    public AdminNotAddedException(String id, String name, String password, String message) {
        super(message);
        this.adminId = id;
        this.adminName = name;
        this.password = password;
    }
}
