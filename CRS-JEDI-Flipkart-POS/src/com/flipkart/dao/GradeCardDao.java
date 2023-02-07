package com.flipkart.dao;


import com.flipkart.bean.RegisteredCourse;
import com.flipkart.constants.Grade;
import com.flipkart.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GradeCardDao {


    private static GradeCardDao instance = null;

    private GradeCardDao() {

    }

    public static GradeCardDao getInstance() {
        if (instance == null) {
            instance = new GradeCardDao();
        }
        return instance;
    }

    private static final String INSERT_IN_GRADE = "INSERT INTO gradeCard (RegisteredCourseId,grade) VALUES(?,?)";

    private static final String UPDATE_GRADE = "UPDATE gradeCard SET grade = ? WHERE registeredCourseId = ?";

    private static final String GET_GRADE = "SELECT * from gradeCard WHERE registeredCourseId = ?";

    public int insert(RegisteredCourse registeredCourse) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_IN_GRADE);
            statement.setString(1, registeredCourse.getRegisteredCourseId());
            statement.setString(2, "IN_PROGRESS");
            int rs = statement.executeUpdate();
            if (rs >= 1) {
                System.out.println("In progress Grade added");
                return rs;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeStatement(statement);
            DBConnection.closeConnection(connection);
        }
        return 0;
    }

    public int updateGrade(String studentId, String grade, String courseCode) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_GRADE);
            preparedStatement.setString(1, grade);

            RegisteredCoursesDao dao = RegisteredCoursesDao.getInstance();
            String registeredCourseId = dao.getRegisteredCourseId(studentId, courseCode);
            preparedStatement.setString(2, registeredCourseId);

            int rs = preparedStatement.executeUpdate();
            System.out.println("Grade updated Successfully");
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeStatement(preparedStatement);
            DBConnection.closeConnection(connection);
        }
        return 0;
    }

    public Grade getGrade(String registeredCourseId) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(GET_GRADE);
            preparedStatement.setString(1, registeredCourseId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Grade grade = Grade.valueOf(rs.getString("grade"));
                return grade;
            }
            return Grade.NOT_CLEAR;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeStatement(preparedStatement);
            DBConnection.closeConnection(connection);
        }
        return null;
    }


}
