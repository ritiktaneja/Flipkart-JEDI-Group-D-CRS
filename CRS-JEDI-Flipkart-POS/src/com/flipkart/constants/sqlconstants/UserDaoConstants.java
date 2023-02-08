package com.flipkart.constants.sqlconstants;

public class UserDaoConstants {

    public static String DELETE = "DELETE FROM user WHERE userId=?";
    public static String GET_ALL = "SELECT * FROM user ORDER BY userId";
    public static String GET_BY_ID = "SELECT * FROM user WHERE userId=?";
    public static String INSERT = "INSERT INTO user(userId,password) VALUES(?, ?)";
    public static String UPDATE = "UPDATE user SET password=? WHERE userId=?";
    public static String INSERT_IN_ROLE = "INSERT INTO ROLE (userID,role) VALUES(?,?)";
    public static String loginQuery = "SELECT * FROM user where userid = ? AND password = ?";
}
