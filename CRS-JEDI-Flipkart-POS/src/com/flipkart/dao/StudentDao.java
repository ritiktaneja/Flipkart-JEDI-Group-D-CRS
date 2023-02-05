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

                ResultSet resultSet = userStatement.executeQuery();
                String password = resultSet.getString("password");
                builder.setPassword(password);

                ResultSet resultSet1 = studentStatement.executeQuery();
                builder.setSemester(resultSet1.getString("semester"));

                int status = resultSet1.getInt("status");
                if (status == 1)
                    builder.setApprovalStatus(true);
                else
                    builder.setApprovalStatus(false);
                
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
        PreparedStatement studentStatement = null;
        PreparedStatement userStatement = null;
        PreparedStatement semesterStatement = null;
        List<Student> studentList = new ArrayList<>();
        try {
            studentStatement = connection.prepareStatement(GET_ALL);
            userStatement = connection.prepareStatement(GET_USER);
            semesterStatement = connection.prepareStatement(GET_SEM_DETAILS);

            ResultSet rs = studentStatement.executeQuery();
            while (rs.next()) {
                String studentId = rs.getString("studentId");

                userStatement.setString(1, studentId);
                semesterStatement.setString(1, studentId);

                Student.StudentBuilder builder = new Student.StudentBuilder();
                builder.setStudentId(studentId);
                builder.setName(rs.getString("studentName"));

                ResultSet resultSet = userStatement.executeQuery();
                String password = resultSet.getString("password");
                builder.setPassword(password);

                ResultSet resultSet1 = studentStatement.executeQuery();
                String semester = resultSet1.getString("semester");
                int status = resultSet1.getInt("status");
                builder.setSemester(semester);
                if (status == 1)
                    builder.setApprovalStatus(true);
                else
                    builder.setApprovalStatus(false);
                studentList.add(builder.build());
            }
            return studentList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
//        Connection connection = DBConnection.getConnection();
//        PreparedStatement stmt = null;
//        try {
//            stmt = connection.prepareStatement(UPDATE);
//            stmt.setString(1, student.getName());
//            stmt.setString(2, student.getPassword());
//            stmt.setString(3, student.getSemester());
//            stmt.setInt(4, student.getDepartment().getValue());
//            stmt.setString(5, id);
//            return stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            DBConnection.closeConnection(connection);
//        }
        return 0;
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