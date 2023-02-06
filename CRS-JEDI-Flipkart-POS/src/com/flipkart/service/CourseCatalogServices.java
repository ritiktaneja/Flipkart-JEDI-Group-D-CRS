package com.flipkart.service;


import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;

import javax.xml.catalog.Catalog;
import java.util.List;

public interface CourseCatalogServices {

    List<CourseCatalog> viewAllCatalogs();

    /**
     *
     * @param catalogId
     * @param courseCode
     * @param courseName
     */
    void addCourseToCatalog(String catalogId, String courseCode, String courseName); // use function inside coursecatalog bean

    /**
     *
     * @param catalogId
     * @param courseCode
     */
    void removeCourseFromCatalog(String catalogId, String courseCode);

    /**
     *
     * @param catalogId
     */
    void addCatalog(String catalogId);

    /**
     *
     * @param catalogId
     */
    void deleteCatalog(String catalogId);

    /**
     *
     * @param Id
     * @return Course Catalog
     */
    CourseCatalog getCatalogFromId(String Id);

    List<Course> listCoursesInCatalog(String id);

}
