package com.flipkart.dao;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CourseDao implements DaoInterface<Course> {
    private static final String DELETE = "DELETE FROM CourseCatalog WHERE courseId=?";
    private static final String GET_ALL = "SELECT * FROM CourseCatalog ORDER BY courseId";
    private static final String GET_BY_ID = "SELECT * FROM CourseCatalog WHERE courseId=?";
    private static final String INSERT = "INSERT INTO CourseCatalog(courseId, name, password) VALUES(?, ?, ?)";
    private static final String UPDATE = "UPDATE professor SET name=?, password=?, WHERE courseId=?";


    @Override
    public Course get(String id) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(GET_BY_ID);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Course course = new Course();
                course.setProfessor(null);
                course.setName(rs.getString("CourseName"));
                return course;
            } else {
                throw new SQLException("Student Not Found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    @Override
    public List<Course> getAll() {
        return null;
    }

    @Override
    public int insert(Course course) {
        return 0;
    }

    @Override
    public int update(String id, Course course) {
        return 0;
    }

    @Override
    public int delete(Course course) {
        return 0;
    }
}
