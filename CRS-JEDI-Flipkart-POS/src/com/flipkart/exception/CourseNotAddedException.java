package com.flipkart.exception;

public class CourseNotAddedException extends Exception {
    private String courseId, courseCode, courseName;
    public CourseNotAddedException(String courseId, String courseCode, String courseName) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
    }


}
