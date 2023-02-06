package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.dao.CourseCatalogDao;
import com.flipkart.dao.GradeCardDao;
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

    public static ProfessorOperations getInstance() {
        if (instance == null) {
            synchronized (ProfessorOperations.class) {
                instance = new ProfessorOperations();
            }
        }
        return instance;
    }


    public List<Student> viewEnrolledStudents(String semester, String courseId) throws CourseNotFoundException {
        try {
            CourseCatalogDao dao = CourseCatalogDao.getInstance();
            return dao.getEnrolledStudents(semester, courseId);
        } catch (Exception e) {
            throw new CourseNotFoundException(semester, courseId);
        }

    }

    @Override
    public List<Course> viewCoursesTaken(String professorId) throws ProfessorNotFoundException {
        try {
            CourseCatalogDao courseCatalogDao = CourseCatalogDao.getInstance();
            return courseCatalogDao.getAssignedCourses(professorId);
        } catch (Exception e) {
            throw new ProfessorNotFoundException(professorId);
        }
    }

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


    public void addGrade(String studentId, String grade, String courseCode) throws GradeNotAddedException {
        try {
            GradeCardDao dao = GradeCardDao.getInstance();
            dao.updateGrade(studentId, grade, courseCode);
        } catch (Exception e) {
            throw new GradeNotAddedException(studentId, courseCode, grade);
        }
    }


}
