package com.flipkart.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Error {

    @JsonProperty
    private String errorMessage;

    @Override
    public String toString() {
        return errorMessage;
    }
    public Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
