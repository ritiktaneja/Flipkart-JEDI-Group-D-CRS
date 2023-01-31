package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.List;

public interface ProfessorInterface {


    public boolean addGrade(RegisteredCourse course) throws Exception;
    public List<Student> viewEnrolledStudents(Course course) throws Exception;
    public List<Course> viewCoursesTaken() throws Exception;

}