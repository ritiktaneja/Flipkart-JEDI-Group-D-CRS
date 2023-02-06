package com.flipkart.exception;

public class AdminNotAddedException extends Exception{
    String adminId, adminName, password;
    public AdminNotAddedException(String id, String name, String password) {
        this.adminId = id;
        this.adminName = name;
        this.password = password;
    }
}
