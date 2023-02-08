package com.flipkart.exception;

/**
 * Grade not added exception class
 */
public class GradeNotAddedException extends Exception{
//    private int
    private String studentId, courseCode, grade;

    /**
     * Grade not added exception class Method
     * @param studentId
     * @param courseCode
     * @param grade
     */
    public GradeNotAddedException(String studentId, String courseCode, String grade) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.grade = grade;
    }
}
