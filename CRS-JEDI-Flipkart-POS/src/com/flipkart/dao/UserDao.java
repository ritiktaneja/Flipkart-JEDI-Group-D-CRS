package com.flipkart.dao;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

public class UserDao implements DaoInterface<User> {


    private static final String DELETE = "DELETE FROM user WHERE userId=?";
    private static final String GET_ALL = "SELECT * FROM user ORDER BY userId";
    private static final String GET_BY_ID = "SELECT * FROM user WHERE userId=?";
    private static final String INSERT = "INSERT INTO user(userId,password) VALUES(?, ?)";
    private static final String UPDATE = "UPDATE user SET password=? WHERE userId=?";
    private static final String INSERT_IN_ROLE = "INSERT INTO ROLE (userID,role) VALUES(?,?)";
    private static final String loginQuery = "SELECT * FROM user where userid = ? AND password = ?";

    private static UserDao instance = null;

    private UserDao() {

    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public boolean updatePassword(String userId, String password) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(UPDATE);
            stmt.setString(1, password);
            stmt.setString(2, userId);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
        return false;
    }

    public User login(String userId, String password) {
        User user = null;
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(loginQuery);
            statement.setString(1, userId);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user1 = new User();
                user1.setUserId(userId);
                user = user1;
            }
        } catch (Exception e) {
            return null;
        }
        if (user != null) {
            AdminDao adminDao = AdminDao.getInstance();
            ProfessorDao professorDao = ProfessorDao.getInstance();
            StudentDao studentDao = StudentDao.getInstance();
            Admin admin = adminDao.get(userId);
            if (admin != null) {
                return admin;
            }
            Professor professor = professorDao.get(userId);
            if (professor != null) {
                return professor;
            }
            Student student = studentDao.get(userId);
            if (student != null) {
                return student;
            }
        }
        return null;
    }

    @Override
    public User get(String userId) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(GET_BY_ID);
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(userId);
                user.setPassword(rs.getString("password"));
                return user;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public int insert(User user) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement userStatement = null;
        PreparedStatement roleStatement = null;
        try {
            userStatement = connection.prepareStatement(INSERT);
            roleStatement = connection.prepareStatement(INSERT_IN_ROLE);

            userStatement.setString(1, user.getUserId());
            userStatement.setString(2, user.getPassword());

            roleStatement.setString(1, user.getUserId());
            String role = "";
            if (user instanceof Professor) {
                role = "Professor";
            } else if (user instanceof Admin) {
                role = "Admin";
            } else if (user instanceof Student) {
                role = "Student";
            }
            roleStatement.setString(2, role);
            userStatement.executeUpdate();
            System.out.println("User Added Successfully");
            roleStatement.executeUpdate();
            System.out.println("Role Added Successfully");
            return 1;
        } catch (Exception e) {
            System.out.println("User with same ID present");
        } finally {
            DBConnection.closeStatement(roleStatement);
            DBConnection.closeStatement(userStatement);
            DBConnection.closeConnection(connection);
        }
        return 0;
    }

    @Override
    public int update(String id, User user) {
        return 0;
    }

    @Override
    public int delete(User user) {
        return 0;
    }
}
