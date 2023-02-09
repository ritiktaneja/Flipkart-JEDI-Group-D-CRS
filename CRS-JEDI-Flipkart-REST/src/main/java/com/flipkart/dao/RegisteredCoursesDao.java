package com.flipkart.dao;

import com.flipkart.bean.*;
import com.flipkart.client.CRSApplication;
import com.flipkart.utils.DBConnection;
import com.flipkart.constants.sqlconstants.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegisteredCoursesDao implements DaoInterface<RegisteredCourse> {

    private static RegisteredCoursesDao instance = null;

    /**
     * Default constructor
     */
    private RegisteredCoursesDao() {

    }

    /**
     * Get list of registered course corresponding to particular student id
     *
     * @param studentId
     * @return list of registered course
     */
    public List<RegisteredCourse> getRegisteredCourse(String studentId) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        List<RegisteredCourse> registeredCourses = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(RegisteredCoursesDaoConstants.GET_ALL_OBJECTS);
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


    /**
     * Get instance
     *
     * @return instance
     */
    public static RegisteredCoursesDao getInstance() {
        if (instance == null) {
            instance = new RegisteredCoursesDao();
        }
        return instance;
    }

    /**
     * Get unique registered course id for corresponding student with student id
     *
     * @param studentId
     * @param courseId
     * @return get registered course id
     */
    public String getRegisteredCourseId(String studentId, String courseId) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(RegisteredCoursesDaoConstants.GET);
            statement.setString(1, studentId);
            statement.setString(2, courseId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String registeredCourseId = rs.getString("RegisteredCourseId");
                return registeredCourseId;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeStatement(statement);
            DBConnection.closeConnection(connection);
        }
    }

    @Override
    public RegisteredCourse get(String id) {
        return null;
    }

    @Override
    public List<RegisteredCourse> getAll() {
        return null;
    }

    /**
     * Insert registered course in the given database
     *
     * @param registeredCourse
     * @return status
     */
    @Override
    public int insert(RegisteredCourse registeredCourse) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(RegisteredCoursesDaoConstants.INSERT, Statement.RETURN_GENERATED_KEYS);
            CourseDao courseDao = CourseDao.getInstance();
            Course course = courseDao.get(registeredCourse.getCourse().getCourseCode());
            if (course != null) {
                String regCourseId = getRegisteredCourseId(registeredCourse.getStudent().getStudentId(), registeredCourse.getCourse().getCourseCode());
                if (regCourseId == null) {
                    statement.setString(1, registeredCourse.getRegisteredCourseId());
                    statement.setString(2, registeredCourse.getStudent().getStudentId());
                    statement.setString(3, registeredCourse.getCourse().getCourseCode());
                    statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                    GradeCardDao gradeCardDao = GradeCardDao.getInstance();
                    gradeCardDao.insert(registeredCourse);
                    int rs = statement.executeUpdate();
                    return rs;
                } else {
                    throw new RuntimeException("Course not registered");
                }
            } else {
                throw new RuntimeException("Course Doesn't exist");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

            DBConnection.closeStatement(statement);
            DBConnection.closeConnection(connection);
        }

    }


    /**
     * Update course method
     *
     * @param studentId
     * @param registeredCourse
     * @return status
     */
    @Override
    public int update(String studentId, RegisteredCourse registeredCourse) {
        // NOT REQUIRED
        return 0;
    }


    /**
     * Delete course method
     *
     * @param registeredCourse
     * @return status
     */
    @Override
    public int delete(RegisteredCourse registeredCourse) {
        // NOT REQUIRED
        return 0;
    }

    /**
     * Drop particular course for a student
     *
     * @param studentId
     * @param courseCode
     * @return status
     */
    public int dropCourse(String studentId, String courseCode) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(RegisteredCoursesDaoConstants.DELETE);
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
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeStatement(preparedStatement);
            DBConnection.closeConnection(connection);
        }

    }
}
