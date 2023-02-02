package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Semester;
import com.flipkart.bean.Student;
import com.flipkart.constants.Grade;

import java.util.List;

public interface StudentServices extends UserServices{
    boolean addCourse(Student student, Semester semester, Course course);
    boolean dropCourse(RegisteredCourse registeredCourse);
    List<RegisteredCourse> viewRegisteredCourses(Student student, Semester semester);
    long calculateFee(Student student, Semester semester);
    boolean getRegistrationStatus(Student student);
}
