package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;

import java.util.List;

public interface StudentServices extends UserServices{

    boolean addCourse(String  studentId, String courseCode);
    boolean dropCourse(String studentId, String courseCode);
    List<Course> viewCourses(String studentID);
    List<RegisteredCourse> viewRegisteredCourses(String studentId);
    // TODO List<Grade>
    long calculateFee(String studentId);
    boolean getRegistrationStatus(String studentId);

}
