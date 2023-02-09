package com.flipkart.dao;

import com.flipkart.bean.*;
import com.flipkart.client.CRSApplication;
import com.flipkart.constants.Department;
import com.flipkart.constants.Designation;
import com.flipkart.service.StudentServices;
import com.flipkart.utils.DBConnection;
import com.flipkart.constants.sqlconstants.*;

import java.security.PublicKey;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * AdminDao Class
 */

public class AdminDao implements DaoInterface<Admin> {

    private static AdminDao instance = null;

    private AdminDao() {

    }

    /**
     * this method is updating semester info
     * @param semester
     * @return
     */
    public int updateSemester(String semester) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(AdminDaoConstants.SET_SEMESTER);
            statement.setString(1, semester);
            int rs = statement.executeUpdate();
            CRSApplication.currentSemester.setCurrentSemester(semester);
            return rs;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DBConnection.closeStatement(statement);
            DBConnection.closeConnection(connection);
        }

    }

    /**
     * getting admin instance
     * @return
     */
    public static AdminDao getInstance() {
        if (instance == null) {
            instance = new AdminDao();
        }
        return instance;
    }

    /**
     * this method use for getting info about current semester
     * @return
     */
    public Semester getCurrentSemester() {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(AdminDaoConstants.GET_CURRENT_SEM);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Semester semester = new Semester();
                semester.setCurrentSemester(rs.getString("currentSemester"));
                semester.setRegistrationOpeningStatus(rs.getInt("RegistrationOpened"));
                return semester;
            }
        } catch (Exception e) {
           throw new RuntimeException("Unable to get current semester");
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
        return null;
    }

    /**
     * this method use for approve student request for registration
     * @param studentID
     * @return
     */
    public int approveStudent(String studentID) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(AdminDaoConstants.APPROVE_STUDENT);
            stmt.setInt(1, 1);
            stmt.setString(2, studentID);
            return stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("No Student found with this ID");

        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }

    }

    /**
     * this method creating admin
     * @param id
     * @return
     */
    @Override
    public Admin get(String id) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(AdminDaoConstants.GET_BY_ID);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Admin.AdminBuilder builder = new Admin.AdminBuilder();
                builder.setAdminId(rs.getString("adminId"));
                builder.setName(rs.getString("adminName"));
                return builder.build();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Admin not found");
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
        return null;
    }

    /**
     * this method fetching admin from database and insert into list
     * @return
     */
    @Override
    public List<Admin> getAll() {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        List<Admin> adminList = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(AdminDaoConstants.GET_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Admin.AdminBuilder builder = new Admin.AdminBuilder();
                builder.setAdminId(rs.getString("adminId"));
                builder.setName(rs.getString("adminName"));
                adminList.add(builder.build());
            }
            return adminList;

        } catch (SQLException e) {
            throw new RuntimeException("Unable to fetch all records");
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
    }

    /**
     * this method inserting admin into database
     * @param admin
     * @return
     */
    public int insert(Admin admin) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(AdminDaoConstants.INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, admin.getAdminId());
            stmt.setString(2, admin.getName());
            stmt.setString(3, CRSApplication.currentSemester.getCurrentSemester());
            int result = stmt.executeUpdate();
            System.out.println("Admin Added Successfully");
            return result;
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage());

        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
    }

    /**
     * this method updating admin into database
     * @param id
     * @param admin
     * @return
     */
    public int update(String id, Admin admin) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(AdminDaoConstants.UPDATE);
            stmt.setString(1, admin.getName());
            stmt.setString(2, admin.getPassword());
            stmt.setString(5, id);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
    }

    /**
     * this method deleting admin from database
     * @param admin
     * @return
     */
    public int delete(Admin admin) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(AdminDaoConstants.DELETE);
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
