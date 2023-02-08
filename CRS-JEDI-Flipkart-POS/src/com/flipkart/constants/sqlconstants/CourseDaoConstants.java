package com.flipkart.constants.sqlconstants;

public class CourseDaoConstants {
    public static final String DELETE = "DELETE FROM Course WHERE courseCode=?";
    public static final String GET_ALL = "SELECT * FROM Course ORDER BY courseCode";
    public static final String GET_BY_ID = "SELECT * FROM Course WHERE courseCode=?";
    public static final String INSERT = "INSERT INTO Course(courseCode, courseName) VALUES(?, ?)";
    public static final String UPDATE = "UPDATE Course SET courseName=? WHERE courseCode=?";
}
