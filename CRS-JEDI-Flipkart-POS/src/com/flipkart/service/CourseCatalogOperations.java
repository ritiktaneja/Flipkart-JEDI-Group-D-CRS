package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.dao.CourseCatalogDao;
import com.flipkart.dao.CourseDao;
import com.flipkart.data.MockDB;


import javax.xml.catalog.Catalog;
import java.util.ArrayList;
import java.util.List;

public class CourseCatalogOperations implements CourseCatalogServices {

    public List<CourseCatalog> viewAllCatalogs() {
        List<CourseCatalog> catalogList = new ArrayList<>();
        for (CourseCatalog catalog : MockDB.catalogs) {
            catalogList.add(catalog);
        }
        return catalogList;
    }

    public CourseCatalog getCatalogFromId(String id) {
        return MockDB.getCatalogFromId(id);
    }

    public void addCourseToCatalog(String catalogId, String courseCode, String courseName) {
        CourseCatalogDao courseCatalogDao = new CourseCatalogDao();
        CourseCatalog catalog = courseCatalogDao.get(catalogId);
        if (catalog == null) {
            catalog = new CourseCatalog();
            catalog.setCatalogId(catalogId);
            System.out.println("New Catalog created with id = " + catalogId);
        } else {
            System.out.println("Catalog with this id present");
        }
        List<Course> courseList = catalog.getCourses();
        for (Course c : courseList) {
            if (c.getCourseCode().equals(courseCode)) {
                System.out.println("The course with this id is already present in catalog");
                return;
            }
        }
        catalog.getCourses().clear();

        Course course = new Course();
        course.setCourseCode(courseCode);
        course.setName(courseName);
        course.setProfessor(null);

        catalog.addCourse(course);


        courseCatalogDao.insert(catalog);
        System.out.println("Course Added to catalog Successfully");
    }

    public void removeCourseFromCatalog(String catalogId, String courseId) {
        CourseCatalogDao dao = new CourseCatalogDao();
        dao.deleteCourseFromCatalog(catalogId,courseId);

    }

    public void addCatalog(String catalogId) {
        CourseCatalog catalog = getCatalogFromId(catalogId);
        if (catalog == null) {
            CourseCatalog catalog1 = new CourseCatalog();
            catalog1.setCatalogId(catalogId);
            MockDB.catalogs.add(catalog1);
        }

    }

    public void deleteCatalog(String catalogId) {
        CourseCatalog catalog = getCatalogFromId(catalogId);
        if (catalog != null) {
            MockDB.catalogs.remove(catalog);
        }
    }


    public List<Course> listCoursesInCatalog(String catalogId) {
        CourseCatalog catalog = getCatalogFromId(catalogId);
        if (catalog != null) {
            return catalog.getCourses();
        }
        return null;
    }


}
