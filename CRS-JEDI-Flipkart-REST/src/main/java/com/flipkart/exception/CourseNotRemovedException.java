package com.flipkart.exception;

/**
 * Course not removed exception class
 */
public class CourseNotRemovedException extends  Exception{

    String courseCode, catalogId;

    /**
     * Course not removed exception Method
     * @param catalogId
     * @param courseCode
     */
    public CourseNotRemovedException(String catalogId, String courseCode, String message) {
        super(message);
        this.catalogId = catalogId;
        this.courseCode = courseCode;
    }
}
