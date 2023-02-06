package com.flipkart.exception;

public class StudentNotApprovedException extends Exception{
    String studentId;
    public StudentNotApprovedException(String id) {
        this.studentId = id;
    }
}
