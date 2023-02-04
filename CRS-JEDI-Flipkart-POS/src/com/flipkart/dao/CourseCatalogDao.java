package com.flipkart.dao;

import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.Professor;
import com.flipkart.utils.DBConnection;

import java.sql.*;
import java.time.temporal.JulianFields;
import java.util.ArrayList;
import java.util.List;

public class CourseCatalogDao {
    private static final String DELETE = "DELETE FROM CourseCatalog WHERE catalogID=?";
    private static final String GET_ALL = "SELECT * FROM CourseCatalog ORDER BY catalogId";
    private static final String GET_BY_ID = "SELECT * FROM CourseCatalog WHERE catalogId=?";
    private static final String INSERT = "insert into coursecatalog(coursecode,catalogid,semester,professorid) values(?,?,?,?);";
    private static final String UPDATE = "UPDATE CourseCatalog SET name=?, password=?, WHERE catalogId=?";
    private static final String GET_ALL_BY_CATALOG_ID = "SELECT * FROM CourseCatalog where catalogId = ?";

    private List<Course> getAllCoursesById(String id) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(GET_ALL_BY_CATALOG_ID);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            List<Course> courses = new ArrayList<>();
            CourseDao courseDao = new CourseDao();
            ProfessorDao professorDao = new ProfessorDao();
            if (rs.next()) {
                while (rs.next()) {
                    String courseId = rs.getString("CourseCode");
                    String professorId = rs.getString("ProfessorID");
                    Course course = courseDao.get(courseId);
                    Professor professor = professorDao.get(professorId);
                    course.setProfessor(professor);
                }
                return courses;
            } else {
                throw new SQLException("Catalog Not Found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    public CourseCatalog get(String id) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(GET_BY_ID);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                List<Course> courses = getAllCoursesById(id);
                CourseCatalog catalog = new CourseCatalog();
                catalog.setCatalogId(id);
                catalog.setCourses(courses);
                return catalog;
            } else {
                System.out.println("No catalog found with this id");
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    public List<CourseCatalog> getAll() {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        List<CourseCatalog> courseCatalogList = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(GET_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CourseCatalog catalog = new CourseCatalog();
                String id = rs.getString("catalogId");
                List<Course> courses = getAllCoursesById(id);
                catalog.setCatalogId(id);
                catalog.setCourses(courses);
                courseCatalogList.add(catalog);
            }
            return courseCatalogList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    public int insert(CourseCatalog courseCatalog) {
        System.out.println("inserting data");
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(INSERT);
            List<Course> courseList = courseCatalog.getCourses();
            int result = 0;
            System.out.println("Adding course catalog id = " + courseCatalog.getCatalogId() + " " + courseList.size());
            for (int i = 0; i < courseList.size(); i++) {
                System.out.println(courseCatalog.getCatalogId());
                System.out.println(courseList.get(i).getCourseCode());


                stmt.setString(1, courseList.get(i).getCourseCode());
                stmt.setString(2, courseCatalog.getCatalogId());
                stmt.setString(3, String.valueOf(1));
                stmt.setString(4, null);

                CourseDao courseDao = new CourseDao();
                courseDao.insert(courseList.get(i));
                System.out.println(INSERT);
                System.out.println(stmt);
                stmt.executeUpdate();
                System.out.println(courseList.get(i) + " inserted");
            }
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
//            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    public int update(String id, CourseCatalog courseCatalog) {
//        Connection connection = DBConnection.getConnection();
//        PreparedStatement stmt = null;
//        try {
//            stmt = connection.prepareStatement(UPDATE);
//            stmt.setString(1, com.flipkart.bean.CourseCatalog.getName());
//            stmt.setString(2, com.flipkart.bean.CourseCatalog.getPassword());
//            stmt.setString(5, id);
//            return stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            DBConnection.closeConnection(connection);
//        }
        return 0;
    }

    public int delete(CourseCatalog CourseCatalog) {
//        Connection connection = DBConnection.getConnection();
//        PreparedStatement stmt = null;
//        try {
//            stmt = connection.prepareStatement(DELETE);
//            stmt.setString(1, com.flipkart.bean.CourseCatalog.getcatalogId());
//            return stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            DBConnection.closeConnection(connection);
//        }
        return 0;
    }

}
