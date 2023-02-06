package com.flipkart.exception;

public class GradeNotAddedException extends Exception{
//    private int
    private String studentId, courseCode, grade;
    public GradeNotAddedException(String studentId, String courseCode, String grade) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.grade = grade;
    }
}
