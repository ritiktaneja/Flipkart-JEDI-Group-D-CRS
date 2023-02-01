package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.constants.Grade;

import java.util.List;

public interface StudentServices extends UserServices{
    boolean addCourse(Course course);
    boolean dropCourse(String courseId);
    List<Course> viewCourses(String catalogId);
    List<RegisteredCourse> viewRegisteredCourses(String studentId);
    // TODO List<Grade>
    boolean calculateFee(String studentId);
    boolean getRegistrationStatus(String studentId);
    boolean setRegistrationStatus(String studentId);

}
