package com.flipkart.exception;

import com.flipkart.bean.Professor;

public class ProfessorNotAddedException extends Exception {
    String profId, profName, password;
    public ProfessorNotAddedException(String id, String name, String password) {
        this.profId = profId;
        this.profName = profName;
        this.password = password;
    }
}
