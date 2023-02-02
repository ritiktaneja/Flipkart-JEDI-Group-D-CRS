package com.flipkart.service;

import com.flipkart.data.MockDB;

import javax.xml.catalog.Catalog;
import java.util.ArrayList;
import java.util.List;

public class CourseCatalogOperations implements CourseCatalogServices {

    List<Catalog> viewAllCatalogs() {
        List<Catalog> catalogList = new ArrayList<>();
        for(Catalog catalog : MockDB.)
    }

    void addCourseToCatalog(String catalogId, String courseId); // use function inside coursecatalog bean
    void removeCourseFromCatalog(String catalogId, String courseId);

    void addCatalog(String catalogId);
    void deleteCatalog(String catalogId);

    Catalog getCatalogFromId(String Id);

}
