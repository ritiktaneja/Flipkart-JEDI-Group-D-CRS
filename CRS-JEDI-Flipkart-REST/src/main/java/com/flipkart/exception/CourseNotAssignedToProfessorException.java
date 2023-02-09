package com.flipkart.exception;

/**
 * Course not assigned to professor exception class
 */
public class CourseNotAssignedToProfessorException extends  Exception{
    String courseCode, professorId, catalogId;

    /**
     * Course not assigned to professor exception method
     * @param catalogId
     * @param cc
     * @param professorId
     */
    public CourseNotAssignedToProfessorException(String catalogId, String cc, String professorId, String message) {
        super(message);
        this.courseCode = cc;
        this.professorId = professorId;
    }
}
