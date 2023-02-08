package com.flipkart.exception;

/**
 * Course not registered exception class
 */
public class CourseNotRegisteredException extends  Exception{
    private String studentId, courseCode;

    /**
     * course not registered exception method
     * @param studentId
     * @param courseCode
     */
    public CourseNotRegisteredException(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }
}
