package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.dao.CourseCatalogDao;
import com.flipkart.dao.CourseDao;
import com.flipkart.data.MockDB;
import com.flipkart.exception.*;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * CourseCatalogOperations class
 */
public class CourseCatalogOperations implements CourseCatalogServices {

    public List<CourseCatalog> viewAllCatalogs() {
        List<CourseCatalog> catalogList = new ArrayList<>();
        for (CourseCatalog catalog : MockDB.catalogs) {
            catalogList.add(catalog);
        }
        return catalogList;
    }

    /**
     * Get catalog from the id
     * @param id
     * @return
     * @throws CatalogNotFoundException
     */
    public CourseCatalog getCatalogFromId(String id) throws CatalogNotFoundException {
        try {
            return MockDB.getCatalogFromId(id);
        } catch(Exception e) {
            throw new CatalogNotFoundException(id, e.getMessage());
        }
    }

    /**
     * Method for adding course in catalog
     * @param catalogId
     * @param courseCode
     * @param courseName
     * @throws CourseNotAddedException
     */
    public void addCourseToCatalog(String catalogId, String courseCode, String courseName) throws CourseNotAddedException {
        try {
            CourseCatalogDao courseCatalogDao = CourseCatalogDao.getInstance();
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
        } catch (Exception e) {
            throw new CourseNotAddedException(catalogId, courseCode, courseName, e.getMessage());
        }
    }

    /**
     * Method for removing course from catalog
     * @param catalogId
     * @param courseId
     * @throws CourseNotRemovedException
     */
    public void removeCourseFromCatalog(String catalogId, String courseId) throws CourseNotRemovedException {
        try {
            CourseCatalogDao dao = CourseCatalogDao.getInstance();
            dao.deleteCourseFromCatalog(catalogId, courseId);
        } catch(Exception e) {
            throw new CourseNotRemovedException(catalogId, courseId, e.getMessage());
        }
    }

    /**
     * Method for adding new Semester
     * @param catalogId
     * @throws CatalogNotAddedException
     */
    public void addCatalog(String catalogId) throws CatalogNotAddedException {
       try {
           CourseCatalog catalog = getCatalogFromId(catalogId);
           if (catalog == null) {
               CourseCatalog catalog1 = new CourseCatalog();
               catalog1.setCatalogId(catalogId);
               MockDB.catalogs.add(catalog1);
           }
       } catch (Exception e) {
           throw new CatalogNotAddedException(catalogId, e.getMessage());
       }

    }

    /**
     * Method to delete course from the catalog
     * @param catalogId
     * @throws CatalogNotRemovedException
     */
    public void deleteCatalog(String catalogId) throws CatalogNotRemovedException {
        try {
            CourseCatalog catalog = getCatalogFromId(catalogId);
            if (catalog != null) {
                MockDB.catalogs.remove(catalog);
            }
        } catch(Exception e) {
            throw new CatalogNotRemovedException(catalogId, e.getMessage());
        }
    }

    /**
     * listing course which is in catalag
     * @param catalogId
     * @return list of Courses ID
     * @throws CatalogNotFoundException
     */
    public List<Course> listCoursesInCatalog(String catalogId) throws CatalogNotFoundException {
        try {
            CourseCatalog catalog = getCatalogFromId(catalogId);
            if (catalog != null) {
                return catalog.getCourses();
            }
            return null;
        } catch (Exception e) {
            throw new CatalogNotFoundException(catalogId, e.getMessage());
        }
    }


}
