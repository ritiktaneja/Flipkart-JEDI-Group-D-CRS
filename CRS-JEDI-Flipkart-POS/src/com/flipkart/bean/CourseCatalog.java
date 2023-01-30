package com.flipkart.bean;

import java.util.ArrayList;
import java.util.List;

public class CourseCatalog {

    private List<Course> courses;

    public  void addCourse(Course course) {
        courses.add(course);
    }
    public  List<Course> getCourseList() {
        return courses;
    }

}
