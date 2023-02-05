package com.flipkart.dao;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constants.Department;
import com.flipkart.constants.Designation;
import com.flipkart.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao implements DaoInterface<Admin> {

    private static final String DELETE = "DELETE FROM admin WHERE adminId=?";
    private static final String GET_ALL = "SELECT * FROM admin ORDER BY adminId";
    private static final String GET_BY_ID = "SELECT * FROM admin WHERE adminId=?";
    private static final String INSERT = "INSERT INTO admin(adminId, adminName) VALUES(?, ?)";
    private static final String UPDATE = "UPDATE professor SET name=?, password=?, WHERE adminId=?";
    private static final String INSERT_IN_USER = "INSERT INTO user (userId,password) VALUES(?,?)";
    private static final String INSERT_IN_ROLE = "INSERT INTO ROLE (userID,role) VALUES(?,?)";

    @Override
    public Admin get(String id) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(GET_BY_ID);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Admin.AdminBuilder builder = new Admin.AdminBuilder();
                builder.setAdminId(rs.getString("adminId"));
                builder.setName(rs.getString("name"));
                builder.setPassword(rs.getString("password"));
                return builder.build();
            } else {
                throw new SQLException("Student Not Found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    @Override
    public List<Admin> getAll() {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        List<Admin> adminList = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(GET_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Admin.AdminBuilder builder = new Admin.AdminBuilder();
                builder.setAdminId(rs.getString("adminId"));
                builder.setName(rs.getString("name"));
                builder.setPassword(rs.getString("password"));
                adminList.add(builder.build());
            }
            return adminList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    public int insert(Admin admin) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, admin.getAdminId());
            stmt.setString(2, admin.getName());
            int result = stmt.executeUpdate();
            System.out.println("Admin Added Successfully");
            return result;
        } catch (Exception e) {
            System.out.println("User with same ID present");
            e.printStackTrace();
            return 0;
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    public int addUser(User user) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        PreparedStatement statement = null;
        try {
            stmt = connection.prepareStatement(INSERT_IN_USER);
            statement = connection.prepareStatement(INSERT_IN_ROLE);

            stmt.setString(1, user.getUserId());
            stmt.setString(2, user.getPassword());
            statement.setString(1, user.getUserId());
            String role = "";
            if (user instanceof Professor) {
                role = "Professor";
            } else if (user instanceof Admin) {
                role = "Admin";
            } else if (user instanceof Student) {
                role = "Student";
            }
            statement.setString(2, role);
            stmt.executeUpdate();
            System.out.println("User Added Successfully");
            statement.executeUpdate();
            System.out.println("Role Added Successfully");
            return 1;
        } catch (Exception e) {
            System.out.println("User with same ID present");
        }
        return 0;
    }


    public int update(String id, Admin admin) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(UPDATE);
            stmt.setString(1, admin.getName());
            stmt.setString(2, admin.getPassword());
            stmt.setString(5, id);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    public int delete(Admin admin) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(DELETE);
            stmt.setString(1, admin.getAdminId());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

}
