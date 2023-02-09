package com.flipkart.exception;

/**
 * Catalog Not added exception class
 */
public class CatalogNotAddedException extends Exception{
    String catalogName;

    /**
     * Catalog Not added exception method
     * @param catalogName
     */
    public CatalogNotAddedException(String catalogName, String message) {

        super(message);
        this.catalogName = catalogName;

    }
}
