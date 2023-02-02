package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Semester;
import com.flipkart.bean.Student;
import com.flipkart.constants.Grade;

import java.util.List;

public interface StudentServices extends UserServices{

    boolean addCourse(String  studentId, String semester, String courseCode);
    boolean dropCourse(String registeredCourseId);
    List<Course> viewCourses(String catalogId);
    List<RegisteredCourse> viewRegisteredCourses(String studentId, String semester);
    // TODO List<Grade>
    long calculateFee(String studentId);
    boolean getRegistrationStatus(String studentId);

}
