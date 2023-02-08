package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.dao.CourseCatalogDao;
import com.flipkart.dao.GradeCardDao;
import com.flipkart.dao.ProfessorDao;
import com.flipkart.dao.RegisteredCoursesDao;
import com.flipkart.exception.CourseNotAssignedToProfessorException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.GradeNotAddedException;
import com.flipkart.exception.ProfessorNotFoundException;

import java.util.List;


public class ProfessorOperations extends UserOperations implements ProfessorServices {

    private static volatile ProfessorOperations instance = null;

    public ProfessorOperations() {

    }

    /**
     * creating new instance of professor
     *
     * @return
     */
    public static ProfessorOperations getInstance() {
        if (instance == null) {
            synchronized (ProfessorOperations.class) {
                instance = new ProfessorOperations();
            }
        }
        return instance;
    }

    /**
     * using this method professor can view student which is part of his course
     *
     * @param semester
     * @param courseId
     * @return
     * @throws CourseNotFoundException
     */

    public List<Student> viewEnrolledStudents(String semester, String courseId) throws CourseNotFoundException {
        try {
            CourseCatalogDao dao = CourseCatalogDao.getInstance();
            return dao.getEnrolledStudents(semester, courseId);
        } catch (Exception e) {
            throw new CourseNotFoundException(semester, courseId);
        }

    }

    /**
     * using this method professor can view course which taken by him
     *
     * @param professorId
     * @return
     * @throws ProfessorNotFoundException
     */
    @Override
    public List<Course> viewCoursesTaken(String professorId) throws ProfessorNotFoundException {
        try {
            CourseCatalogDao courseCatalogDao = CourseCatalogDao.getInstance();
            return courseCatalogDao.getAssignedCourses(professorId);
        } catch (Exception e) {
            throw new ProfessorNotFoundException(professorId);
        }
    }

    /**
     * using this method professor can register for course he want to take
     *
     * @param professorId
     * @param courseId
     * @param semester
     * @throws CourseNotAssignedToProfessorException
     */
    @Override
    public void registerForCourse(String professorId, String courseId, String semester) throws CourseNotAssignedToProfessorException {
        try {
            Professor.ProfessorBuilder builder = new Professor.ProfessorBuilder();
            builder.setFacultyId(professorId);
            Course course = new Course();
            course.setCourseCode(courseId);
            CourseCatalogDao dao = CourseCatalogDao.getInstance();
            dao.registerForCourse(course, builder.build());
        } catch (Exception e) {
            throw new CourseNotAssignedToProfessorException(semester, courseId, professorId);
        }
    }

    /**
     * using this method professor can assign grade to student which taken his course
     *
     * @param studentId
     * @param grade
     * @param courseCode
     * @throws GradeNotAddedException
     */
    public void addGrade(String studentId, String grade, String courseCode) throws GradeNotAddedException {
        try {
            GradeCardDao dao = GradeCardDao.getInstance();
            dao.updateGrade(studentId, grade, courseCode);
        } catch (Exception e) {
            throw new GradeNotAddedException(studentId, courseCode, grade);
        }
    }

    public Professor getProfessorById(String professorId) {
        ProfessorDao professorDao = ProfessorDao.getInstance();
        return professorDao.get(professorId);
    }

}
