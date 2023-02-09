package com.flipkart.constants.sqlconstants;

public class AdminDaoConstants {
    public static final String DELETE = "DELETE FROM admin WHERE adminId=?";
    public static final String GET_ALL = "SELECT * FROM admin ORDER BY adminId";
    public static final String GET_BY_ID = "SELECT * FROM admin WHERE adminId=?";
    public static final String INSERT = "INSERT INTO admin(adminId, adminName,currentSemester) VALUES(?, ?,?)";
    public static final String UPDATE = "UPDATE professor SET professorName=?, password=?, WHERE  Id=?";

    public static final String APPROVE_STUDENT = "UPDATE semesterRegistration SET status= ? where studentId = ?";
    public static final String GET_CURRENT_SEM = "SELECT * FROM admin";

    public static final String SET_SEMESTER = "UPDATE admin SET currentSemester = ? ";

}
