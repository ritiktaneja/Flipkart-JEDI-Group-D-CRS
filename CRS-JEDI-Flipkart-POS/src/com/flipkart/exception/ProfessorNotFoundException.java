package com.flipkart.exception;

public class ProfessorNotFoundException extends Exception{
    private String professorId;
    public ProfessorNotFoundException(String profId) {
        this.professorId = profId;
    }
}
