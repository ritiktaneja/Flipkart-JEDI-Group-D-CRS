package com.flipkart.exception;

public class CourseNotFoundException extends Exception{
    private String catalogId, courseCode;
    public CourseNotFoundException(String catalogId, String courseCode) {
        this.catalogId = catalogId;
        this.courseCode = courseCode;
    }

}
