package com.flipkart.dao;

import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.client.CRSApplication;
import com.flipkart.constants.Department;
import com.flipkart.utils.DBConnection;
import com.flipkart.constants.sqlconstants.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentDao Class
 */
public class StudentDao implements DaoInterface<Student> {


    private static StudentDao instance = null;

    /**
     * Default constructor for student dao
     */
    private StudentDao() {

    }

    /**
     * Get instance
     * @return instance
     */
    public static StudentDao getInstance() {
        if (instance == null) {
            instance = new StudentDao();
        }
        return instance;
    }

    /**
     * Get Student object from student id
     * @param studentId
     * @return
     */
    @Override
    public Student get(String studentId) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement studentStatement = null;
        try {
            studentStatement = connection.prepareStatement(StudentDaoConstants.GET_BY_ID);

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
                    builder.setPassword("REDACTED");
                    builder.setName(rs.getString("studentName"));
                    builder.setDepartment(Department.valueOf(rs.getString("Department")));
                    builder.setApprovalStatus(registrationStatus);
                    builder.setSemester(student.getSemester());
                    if (status) {
                        builder.setApprovalStatus(true);
                    } else {
                        builder.setApprovalStatus(false);
                    }
                    return builder.build();
                } else {

                    return null;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Student not found");
        } finally {
            DBConnection.closeStatement(studentStatement);
            DBConnection.closeConnection(connection);
        }
        return null;

    }

    /**
     * Get all student registered in the current semester from the database
     * @return list of student
     */
    @Override
    public List<Student> getAll() {
        Connection connection = DBConnection.getConnection();
        PreparedStatement studentStatement = null;


        List<Student> studentList = new ArrayList<>();
        try {

            studentStatement = connection.prepareStatement(StudentDaoConstants.GET_ALL);
            ResultSet rs = studentStatement.executeQuery();
            while (rs.next()) {
                String studentId = rs.getString("studentId");
                String studentNme = rs.getString("studentName");


                Student.StudentBuilder builder = new Student.StudentBuilder();
                builder.setStudentId(studentId);
                builder.setPassword("REDACTED");
                builder.setDepartment(Department.valueOf(rs.getString("Department")));
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
           throw new RuntimeException(e.getMessage());
        } finally {
            DBConnection.closeStatement(studentStatement);
            DBConnection.closeConnection(connection);
        }
    }

    /**
     * Insert student in the database
     * @param student
     * @return status
     */
    public int insert(Student student) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement(StudentDaoConstants.INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, student.getStudentId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getDepartment().toString());
            SemesterRegistrationDao dao = SemesterRegistrationDao.getInstance();
            int rs = dao.insert(student.getStudentId(), CRSApplication.currentSemester.getCurrentSemester());
            if (rs == 1) {
                System.out.println("Semester registration Successfully");
            }
            UserDao userDao = UserDao.getInstance();
            userDao.insert(student);
            int result = stmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
    }

    /**
     * Update student in the database
     * @param id
     * @param student
     * @return
     */
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

    /**
     * Delete student from the database
     * @param student
     * @return status
     */
    public int delete(Student student) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(StudentDaoConstants.DELETE);
            stmt.setString(1, student.getStudentId());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
    }

    /**
     * Return number of courses taken by the given student
     * @param studentId
     * @return number of course
     */
    public int NumberOfCoursesTaken(String studentId) {
        List<RegisteredCourse> regList = new ArrayList<>();
        regList = RegisteredCoursesDao.getInstance().getRegisteredCourse(studentId);
        return regList.size();
    }

}


/**
 * CRUD -> Create, Read,
 */