package com.flipkart.exception;

/**
 * Catalog not found exception class
 */
public class CatalogNotFoundException extends Exception{
    String id;

    /**
     * Catalog not found exception method
     * @param id
     */
    public CatalogNotFoundException(String id, String message) {

        super(message);
        this.id = id;
    }
}
