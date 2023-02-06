package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;

import java.util.List;

public interface StudentServices extends UserServices{

    /**
     * Default Constructor
     * @param studentId
     * @param courseCode
     * @return add course status
     */
    boolean addCourse(String  studentId, String courseCode);
    /**
     * Default Constructor
     * @param studentId
     * @param courseCode
     * @return drop course status
     */
    boolean dropCourse(String studentId, String courseCode);

    /**
     *
     * @param studentID
     * @return List of courses
     */
    List<Course> viewCourses(String studentID);

    /**
     *
     * @param studentId
     * @return List of registered courses
     */
    List<RegisteredCourse> viewRegisteredCourses(String studentId);
    // TODO List<Grade>

    /**
     *
     * @param studentId
     * @return fee for student
     */
    long calculateFee(String studentId);
    boolean getRegistrationStatus(String studentId);

}
