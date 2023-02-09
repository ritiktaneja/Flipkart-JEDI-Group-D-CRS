package com.flipkart.exception;

/**
 * Professor not found exception class
 */
public class ProfessorNotFoundException extends Exception{
    private String professorId;

    /**
     * Professor not found exception Method
     * @param profId
     */
    public ProfessorNotFoundException(String profId, String message) {

        super(message);
        this.professorId = profId;
    }
}
