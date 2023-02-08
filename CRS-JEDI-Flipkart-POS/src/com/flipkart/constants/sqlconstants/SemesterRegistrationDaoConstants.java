package com.flipkart.constants.sqlconstants;

public class SemesterRegistrationDaoConstants {
    public static final String GET_STATUS = "SELECT * FROM semesterRegistration WHERE studentId = ? and semester = ?";
    public static final String INSERT = "INSERT INTO semesterRegistration(studentId, semester,status) VALUES(?,?,?)";
    public static final String GET = "SELECT * FROM semesterRegistration WHERE studentId = ? AND semester = ?";
}
