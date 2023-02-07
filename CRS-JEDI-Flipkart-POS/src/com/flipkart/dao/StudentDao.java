package com.flipkart.dao;

import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.client.CRSApplication;
import com.flipkart.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements DaoInterface<Student> {

    private static final String DELETE = "DELETE FROM student WHERE studentId=?";
    private static final String GET_ALL = "SELECT * FROM student ORDER BY studentId";
    private static final String GET_BY_ID = "SELECT * FROM student WHERE studentId=?";
    private static final String INSERT = "INSERT INTO student(studentId, studentName) VALUES(?, ?)";
    private static final String UPDATE = "UPDATE user SET password=? WHERE userId=?";
    private static final String GET_USER = "SELECT * FROM user where userId = ?";


    private static StudentDao instance = null;

    private StudentDao() {

    }

    public static StudentDao getInstance() {
        if (instance == null) {
            instance = new StudentDao();
        }
        return instance;
    }


    @Override
    public Student get(String studentId) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement studentStatement = null;
        try {
            studentStatement = connection.prepareStatement(GET_BY_ID);

            studentStatement.setString(1, studentId);


            SemesterRegistrationDao registrationDao = SemesterRegistrationDao.getInstance();
            Student student = registrationDao.getRegistrationStatus(studentId);
            if (student != null) {
                Boolean status = student.isApproved();
                Boolean registrationStatus = false;
                if (status) {
                    registrationStatus = true;
                }
                ResultSet rs = studentStatement.executeQuery();
                if (rs.next()) {
                    Student.StudentBuilder builder = new Student.StudentBuilder();
                    builder.setStudentId(studentId);
                    builder.setName(rs.getString("studentName"));
                    builder.setApprovalStatus(registrationStatus);
                    builder.setSemester(student.getSemester());
                    if (status) {
                        builder.setApprovalStatus(true);
                    } else {
                        builder.setApprovalStatus(false);
                    }
                    return builder.build();
                } else {
                    System.out.println("Student not found");
                    return null;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBConnection.closeStatement(studentStatement);
            DBConnection.closeConnection(connection);
        }
        return null;
    }

    @Override
    public List<Student> getAll() {
        Connection connection = DBConnection.getConnection();
        PreparedStatement studentStatement = null;


        List<Student> studentList = new ArrayList<>();
        try {

            studentStatement = connection.prepareStatement(GET_ALL);
            ResultSet rs = studentStatement.executeQuery();
            while (rs.next()) {
                String studentId = rs.getString("studentId");
                String studentNme = rs.getString("studentName");


                Student.StudentBuilder builder = new Student.StudentBuilder();
                builder.setStudentId(studentId);
                builder.setName(rs.getString("studentName"));


                SemesterRegistrationDao registrationDao = SemesterRegistrationDao.getInstance();
                Student student = registrationDao.getRegistrationStatus(studentId);
                if (student != null) {
                    builder.setSemester(student.getSemester());
                    if (student.isApproved()) {
                        builder.setApprovalStatus(true);
                    } else {
                        builder.setApprovalStatus(false);
                    }
                    studentList.add(builder.build());
                }
            }
            return studentList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBConnection.closeStatement(studentStatement);
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

            SemesterRegistrationDao dao = SemesterRegistrationDao.getInstance();
            int rs = dao.insert(student.getStudentId(), CRSApplication.currentSemester);
            if (rs == 1) {
                System.out.println("Semester registration Successfully");
            }
            UserDao userDao = UserDao.getInstance();
            userDao.insert(student);
            int result = stmt.executeUpdate();
            System.out.println("Student added successfully");
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeStatement(stmt);
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
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
    }

    public int NumberOfCoursesTaken(String studentId) {
        List<RegisteredCourse> regList = new ArrayList<>();
        regList = RegisteredCoursesDao.getInstance().getRegisteredCourse(studentId);
        return regList.size();
    }

}


/**
 * CRUD -> Create, Read,
 */