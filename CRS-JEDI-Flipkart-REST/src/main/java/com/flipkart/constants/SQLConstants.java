package com.flipkart.constants;

public class SQLConstants {
    private static final String DELETE = "DELETE FROM admin WHERE adminId=?";
    private static final String GET_ALL = "SELECT * FROM admin ORDER BY adminId";
    private static final String GET_BY_ID = "SELECT * FROM admin WHERE adminId=?";
    private static final String INSERT = "INSERT INTO admin(adminId, adminName,currentSemester) VALUES(?, ?,?)";
    private static final String UPDATE = "UPDATE professor SET professorName=?, password=?, WHERE  Id=?";

    private static final String APPROVE_STUDENT = "UPDATE semesterRegistration SET status= ? where studentId = ?";
    private static final String GET_CURRENT_SEM = "SELECT * FROM admin";

    private static final String SET_SEMESTER = "UPDATE admin SET currentSemester = ? ";

}
