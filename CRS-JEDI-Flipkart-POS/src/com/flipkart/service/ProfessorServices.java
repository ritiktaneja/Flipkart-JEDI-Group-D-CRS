package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.constants.Grade;
import com.flipkart.exception.CourseNotAssignedToProfessorException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.GradeNotAddedException;
import com.flipkart.exception.ProfessorNotFoundException;

import java.util.List;

public interface ProfessorServices extends UserServices {

    public void addGrade(String studentId, String grade, String courseCode) throws GradeNotAddedException;

//    boolean addGrade(RegisteredCourse course, Grade grade) throws Exception;

    public List<Student> viewEnrolledStudents(String semester, String courseId) throws CourseNotFoundException;

    public List<Course> viewCoursesTaken(String professorId) throws ProfessorNotFoundException;

    public void registerForCourse(String professorId, String courseId, String semester) throws CourseNotAssignedToProfessorException;

    public Professor getProfessorById(String professorId);
}

