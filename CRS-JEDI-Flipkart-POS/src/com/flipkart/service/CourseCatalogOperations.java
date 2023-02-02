package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.data.MockDB;


import javax.xml.catalog.Catalog;
import java.util.ArrayList;
import java.util.List;

public class CourseCatalogOperations implements CourseCatalogServices {

    public List<CourseCatalog> viewAllCatalogs() {
        List<CourseCatalog> catalogList = new ArrayList<>();
        for(CourseCatalog catalog : MockDB.catalogs) {
            catalogList.add(catalog);
        }
        return catalogList;
    }

    public void addCourseToCatalog(String catalogId, String courseCode, String courseName) {
        CourseCatalog catalog = getCatalogFromId(catalogId);
        if(catalog != null) {
            Course course = new Course();
            course.setCourseCode(courseCode);
            course.setName(courseName);
            catalog.addCourse(course);
        }
    }
    public void removeCourseFromCatalog(String catalogId, String courseId) {
        CourseCatalog catalog  = getCatalogFromId(catalogId);
        if(catalog != null) {
            catalog.removeCourse(courseId);
        }
    }

    public void addCatalog(String catalogId) {
        CourseCatalog catalog = getCatalogFromId(catalogId);
        if(catalog == null) {
            CourseCatalog catalog1 = new CourseCatalog();
            catalog1.setCatalogId(catalogId);
            MockDB.catalogs.add(catalog1);
        }

    }
    public void deleteCatalog(String catalogId) {
        CourseCatalog catalog = getCatalogFromId(catalogId);
        if(catalog != null) {
            MockDB.catalogs.remove(catalog);
        }
    }

   public CourseCatalog getCatalogFromId(String id) {
       return MockDB.getCatalogFromId(id);
   }

   public List<Course> listCoursesInCatalog(String catalogId) {
        CourseCatalog catalog = getCatalogFromId(catalogId);
        if(catalog != null) {
            return catalog.getCourses();
        }
        return null;
   }


}
