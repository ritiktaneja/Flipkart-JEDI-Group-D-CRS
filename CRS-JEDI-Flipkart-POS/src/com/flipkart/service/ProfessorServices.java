package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.constants.Grade;

import java.util.List;

public interface ProfessorServices extends UserServices {

    public void addGrade(String studentId, String grade, String courseCode) throws Exception;

//    boolean addGrade(RegisteredCourse course, Grade grade) throws Exception;

    public List<Student> viewEnrolledStudents(String semester, String courseId) throws Exception;

    public List<Course> viewCoursesTaken(String professorId) throws Exception;

    //public boolean registerForCourse(String professorId,String courseId) throws Exception;

}
