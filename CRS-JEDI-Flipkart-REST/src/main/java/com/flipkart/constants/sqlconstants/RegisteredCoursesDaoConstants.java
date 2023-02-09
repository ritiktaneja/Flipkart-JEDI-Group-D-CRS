package com.flipkart.constants.sqlconstants;

public class RegisteredCoursesDaoConstants {
    public static final String INSERT = "INSERT INTO RegisteredCourses (RegisteredCourseID,studentId, courseCode,timeadded) VALUES(?,?,?,?)";


    public static final String GET_ALL_OBJECTS = "SELECT * FROM registeredCourses WHERE studentId = ?";

    public static final String DELETE = "DELETE FROM registeredCourses WHERE studentId = ? and CourseCode = ?";

    public static final String GET_STUDENTS_IN_COURSE = "SELECT * FROM registeredCourses WHERE courseCode  = ?";
    public static final String GET = "SELECT * FROM registeredCourses WHERE studentId = ? AND courseCode  = ?";
}
