package com.flipkart.dao;

import com.flipkart.bean.*;
import com.flipkart.client.CRSApplication;
import com.flipkart.constants.Department;
import com.flipkart.constants.Designation;
import com.flipkart.service.StudentServices;
import com.flipkart.utils.DBConnection;

import java.security.PublicKey;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao implements DaoInterface<Admin> {

    private static final String DELETE = "DELETE FROM admin WHERE adminId=?";
    private static final String GET_ALL = "SELECT * FROM admin ORDER BY adminId";
    private static final String GET_BY_ID = "SELECT * FROM admin WHERE adminId=?";
    private static final String INSERT = "INSERT INTO admin(adminId, adminName,currentSemester) VALUES(?, ?,?)";
    private static final String UPDATE = "UPDATE professor SET professorName=?, password=?, WHERE  Id=?";

    private static final String APPROVE_STUDENT = "UPDATE semesterRegistration SET status= ? where studentId = ?";
    private static final String GET_CURRENT_SEM = "SELECT * FROM admin";

    private static final String SET_SEMESTER = "UPDATE admin SET currentSemester = ? ";


    private static AdminDao instance = null;

    private AdminDao() {

    }

    public int updateSemester(String semester) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SET_SEMESTER);
            statement.setString(1, semester);
            int rs = statement.executeUpdate();
            CRSApplication.currentSemester.setCurrentSemester(semester);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeStatement(statement);
            DBConnection.closeConnection(connection);
        }
        return 0;
    }

    public static AdminDao getInstance() {
        if (instance == null) {
            instance = new AdminDao();
        }
        return instance;
    }

    public Semester getCurrentSemester() {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(GET_CURRENT_SEM);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Semester semester = new Semester();
                semester.setCurrentSemester(rs.getString("currentSemester"));
                semester.setRegistrationOpeningStatus(rs.getInt("RegistrationOpened"));
                return semester;
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
        return null;
    }

    public int approveStudent(String studentID) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(APPROVE_STUDENT);
            stmt.setInt(1, 1);
            stmt.setString(2, studentID);
            return stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("No Student found with this ID");
            e.printStackTrace();
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
        return 0;
    }

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
                builder.setName(rs.getString("adminName"));
                return builder.build();
            }
        } catch (SQLException e) {
            System.out.println("Admin not found");
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
        return null;
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
                builder.setName(rs.getString("adminName"));
                adminList.add(builder.build());
            }
            return adminList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeStatement(stmt);
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
            stmt.setString(3, CRSApplication.currentSemester.getCurrentSemester());
            int result = stmt.executeUpdate();
            System.out.println("Admin Added Successfully");
            return result;
        } catch (Exception e) {
            System.out.println("User with same ID present");
            e.printStackTrace();
            return 0;
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
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
            DBConnection.closeStatement(stmt);
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
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
    }

}
