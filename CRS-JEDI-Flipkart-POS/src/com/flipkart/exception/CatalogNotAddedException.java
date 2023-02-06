package com.flipkart.exception;

public class CatalogNotAddedException extends Exception{
    String catalogName;
    public CatalogNotAddedException(String catalogName) {
        this.catalogName = catalogName;
    }
}
