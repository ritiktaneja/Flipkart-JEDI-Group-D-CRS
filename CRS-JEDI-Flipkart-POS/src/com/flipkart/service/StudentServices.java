package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotRegisteredException;

import java.util.List;

public interface StudentServices extends UserServices {

    boolean addCourse(String studentId, String courseCode) throws CourseNotRegisteredException;

    boolean dropCourse(String studentId, String courseCode);

    List<Course> viewAvailableCourses(String studentID);

    List<RegisteredCourse> viewRegisteredCourses(String studentId);

    // TODO List<Grade>
    long calculateFee(String studentId);

    boolean getRegistrationStatus(String studentId);

    Student getStudentByID(String studentId);


}
