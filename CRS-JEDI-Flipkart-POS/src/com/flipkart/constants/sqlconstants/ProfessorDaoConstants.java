package com.flipkart.constants.sqlconstants;

public class ProfessorDaoConstants {
    public static final String DELETE = "DELETE FROM professor WHERE professorId=?";
    public static final String GET_ALL = "SELECT * FROM professor ORDER BY professorId";
    public static final String GET_BY_ID = "SELECT * FROM professor WHERE professorId=?";
    public static final String INSERT = "INSERT INTO professor(professorId,professorName) VALUES(?, ?)";
    public static final String UPDATE = "UPDATE professor SET name=?, password=?, department=?, designation=? WHERE professorId=?";
    public static final String GET_USER = "SELECT * FROM user where userId= ?";
}
