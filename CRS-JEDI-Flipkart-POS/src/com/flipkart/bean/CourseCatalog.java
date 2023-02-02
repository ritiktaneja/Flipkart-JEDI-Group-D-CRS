package com.flipkart.bean;

import java.util.ArrayList;
import java.util.List;

public class CourseCatalog {

<<<<<<< HEAD
    private String catalogId;
    private List<Course> courses = new ArrayList<>();
=======
    private String semester;
    private List<Course> courses;
>>>>>>> c45e388 (CourseCatalogService impl)

    public  void addCourse(Course course) {
        courses.add(course);
    }
    public  List<Course> getCourseList() {
        return courses;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
