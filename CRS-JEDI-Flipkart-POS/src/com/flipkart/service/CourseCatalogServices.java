package com.flipkart.service;


import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;

import javax.xml.catalog.Catalog;
import java.util.List;

public interface CourseCatalogServices {

    List<Catalog> viewAllCatalogs();

    void addCourseToCatalog(CourseCatalog catalog, Course course); // use function inside coursecatalog bean
    void removeCourseFromCatalog(CourseCatalog catalog, Course course);

    void addCatalog(String catalogId);
    void deleteCatalog(String catalogId);

}
