package com.flipkart.dao;

import com.flipkart.bean.*;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.client.CRSApplication;
import com.flipkart.utils.DBConnection;

import java.sql.*;
import java.time.temporal.JulianFields;
import java.util.ArrayList;
import java.util.List;

public class CourseCatalogDao {
    private static final String DELETE = "DELETE FROM CourseCatalog WHERE catalogID=?";
    private static final String GET_ALL = "SELECT * FROM CourseCatalog ORDER BY catalogId";
    private static final String GET_BY_ID = "SELECT * FROM courseCatalog WHERE catalogId=?";
    private static final String INSERT = "insert into coursecatalog(coursecode,catalogid,professorid) values(?,?,?);";
    private static final String UPDATE = "UPDATE CourseCatalog SET name=?, password=?, WHERE catalogId=?";
    private static final String GET_ALL_BY_CATALOG_ID = "SELECT * FROM CourseCatalog where catalogId = ?";
    private static final String UPDATE_COURSE_IN_CATALOG = "UPDATE CourseCatalog SET professorID = ? WHERE courseCode =? and CatalogID = ?";
    private static final String GET_CATALOG_ID_FROM_COURSE = "SELECT catalogID from CourseCatalog where courseCode = ?";
    private static final String DELETE_COURSE_FROM_CATALOG = "DELETE FROM courseCatalog where catalogId=? and courseCode=?";
    private static final String GET_PROFESSOR = "SELECT professorID FROM CourseCatalog WHERE catalogId=? AND courseCode=?";
    private static final String GET_COURSES_BY_PROFESSOR_ID = "SELECT * FROM courseCatalog WHERE professorId = ? ";
    private static final String GET_STUDENTS_BY_COURSE_ID = "SELECT * FROM RegisteredCourses WHERE CourseCode = ?";
    private PreparedStatement stmt;

    private static CourseCatalogDao instance = null;

    private CourseCatalogDao() {

    }

    public static CourseCatalogDao getInstance() {
        if (instance == null) {
            instance = new CourseCatalogDao();
        }
        return instance;
    }


    public List<Student> getEnrolledStudents(String catalogId, String courseId) {
        Connection con = DBConnection.getConnection();
        PreparedStatement pstmt = null;
        List<Student> students = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(GET_STUDENTS_BY_COURSE_ID);
            pstmt.setString(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String studentId = rs.getString("StudentId");
                StudentDao studentDao = StudentDao.getInstance();
                students.add(studentDao.get(studentId));
            }
            return students;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<Course> getAssignedCourses(String professorId) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        List<Course> courses = null;
        try {
            statement = connection.prepareStatement(GET_COURSES_BY_PROFESSOR_ID);
            statement.setString(1, professorId);
            ResultSet rs = statement.executeQuery();
            courses = new ArrayList<>();
            while (rs.next()) {
                CourseDao dao = CourseDao.getInstance();
                String courseId = rs.getString("courseCode");
                Course course = dao.get(courseId);
                if (course != null) {
                    Professor professor = ProfessorDao.getInstance().get(professorId);
                    if(professor != null)
                        course.setProfessor(professor);
                    courses.add(course);
                }
            }
            return courses;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection);
        }
        return null;
    }

    private List<Course> getAllCoursesByCatalogId(String id) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(GET_ALL_BY_CATALOG_ID);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            List<Course> courses = new ArrayList<>();
            CourseDao courseDao = CourseDao.getInstance();
            ProfessorDao professorDao = ProfessorDao.getInstance();
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

            CourseDao courseDao = CourseDao.getInstance();
            ProfessorDao professorDao = ProfessorDao.getInstance();

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
            stmt.setString(2, CRSApplication.currentSemester);
            stmt.setString(3, null);
            CourseDao courseDao = CourseDao.getInstance();
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

    public int registerForCourse(Course course, Professor professor) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(GET_PROFESSOR);
            String catalogId = getCatalogID(course.getCourseCode());
            if (catalogId == null) {
                System.out.println("This course does not belongs to any catalog");
                return 0;
            }
            stmt.setString(1, catalogId);
            stmt.setString(2, course.getCourseCode());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String assignedProfessorId = rs.getString("professorId");
                if (assignedProfessorId == null) {
                    stmt = connection.prepareStatement(UPDATE_COURSE_IN_CATALOG);
                    stmt.setString(1, professor.getFacultyId());
                    stmt.setString(2, course.getCourseCode());
                    stmt.setString(3, catalogId);
                    System.out.println("Course Successfully assigned to you");
                    return stmt.executeUpdate();
                } else if (professor.getFacultyId().equals(assignedProfessorId)) {
                    System.out.println("This course is already assigned to you");
                    return 0;
                } else {
                    System.out.println("This course is already assigned to someone");
                    return 0;
                }
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
            if (rs.next()) {
                return rs.getString("catalogId");
            }
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
            CourseDao courseDao = CourseDao.getInstance();

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
