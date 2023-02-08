package com.flipkart.dao;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.utils.DBConnection;
import com.flipkart.constants.sqlconstants.*;

import java.sql.*;
import java.util.List;

public class CourseDao implements DaoInterface<Course> {

    private static CourseDao instance = null;

    private CourseDao() {
    }

    /**
     * Get the instance of the course
     * @return course
     */
    public static CourseDao getInstance() {
        if(instance == null) {
            instance = new CourseDao();
        }
        return instance;
    }

    /**
     * Get course from the course ID
     * @param id
     * @return course
     */
    @Override
    public Course get(String id) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(CourseDaoConstants.GET_BY_ID);
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
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
    }

    @Override
    public List<Course> getAll() {
        return null;
    }

    /**
     * Insert course the database
     * @param course
     */
    @Override
    public int insert(Course course) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(CourseDaoConstants.INSERT);
            int result = 0;
            stmt.setString(1, course.getCourseCode());
            stmt.setString(2, course.getName());
            stmt.executeUpdate();
            System.out.println("Course added successfully");
            return result;
        } catch (Exception e) {
            System.out.println("Course already exists with this ID");

        } finally {
            DBConnection.closeStatement(stmt);
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

    /**
     * Delete course from the given database
     * @param course
     * @return status
     */
    @Override
    public int delete(Course course) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(CourseDaoConstants.DELETE);
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
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
        return 0;
    }
}
