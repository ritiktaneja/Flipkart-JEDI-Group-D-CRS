package com.flipkart.service;


import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;

import javax.xml.catalog.Catalog;
import java.util.List;

public interface CourseCatalogServices {

    List<CourseCatalog> viewAllCatalogs();

    void addCourseToCatalog(String catalogId, String courseCode, String courseName); // use function inside coursecatalog bean
    void removeCourseFromCatalog(String catalogId, String courseCode);

    void addCatalog(String catalogId);
    void deleteCatalog(String catalogId);
    CourseCatalog getCatalogFromId(String Id);

    List<Course> listCoursesInCatalog(String id);

}
