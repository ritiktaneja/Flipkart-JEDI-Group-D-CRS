package com.flipkart.service;


import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;

import javax.xml.catalog.Catalog;
import java.util.List;

public interface CourseCatalogServices {

    List<Catalog> viewAllCatalogs();

    void addCourseToCatalog(String catalogId, String courseId); // use function inside coursecatalog bean
    void removeCourseFromCatalog(String catalogId, String courseId);

    void addCatalog(String catalogId);
    void deleteCatalog(String catalogId);

    Catalog getCatalogFromId(String Id);

}
