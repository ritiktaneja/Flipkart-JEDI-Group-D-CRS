package com.flipkart.exception;

public class CatalogNotRemovedException extends  Exception{
    String catalogId;
    public CatalogNotRemovedException(String id) {
        this.catalogId = id;
    }
}
