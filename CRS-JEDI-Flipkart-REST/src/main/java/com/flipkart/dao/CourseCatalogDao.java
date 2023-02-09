package com.flipkart.dao;

import com.flipkart.bean.*;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.client.CRSApplication;
import com.flipkart.utils.DBConnection;
import com.flipkart.constants.sqlconstants.*;

import java.sql.*;
import java.time.temporal.JulianFields;
import java.util.ArrayList;
import java.util.List;

/**
 * Course catalog class
 */
public class CourseCatalogDao {


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

    /**
     * this method getting enrolled student from database and putting it into list
     *
     * @param catalogId
     * @param courseId
     * @return
     */
    public List<Student> getEnrolledStudents(String catalogId, String courseId) {
        Connection con = DBConnection.getConnection();
        PreparedStatement pstmt = null;
        List<Student> students = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(CourseCatalogDaoConstants.GET_STUDENTS_BY_COURSE_ID);
            pstmt.setString(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String studentId = rs.getString("StudentId");
                StudentDao studentDao = StudentDao.getInstance();
                students.add(studentDao.get(studentId));
            }
            return students;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);
        }


    }

    /**
     * this method getting courses which  Assigned to professor from database and putting it into list
     *
     * @param professorId
     * @return
     */
    public List<Course> getAssignedCourses(String professorId) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        List<Course> courses = null;
        try {
            statement = connection.prepareStatement(CourseCatalogDaoConstants.GET_COURSES_BY_PROFESSOR_ID);
            statement.setString(1, professorId);
            ResultSet rs = statement.executeQuery();
            courses = new ArrayList<>();
            while (rs.next()) {
                CourseDao dao = CourseDao.getInstance();
                String courseId = rs.getString("courseCode");
                Course course = dao.get(courseId);
                if (course != null) {
                    Professor professor = ProfessorDao.getInstance().get(professorId);
                    if (professor != null)
                        course.setProfessor(professor);
                    courses.add(course);
                }
            }
            return courses;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeStatement(statement);
            DBConnection.closeConnection(connection);
        }

    }

    /**
     * this method getting courses all from database and putting it into list
     *
     * @param id
     * @return
     */

    private List<Course> getAllCoursesByCatalogId(String id) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(CourseCatalogDaoConstants.GET_ALL_BY_CATALOG_ID);
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
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
    }

    /**
     * this method getting individual course catalog from database and putting it into list
     *
     * @param catalogId
     * @return
     */
    public CourseCatalog get(String catalogId) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(CourseCatalogDaoConstants.GET_BY_ID);
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
           throw new RuntimeException(e);
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }

    }

    /**
     * this method getting course catalog from database and putting it into list
     *
     * @return
     */
    public List<CourseCatalog> getAll() {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        List<CourseCatalog> courseCatalogList = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(CourseCatalogDaoConstants.GET_ALL);
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
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
    }

    /**
     * this method getting professor by using course Id
     *
     * @param courseCode
     * @param catalogId
     * @return
     */
    public Professor getProfessorByCourseId(String courseCode, String catalogId) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(CourseCatalogDaoConstants.GET_PROFESSOR);
            stmt.setString(1, CRSApplication.currentSemester.getCurrentSemester());
            stmt.setString(2, courseCode);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ProfessorDao professorDao = ProfessorDao.getInstance();
                return professorDao.get(rs.getString("professorId"));
            }

        } catch (Exception e) {
           throw new RuntimeException(e);
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
        return null;
    }

    /**
     * this method inserting course catalog in database
     *
     * @param courseCatalog
     * @return
     */
    public int insert(CourseCatalog courseCatalog) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(CourseCatalogDaoConstants.INSERT, Statement.RETURN_GENERATED_KEYS);
            List<Course> courses = courseCatalog.getCourses();
            System.out.println(courses.get(0));
            stmt.setString(1, courses.get(0).getCourseCode());
            stmt.setString(2, CRSApplication.currentSemester.getCurrentSemester());
            stmt.setString(3, null);
            CourseDao courseDao = CourseDao.getInstance();
            courseDao.insert(courses.get(0));
            return stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
    }

    /**
     * this method updating course catalog in database
     *
     * @param id
     * @param courseCatalog
     * @return
     */
    public int update(String id, CourseCatalog courseCatalog) {
        return 0;
    }

    /**
     * this method updating course catalog in database
     *
     * @param courseCatalog
     * @return
     */
    public int delete(CourseCatalog courseCatalog) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(CourseCatalogDaoConstants.DELETE);
            stmt.setString(1, courseCatalog.getCatalogId());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
    }

    /**
     * using this method professor can register in courses
     * @param course
     * @param professor
     * @return
     */
    public int registerForCourse(Course course, Professor professor) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(CourseCatalogDaoConstants.GET_PROFESSOR);
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
                    stmt = connection.prepareStatement(CourseCatalogDaoConstants.UPDATE_COURSE_IN_CATALOG);
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
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
        return 0;
    }

    /**
     * this method getting catalog Id from database
     * @param courseId
     * @return
     */
    public String getCatalogID(String courseId) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(CourseCatalogDaoConstants.GET_CATALOG_ID_FROM_COURSE);
            stmt.setString(1, courseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("catalogId");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
        return null;
    }

    /**
     * this method deleting course from course catalog
     * @param catalogId
     * @param courseId
     * @return
     */
    public int deleteCourseFromCatalog(String catalogId, String courseId) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(CourseCatalogDaoConstants.DELETE_COURSE_FROM_CATALOG);
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

            throw new RuntimeException("No catalog found with this ID");

        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
    }


}
