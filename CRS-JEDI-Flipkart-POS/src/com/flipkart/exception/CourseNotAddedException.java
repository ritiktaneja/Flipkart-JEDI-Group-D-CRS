package com.flipkart.exception;

/**
 * Course not added exception class
 */
public class CourseNotAddedException extends Exception {
    private String courseId, courseCode, courseName;

    /**
     * Course not added exception class
     * @param courseId
     * @param courseCode
     * @param courseName
     */
    public CourseNotAddedException(String courseId, String courseCode, String courseName) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
    }


}
