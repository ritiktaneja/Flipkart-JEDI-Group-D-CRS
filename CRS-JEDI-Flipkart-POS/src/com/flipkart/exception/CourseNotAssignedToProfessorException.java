package com.flipkart.exception;

public class CourseNotAssignedToProfessorException extends  Exception{
    String courseCode, professorId, catalogId;
    public CourseNotAssignedToProfessorException(String catalogId, String cc, String professorId) {
        this.courseCode = cc;
        this.professorId = professorId;
    }
}
