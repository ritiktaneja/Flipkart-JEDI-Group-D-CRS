package com.flipkart.exception;

/**
 * Student not approved exception class
 */
public class StudentNotApprovedException extends Exception{
    String studentId;

    /**
     * Student not approved exception Method
     * @param id
     */
    public StudentNotApprovedException(String id) {
        this.studentId = id;
    }
}
