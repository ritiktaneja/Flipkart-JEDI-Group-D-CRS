package com.flipkart.bean;

import java.util.ArrayList;
import java.util.List;

public class CourseCatalog {


    private String catalogId;
    private List<Course> courses = new ArrayList<>();


    public  void addCourse(Course course) {
        for(Course course1 : courses) {
            if(course1 == course) { // duplicate course
                return;
            }
        }
        courses.add(course);
    }

    public void removeCourse(String courseCode) {
        for(int i = 0; i < courses.size(); i++) {
            if(courses.get(i).getCourseCode().equals(courseCode)) {
                courses.remove(i);
                break;
            }
        }
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogId() {
        return this.catalogId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        String str = " Catalog ID : " + this.catalogId + "\n";
        str += "Courses : \n";
        for(Course course : this.courses) {
            str += course + "\n";
        }
        return str;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
