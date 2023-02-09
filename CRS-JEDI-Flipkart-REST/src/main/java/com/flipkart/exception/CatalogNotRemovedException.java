package com.flipkart.exception;

/**
 * Catalog not removed exception class
 */
public class CatalogNotRemovedException extends  Exception{
    String catalogId;

    /**
     * Catalog not removed exception method
     * @param id
     */
    public CatalogNotRemovedException(String id, String message) {

        super(message);
        this.catalogId = id;
    }
}
