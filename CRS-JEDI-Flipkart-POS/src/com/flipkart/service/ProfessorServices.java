package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.constants.Grade;

import java.util.List;

public interface ProfessorServices extends UserServices {

    /**
     *
     * @param studentId
     * @param grade
     * @param courseCode
     * @throws Exception
     */
    public void addGrade(String studentId, String grade, String courseCode) throws Exception;

//    boolean addGrade(RegisteredCourse course, Grade grade) throws Exception;

    /**
     *
     * @param semester
     * @param courseId
     * @return
     * @throws Exception
     */
    public List<Student> viewEnrolledStudents(String semester, String courseId) throws Exception;

    /**
     *
     * @param professorId
     * @return
     * @throws Exception
     */
    public List<Course> viewCoursesTaken(String professorId) throws Exception;

    /**
     *
     * @param professorId
     * @param courseId
     * @param semester
     * @throws Exception
     */
    public void registerForCourse(String professorId,String courseId, String semester) throws Exception;

}
