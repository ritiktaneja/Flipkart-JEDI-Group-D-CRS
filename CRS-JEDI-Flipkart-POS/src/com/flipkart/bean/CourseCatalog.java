package com.flipkart.bean;

import java.util.ArrayList;
import java.util.List;

public class CourseCatalog {


    private String catalogId;
    private List<Course> courses = new ArrayList<>();

    /**
     * Method to add course in the Course Catalog
     * @param course
     */
    public  void addCourse(Course course) {
        for(Course course1 : courses) {
            if(course1 == course) { // duplicate course
                return;
            }
        }
        courses.add(course);
    }

    /**
     * Method to remove Course
     * @param courseCode
     */
    public void removeCourse(String courseCode) {
        for(int i = 0; i < courses.size(); i++) {
            if(courses.get(i).getCourseCode().equals(courseCode)) {
                courses.remove(i);
                break;
            }
        }
    }

    /**
     * Method to set Catalog Id
     * @param catalogId
     */
    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    /**
     * Method to get Catalog ID
     * @return catalog Id
     */
    public String getCatalogId() {
        return this.catalogId;
    }

    /**
     *
     * @return Courses
     */
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

    /**
     * Method to set Courses
     * @param courses
     */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
