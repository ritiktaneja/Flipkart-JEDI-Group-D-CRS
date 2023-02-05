package com.flipkart.dao;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.utils.DBConnection;

import java.sql.*;
import java.util.List;

public class CourseDao implements DaoInterface<Course> {
    private static final String DELETE = "DELETE FROM Course WHERE courseCode=?";
    private static final String GET_ALL = "SELECT * FROM Course ORDER BY courseCode";
    private static final String GET_BY_ID = "SELECT * FROM Course WHERE courseCode=?";
    private static final String INSERT = "INSERT INTO Course(courseCode, courseName) VALUES(?, ?)";
    private static final String UPDATE = "UPDATE Course SET courseName=? WHERE courseCode=?";


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
                course.setCourseCode(id);
                course.setName(rs.getString("CourseName"));
                return course;
            } else {
                System.out.println("Course Not Found");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Course Not Found");
            return null;
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
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(INSERT);
            int result = 0;
            stmt.setString(1, course.getCourseCode());
            stmt.setString(2, course.getName());
            stmt.executeUpdate();
            System.out.println("Course added successfully");
            return result;
        } catch (Exception e) {
            System.out.println("Course already exists with this ID");

        } finally {
            DBConnection.closeConnection(connection);
        }
        return 0;
    }

    @Override
    public int update(String id, Course course) {
//        Connection connection = DBConnection.getConnection();
//        PreparedStatement stmt = null;
//        try {
//            stmt = connection.prepareStatement(UPDATE);
//            int result = 0;
//            stmt.setString(1, course.getName());
//            stmt.setString(2, course.getCourseCode());
//            stmt.executeUpdate();
//            CourseCatalogDao courseCatalogDao = new CourseCatalogDao();
//            String catalogId = courseCatalogDao.getCatalogID(course.getCourseCode());
//            if (catalogId == null) {
//                System.out.println("No Catalog Corresponding to this Course");
//                return 0;
//            }
//            courseCatalogDao.updateCourseInCatalog(course, catalogId);
//            return result;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        } finally {
//            DBConnection.closeConnection(connection);
//        }
        return 0;
    }

    @Override
    public int delete(Course course) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(DELETE);
            stmt.setString(1, course.getCourseCode());
            course = get(course.getCourseCode());
            if (course == null) {
                System.out.println("No Course associated with this ID");
                return 0;
            }
            stmt.executeUpdate();
            System.out.println("Course Deleted Successfully");
            return 1;
        } catch (Exception e) {
            System.out.println("Course Not Deleted");
            e.printStackTrace();
        }
        return 0;
    }
}
