package com.flipkart.exception;

import com.flipkart.bean.Professor;

/**
 * Professor not added exception class
 */
public class ProfessorNotAddedException extends Exception {
    String profId, profName, password;

    /**
     * Professor not added exception Method
     * @param id
     * @param name
     * @param password
     */
    public ProfessorNotAddedException(String id, String name, String password) {
        this.profId = profId;
        this.profName = profName;
        this.password = password;
    }
}
