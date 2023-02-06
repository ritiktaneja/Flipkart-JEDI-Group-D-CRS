package com.flipkart.exception;

public class CourseNotRegisteredException extends  Exception{
    private String studentId, courseCode;

    public CourseNotRegisteredException(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }
}
