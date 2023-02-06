package com.flipkart.dao;

import com.flipkart.bean.Student;
import com.flipkart.client.CRSApplication;
import com.flipkart.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SemesterRegistrationDao {

    private static SemesterRegistrationDao instance = null;

    private static final String GET_STATUS = "SELECT * FROM semesterRegistration WHERE studentId = ? and semester = ?";
    private static final String INSERT = "INSERT INTO semesterRegistration(studentId, semester,status) VALUES(?,?,?)";
    private static final String GET = "SELECT * FROM semesterRegistration WHERE studentId = ? AND semester = ?";

    private SemesterRegistrationDao() {

    }

    public static SemesterRegistrationDao getInstance() {
        if (instance == null) {
            instance = new SemesterRegistrationDao();
        }
        return instance;
    }

    public Student getRegistrationStatus(String studentId) {
        String semester = CRSApplication.currentSemester;
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(GET_STATUS);
            statement.setString(1, studentId);
            statement.setString(2, semester);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int status = rs.getInt("status");
                String studentSemester = rs.getString("semester");
                Student.StudentBuilder builder = new Student.StudentBuilder();
                builder.setStudentId(studentId);
                builder.setApprovalStatus(status != 0);
                builder.setSemester(studentSemester);
                return builder.build();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insert(String studentId, String semester) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT);

            Student student = get(studentId, semester);
            if (student == null) {
                statement.setString(1, studentId);
                statement.setString(2, semester);
                statement.setInt(3, 0);
                int rs = statement.executeUpdate();
                return rs;
            } else {
                System.out.println("Student already registered for this semester");
                return 0;
            }
        } catch (Exception e) {
            System.out.println("Failed to register for this semester");
            e.printStackTrace();
        }
        return 0;
    }

    public Student get(String studentId, String semester) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(GET);
            statement.setString(1, studentId);
            statement.setString(2, semester);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Student.StudentBuilder builder = new Student.StudentBuilder();
                builder.setStudentId(studentId);
                return builder.build();
            }
            return null;
        } catch (Exception e) {
            System.out.println("Student with this id and semester exists");
        }
        return null;
    }
}
