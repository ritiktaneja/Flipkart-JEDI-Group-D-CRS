package com.flipkart.exception;

public class CourseNotRemovedException extends  Exception{

    String courseCode, catalogId;
    public CourseNotRemovedException(String catalogId, String courseCode) {
        this.catalogId = catalogId;
        this.courseCode = courseCode;
    }
}
