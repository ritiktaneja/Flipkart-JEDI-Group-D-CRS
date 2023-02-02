package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.constants.Grade;

import java.util.List;

public interface ProfessorServices extends UserServices {

    public boolean addGrade(RegisteredCourse course, Grade grade) throws Exception;

    public List<Student> viewEnrolledStudents(Course course) throws Exception;

    public List<Course> viewCoursesTaken(Professor professor) throws Exception;

    public boolean registerForCourse(Professor professor,Course course) throws Exception;

}
