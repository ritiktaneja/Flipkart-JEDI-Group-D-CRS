package com.flipkart.dao;


import com.flipkart.bean.RegisteredCourse;
import com.flipkart.constants.Grade;
import com.flipkart.utils.DBConnection;
import com.flipkart.constants.sqlconstants.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * GradeCardDao Class
 */
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

    /**
     * this method inserting grade in grade card
     * @param registeredCourse
     * @return
     */
    public int insert(RegisteredCourse registeredCourse) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(GradeCardDaoConstants.INSERT_IN_GRADE);
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

    /**
     * this method updating grade card
     * @param studentId
     * @param grade
     * @param courseCode
     * @return
     */
    public int updateGrade(String studentId, String grade, String courseCode) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(GradeCardDaoConstants.UPDATE_GRADE);
            Grade grade1 = Grade.IN_PROGRESS;
            try{
                grade1 = Grade.valueOf(grade);
            } catch (Exception e) {
                System.out.println("Invalid Grade");
                return 0;
            }
            preparedStatement.setString(1, grade1.toString());

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

    /**
     * this method getting grade from database
     * @param registeredCourseId
     * @return
     */
    public Grade getGrade(String registeredCourseId) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(GradeCardDaoConstants.GET_GRADE);
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
