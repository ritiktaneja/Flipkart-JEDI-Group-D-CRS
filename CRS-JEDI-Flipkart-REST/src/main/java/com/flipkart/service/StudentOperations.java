package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.client.CRSApplication;
import com.flipkart.constants.Grade;
import com.flipkart.dao.CourseCatalogDao;
import com.flipkart.dao.RegisteredCoursesDao;
import com.flipkart.dao.StudentDao;
import com.flipkart.data.MockDB;
import com.flipkart.exception.CourseNotRegisteredException;

import java.sql.BatchUpdateException;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class StudentOperations extends UserOperations implements StudentServices {

    /**
     * using this method student can add course which they want
     *
     * @param studentId
     * @param courseCode
     * @return add course status
     * @throws CourseNotRegisteredException
     */
    public boolean addCourse(String studentId, String courseCode) throws CourseNotRegisteredException {
        Student student = null;
        try {
            RegisteredCoursesDao registeredCoursesDao = RegisteredCoursesDao.getInstance();

            RegisteredCourse.RegisteredCourseBuilder builder = new RegisteredCourse.RegisteredCourseBuilder();

            Course course = new Course();
            course.setCourseCode(courseCode);
            builder.setCourse(course);

            Student.StudentBuilder studentBuilder = new Student.StudentBuilder();
            studentBuilder.setStudentId(studentId);
            student = studentBuilder.build();
            builder.setStudent(student);

            builder.setGrade(Grade.IN_PROGRESS);
            RegisteredCourse registeredCourse = builder.build();
            registeredCourse.setRegisteredCourseId(String.valueOf(UUID.randomUUID()));

            if (registeredCoursesDao.insert(registeredCourse) == 1)
                return true;
            else
                return false;
        } catch (Exception e) {
            throw new CourseNotRegisteredException(studentId, courseCode, e.getMessage());
        }

    }

    /**
     * using this method student can drop course which they have taken earlier
     *
     * @param studentId
     * @param courseCode
     * @return drop course status
     */
    public boolean dropCourse(String studentId, String courseCode) {
        RegisteredCoursesDao registeredCoursesDao = RegisteredCoursesDao.getInstance();
        registeredCoursesDao.dropCourse(studentId, courseCode);
        return true;
    }

    /**
     * using this method student can view course which is currently available
     *
     * @param studentId
     * @return
     */
    public List<Course> viewAvailableCourses(String studentId) {
        Student student = StudentDao.getInstance().get(studentId);
        if (student == null) return null;
        CourseCatalog obj = CourseCatalogDao.getInstance().get(CRSApplication.currentSemester.getCurrentSemester());
        return obj.getCourses();
    }

    /**
     * using this method student can view course which they have taken
     *
     * @param studentId
     * @return list of student's registered course
     */
    public List<RegisteredCourse> viewRegisteredCourses(String studentId) {
        RegisteredCoursesDao dao = RegisteredCoursesDao.getInstance();
        return dao.getRegisteredCourse(studentId);
    }

    /**
     * Method to calculate fee for a particular student
     *
     * @param studentId
     * @return fee calculation
     */
    public long calculateFee(String studentId) {
        StudentDao dao = StudentDao.getInstance();
        int courses = dao.NumberOfCoursesTaken(studentId);
//        Student student = MockDB.getStudentFromId(studentId);
        return courses * 100;
    }

    /**
     * Method to get registration status of particular student
     *
     * @param studentId
     * @return registration status
     */
    public boolean getRegistrationStatus(String studentId) {
        return true; // TODO
    }

    public Student getStduentById(String studentId) {
        StudentDao dao = StudentDao.getInstance();
        return dao.get(studentId);
    }

    /**
     * Method to get student by student id
     * @param studentId
     * @return student object
     */
    public Student getStudentByID(String studentId) {
            StudentDao studentDao = StudentDao.getInstance();
            return studentDao.get(studentId);
    }

}
