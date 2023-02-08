package com.flipkart.constants.sqlconstants;

public class StudentDaoConstants {
    public static final String DELETE = "DELETE FROM student WHERE studentId=?";
    public static final String GET_ALL = "SELECT * FROM student ORDER BY studentId";
    public static final String GET_BY_ID = "SELECT * FROM student WHERE studentId=?";
    public static final String INSERT = "INSERT INTO student(studentId, studentName) VALUES(?, ?)";
    public static final String UPDATE = "UPDATE user SET password=? WHERE userId=?";
    public static final String GET_USER = "SELECT * FROM user where userId = ?";
}
