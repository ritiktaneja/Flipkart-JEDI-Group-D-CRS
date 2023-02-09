package com.flipkart.service;


import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.exception.*;

import java.util.List;

/**
 * CourseCatalogServices Class
 */
public interface CourseCatalogServices {

    List<CourseCatalog> viewAllCatalogs();

    void addCourseToCatalog(String catalogId, String courseCode, String courseName) throws CourseNotAddedException; // use function inside coursecatalog bean
    void removeCourseFromCatalog(String catalogId, String courseCode) throws CourseNotRemovedException;

    void addCatalog(String catalogId) throws CatalogNotAddedException;
    void deleteCatalog(String catalogId) throws CatalogNotRemovedException;
    CourseCatalog getCatalogFromId(String Id) throws CatalogNotFoundException;

    List<Course> listCoursesInCatalog(String id) throws CatalogNotFoundException;

}
