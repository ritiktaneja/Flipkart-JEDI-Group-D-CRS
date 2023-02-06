package com.flipkart.exception;

public class CatalogNotFoundException extends Exception{
    String id;
    public CatalogNotFoundException(String id) {
        this.id = id;
    }
}
