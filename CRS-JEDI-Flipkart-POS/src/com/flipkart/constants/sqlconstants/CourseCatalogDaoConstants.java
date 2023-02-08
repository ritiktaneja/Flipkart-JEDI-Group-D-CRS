package com.flipkart.constants.sqlconstants;

public class CourseCatalogDaoConstants {
    public static final String DELETE = "DELETE FROM CourseCatalog WHERE catalogID=?";
    public static final String GET_ALL = "SELECT * FROM CourseCatalog ORDER BY catalogId";
    public static final String GET_BY_ID = "SELECT * FROM courseCatalog WHERE catalogId=?";
    public static final String INSERT = "insert into coursecatalog(coursecode,catalogid,professorid) values(?,?,?);";
    public static final String UPDATE = "UPDATE CourseCatalog SET name=?, password=?, WHERE catalogId=?";
    public static final String GET_ALL_BY_CATALOG_ID = "SELECT * FROM CourseCatalog where catalogId = ?";
    public static final String UPDATE_COURSE_IN_CATALOG = "UPDATE CourseCatalog SET professorID = ? WHERE courseCode =? and CatalogID = ?";
    public static final String GET_CATALOG_ID_FROM_COURSE = "SELECT catalogID from CourseCatalog where courseCode = ?";
    public static final String DELETE_COURSE_FROM_CATALOG = "DELETE FROM courseCatalog where catalogId=? and courseCode=?";
    public static final String GET_PROFESSOR = "SELECT professorID FROM CourseCatalog WHERE catalogId=? AND courseCode=?";
    public static final String GET_COURSES_BY_PROFESSOR_ID = "SELECT * FROM courseCatalog WHERE professorId = ? ";
    public static final String GET_STUDENTS_BY_COURSE_ID = "SELECT * FROM RegisteredCourses WHERE CourseCode = ?";
}
