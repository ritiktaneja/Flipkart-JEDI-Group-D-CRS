package com.flipkart.bean;

import java.util.ArrayList;
import java.util.List;

public class CourseCatalog {

    private String catalogId;
    private List<Course> courses = new ArrayList<>();

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
