package com.flipkart.exception;

/**
 * Course not found exception class
 */
public class CourseNotFoundException extends Exception{
    private String catalogId, courseCode;

    /**
     * Course not found exception method
     * @param catalogId
     * @param courseCode
     */
    public CourseNotFoundException(String catalogId, String courseCode, String message) {
        super(message);
        this.catalogId = catalogId;
        this.courseCode = courseCode;
    }

}
