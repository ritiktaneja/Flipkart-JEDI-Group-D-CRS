package com.flipkart.dao;

import com.flipkart.bean.Student;
import com.flipkart.constants.Department;
import com.flipkart.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements DaoInterface<Student> {

    private static final String DELETE = "DELETE FROM student WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM student ORDER BY id";
    private static final String GET_BY_ID = "SELECT * FROM student WHERE id=?";
    private static final String INSERT = "INSERT INTO student(studentId, name, password, batch, department) VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE user SET password=? WHERE userId=?";
    private static final String GET_USER = "SELECT * FROM user where userId = ?";
    private static final String GET_SEM_DETAILS = "SELECT * FROM semesterRegistration where studentId = ?";

    @Override
    public Student get(String studentId) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement studentStatement = null;
        PreparedStatement userStatement = null;
        PreparedStatement semesterStatement = null;
        try {
            studentStatement = connection.prepareStatement(GET_BY_ID);
            userStatement = connection.prepareStatement(GET_USER);
            semesterStatement = connection.prepareStatement(GET_SEM_DETAILS);

            studentStatement.setString(1, studentId);
            userStatement.setString(1, studentId);
            semesterStatement.setString(1, studentId);

            ResultSet rs = studentStatement.executeQuery();
            if (rs.next()) {
                Student.StudentBuilder builder = new Student.StudentBuilder();
                builder.setStudentId(studentId);
                builder.setName(rs.getString("studentName"));

                String password = userStatement.executeQuery().getString("password");
                builder.setPassword(password);

                builder.setSemester(semesterStatement.executeQuery().getString("semester"));
                builder.setApprovalStatus(semesterStatement.executeQuery().getBoolean("status"));

                return builder.build();
            } else {
                System.out.println("Student not found");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    @Override
    public List<Student> getAll() {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        List<Student> studentList = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(GET_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student.StudentBuilder builder = new Student.StudentBuilder();
                builder.setStudentId(rs.getString("studentId"));
                builder.setName(rs.getString("name"));
                builder.setPassword(rs.getString("password"));
                builder.setSemester(rs.getString("batch"));
                builder.setDepartment(Department.values()[rs.getInt("department")]);
                studentList.add(builder.build());
            }
            return studentList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    public int insert(Student student) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, student.getStudentId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getPassword());
            stmt.setString(4, student.getSemester());
            stmt.setInt(5, student.getDepartment().getValue());
            int result = stmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    public int update(String id, Student student) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(UPDATE);
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getPassword());
            stmt.setString(3, student.getSemester());
            stmt.setInt(4, student.getDepartment().getValue());
            stmt.setString(5, id);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    public int delete(Student student) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(DELETE);
            stmt.setString(1, student.getStudentId());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

}


/**
 * CRUD -> Create, Read,
 */