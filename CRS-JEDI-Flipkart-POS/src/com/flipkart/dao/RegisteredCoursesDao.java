package com.flipkart.dao;

import com.flipkart.bean.*;
import com.flipkart.client.CRSApplication;
import com.flipkart.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegisteredCoursesDao implements DaoInterface<RegisteredCourse> {

    private static final String INSERT = "INSERT INTO RegisteredCourses (RegisteredCourseID,studentId, courseCode,timeadded) VALUES(?,?,?,?)";


    private static final String GET_ALL_OBJECTS = "SELECT * FROM registeredCourses WHERE studentId = ?";

    private static final String DELETE = "DELETE FROM registeredCourses WHERE studentId = ? and CourseCode = ?";

    private static final String GET_STUDENTS_IN_COURSE = "SELECT * FROM registeredCourses WHERE courseCode  = ?";
    private static final String GET = "SELECT * FROM registeredCourses WHERE studentId = ? AND courseCode  = ?";

    private static RegisteredCoursesDao instance = null;


    private RegisteredCoursesDao() {

    }

    public List<RegisteredCourse> getRegisteredCourse(String studentId) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        List<RegisteredCourse> registeredCourses = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(GET_ALL_OBJECTS);
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            GradeCardDao gradeCardDao = GradeCardDao.getInstance();
            while (rs.next()) {
                String registeredCourseId = rs.getString("RegisteredCourseID");
                RegisteredCourse.RegisteredCourseBuilder builder = new RegisteredCourse.RegisteredCourseBuilder();
                Course course = CourseDao.getInstance().get(rs.getString("CourseCode"));

                CourseCatalogDao courseCatalogDao = CourseCatalogDao.getInstance();
                Professor professor = courseCatalogDao.getProfessorByCourseId(rs.getString("courseCode"), CRSApplication.currentSemester.getCurrentSemester());
                if (professor != null) {
                    course.setProfessor(professor);
                }
                builder.setCourse(course);
                Student student = StudentDao.getInstance().get(rs.getString("StudentId"));
                builder.setStudent(student);
                builder.setGrade(gradeCardDao.getGrade(registeredCourseId));
                registeredCourses.add(builder.build());
            }
            return registeredCourses;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
    }

    public static RegisteredCoursesDao getInstance() {
        if (instance == null) {
            instance = new RegisteredCoursesDao();
        }
        return instance;
    }

    public String getRegisteredCourseId(String studentId, String courseId) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(GET);
            statement.setString(1, studentId);
            statement.setString(2, courseId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String registeredCourseId = rs.getString("RegisteredCourseId");
                return registeredCourseId;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeStatement(statement);
            DBConnection.closeConnection(connection);
        }
        return null;
    }

    @Override
    public RegisteredCourse get(String id) {
        return null;
    }

    @Override
    public List<RegisteredCourse> getAll() {
        return null;
    }

    @Override
    public int insert(RegisteredCourse registeredCourse) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        PreparedStatement gradeStatement = null;
        try {
            statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            CourseDao courseDao = CourseDao.getInstance();
            Course course = courseDao.get(registeredCourse.getCourse().getCourseCode());
            if (course == null) {
                System.out.println("No course associated with this ID");
                return 0;
            }
            System.out.println(registeredCourse.getRegisteredCourseId());

            statement.setString(1, registeredCourse.getRegisteredCourseId());
            statement.setString(2, registeredCourse.getStudent().getStudentId());
            statement.setString(3, registeredCourse.getCourse().getCourseCode());
            statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

            GradeCardDao gradeCardDao = GradeCardDao.getInstance();
            gradeCardDao.insert(registeredCourse);

            int rs = statement.executeUpdate();
            System.out.println("Grade Inserted Successfully");
            System.out.println("Student Registered for course Successfully");
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeStatement(gradeStatement);
            DBConnection.closeStatement(statement);
            DBConnection.closeConnection(connection);
        }
        return 0;
    }

    @Override
    public int update(String studentId, RegisteredCourse registeredCourse) {
        // NOT REQUIRED
        return 0;
    }


    @Override
    public int delete(RegisteredCourse registeredCourse) {
        // NOT REQUIRED
        return 0;
    }

    public int dropCourse(String studentId, String courseCode) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setString(1, studentId);
            preparedStatement.setString(2, courseCode);
            int rs = preparedStatement.executeUpdate();
            if (rs == 0) {
                System.out.println("Incorrect student or Course code");
            } else {
                System.out.println("Course Dropped Successfully");
            }
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeStatement(preparedStatement);
            DBConnection.closeConnection(connection);
        }
        return 0;
    }
}
