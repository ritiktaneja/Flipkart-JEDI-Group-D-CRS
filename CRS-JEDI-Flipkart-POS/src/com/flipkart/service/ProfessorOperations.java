package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.constants.Grade;
import com.flipkart.data.MockDB;

import java.awt.dnd.DragGestureEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ProfessorOperations extends UserOperations implements ProfessorServices {

    private static volatile ProfessorOperations instance = null;

    /**
     * Default Contructor
     */
    public ProfessorOperations() {

    }

    /**
     *
     */
    public static ProfessorOperations getInstance() {
        if (instance == null) {
            synchronized (ProfessorOperations.class) {
                instance = new ProfessorOperations();
            }
        }
        return instance;
    }

//    private boolean addGrade(RegisteredCourse course, Grade grade) throws Exception {
//        course.setGrade(grade);
//        return true;
//    }

    /**
     * View Enrolled Students
     * @param course
     * @return
     */
    private List<Student> viewEnrolledStudents(Course course) {
        List<Student> students = new ArrayList<>();
        for (Map.Entry<Student, Set<RegisteredCourse>> y : MockDB.registeredCourses.entrySet()) {
            for (RegisteredCourse rc : y.getValue()) {
                if (rc.getCourse().getCourseCode() == course.getCourseCode())
                    students.add(y.getKey());
            }
        }

        return students;
    }

    /**
     *
     * @param professor
     * @return Courses
     */
    private List<Course> viewCoursesTaken(Professor professor) {
        List<Course> list = new ArrayList<>();
        for (CourseCatalog c : MockDB.catalogs) {
            for (Course course : c.getCourses()) {
                if (course.getProfessor() == professor) {
                    list.add(course);
                }
            }
        }
        return list;
    }

    /**
     *
     * @param professor
     * @param course
     * @throws Exception
     */
    private boolean registerForCourse(Professor professor, Course course) throws Exception {
        if (course.getProfessor() == null) {
            course.setProfessor(professor);
            System.out.println(course.getName() + " is Successfully assigned to " + professor.getName());
            return true;
        }
        System.out.println("Error -> " + course.getName() + " is already assigned to " + professor.getName());
        return false;
    }


    /**
     *
     * @param semester
     * @param courseId
     * @return
     */
    public List<Student> viewEnrolledStudents(String semester, String courseId) {
        Course course = MockDB.getCourseFromId(semester, courseId);
        return viewEnrolledStudents(course);
    }

    /**
     *
     * @param professorId
     * @return List of Courses
     */
    @Override
    public List<Course> viewCoursesTaken(String professorId) {
        Professor professor = MockDB.getProfessorFromId(professorId);
        return viewCoursesTaken(professor);
    }

    /**
     * @param professorId
     * @param courseId
     * @param semester
     * @throws Exception
     */
    @Override
    public void registerForCourse(String professorId, String courseId, String semester) throws Exception {
        Professor professor = MockDB.getProfessorFromId(professorId);
        Course course = MockDB.getCourseFromId(semester, courseId);
        registerForCourse(professor, course);
    }

    /**
     * @param studentId
     * @param grade
     * @param courseCode
     */
    public void addGrade(String studentId, String grade, String courseCode) {

        Student student = MockDB.getStudentFromId(studentId);
        for (RegisteredCourse rc : MockDB.registeredCourses.get(student)) {
            if (rc.getCourse().getCourseCode().equals(courseCode)) {
                rc.setGrade(Grade.valueOf(grade));
                break;
            }
        }

    }


}
