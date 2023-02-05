package com.flipkart.dao;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class UserDao implements DaoInterface<User> {


    private static final String DELETE = "DELETE FROM user WHERE userId=?";
    private static final String GET_ALL = "SELECT * FROM user ORDER BY userId";
    private static final String GET_BY_ID = "SELECT * FROM user WHERE userId=?";
    private static final String INSERT = "INSERT INTO user(userId,password) VALUES(?, ?)";
    private static final String UPDATE = "UPDATE user SET password=? WHERE userId=?";
    private static final String INSERT_IN_ROLE = "INSERT INTO ROLE (userID,role) VALUES(?,?)";


    public User login(String userId, String password) {
        User user = get(userId);
        if (user == null) {
            System.out.println("UserID or password is Incorrect");
            return null;
        }
        AdminDao adminDao = new AdminDao();
        ProfessorDao professorDao = new ProfessorDao();
        StudentDao studentDao = new StudentDao();
        Admin admin = adminDao.get(userId);
        if (admin != null) {
            System.out.println("Admin Login Successful");
            return admin;
        }
        Professor professor = professorDao.get(userId);
        if (professor != null) {
            System.out.println("Professor Login Successful");
            return professor;
        }
        Student student = studentDao.get(userId);
        if (student != null) {
            System.out.println("Student Login Successful");
            return student;
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
