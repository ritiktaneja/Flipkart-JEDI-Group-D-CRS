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
    private static final String UPDATE_COURSE_IN_CATALOG = "UPDATE CourseCatalog SET professorID = ? WHERE courseCode =? and CatalogID = ?";
    private static final String GET_CATALOG_ID_FROM_COURSE = "SELECT catalogID from CourseCatalog where courseCourse = ?";

    private static final String DELETE_COURSE_FROM_CATALOG = "DELETE FROM courseCatalog where catalogId=? and courseCode=?";
    private static final String GET_PROFESSOR = "SELECT professorID FROM CourseCatalog WHERE catalogId=? AND courseCode=?";

    private PreparedStatement stmt;

    private List<Course> getAllCoursesByCatalogId(String id) {
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

    public CourseCatalog get(String catalogId) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(GET_BY_ID);
            stmt.setString(1, catalogId);
            ResultSet rs = stmt.executeQuery();
            CourseCatalog catalog = null;
            catalog = new CourseCatalog();
            catalog.setCatalogId(catalogId);
            CourseDao courseDao = new CourseDao();
            ProfessorDao professorDao = new ProfessorDao();
            while (rs.next()) {
                String courseId = rs.getString("CourseCode");
                String professorId = rs.getString("ProfessorID");
                Course course = courseDao.get(courseId);

                Professor professor = professorDao.get(professorId);
                if (professor != null)
                    course.setProfessor(professor);
                catalog.addCourse(course);
            }
            return catalog;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection);
        }
        return null;
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
                List<Course> courses = getAllCoursesByCatalogId(id);
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
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            List<Course> courses = courseCatalog.getCourses();
            System.out.println(courses.get(0));
            stmt.setString(1, courses.get(0).getCourseCode());
            stmt.setString(2, courseCatalog.getCatalogId());
            stmt.setString(3, String.valueOf(1));
            stmt.setString(4, null);
            CourseDao courseDao = new CourseDao();
            courseDao.insert(courses.get(0));
            return stmt.executeUpdate();
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

    public int delete(CourseCatalog courseCatalog) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(DELETE);
            stmt.setString(1, courseCatalog.getCatalogId());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    public int registerForCourse(Course course, String professorId) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(GET_PROFESSOR);
            String catalogId = getCatalogID(course.getCourseCode());
            if (catalogId == null) {
                System.out.println("This course do not belongs to any catalog");
                return 0;
            }
            stmt.setString(1, catalogId);
            stmt.setString(2, course.getCourseCode());
            ResultSet rs = stmt.executeQuery();
            String assignedProfessorId = rs.getString("professorId");
            if (assignedProfessorId == null) {
                stmt = connection.prepareStatement(UPDATE_COURSE_IN_CATALOG);
                stmt.setString(1, professorId);
                stmt.setString(2, course.getCourseCode());
                stmt.setString(2, catalogId);
                System.out.println("Course Successfully assigned to you");
                return stmt.executeUpdate();
            } else if (professorId.equals(assignedProfessorId)) {
                System.out.println("This course is already assigned to you");
                return 0;
            } else {
                System.out.println("This course is already assigned to someone");
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getCatalogID(String courseId) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(GET_CATALOG_ID_FROM_COURSE);
            stmt.setString(1, courseId);
            ResultSet rs = stmt.executeQuery();
            return rs.getString("catalogid");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int deleteCourseFromCatalog(String catalogId, String courseId) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(DELETE_COURSE_FROM_CATALOG);
            stmt.setString(1, catalogId);
            stmt.setString(2, courseId);
            CourseDao courseDao = new CourseDao();

            Course course = new Course();
            course.setCourseCode(courseId);
            int rs = courseDao.delete(course);
            if (rs == 1) {
                CourseCatalog catalog = get(catalogId);
                if (catalog == null) {
                    System.out.println("No catalog associated with this ID");
                    return 0;
                }
                stmt.executeUpdate();
                System.out.println("Course Removed from catalog Successfully");
                return 1;
            }
            System.out.println("Course Not found in this catalog");
            return 0;
        } catch (Exception e) {
            System.out.println("No catalog found with this ID");
            e.printStackTrace();
            return 0;
        }
    }


}
