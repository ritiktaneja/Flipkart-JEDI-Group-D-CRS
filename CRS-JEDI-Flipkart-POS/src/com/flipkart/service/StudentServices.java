package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Semester;
import com.flipkart.bean.Student;
import com.flipkart.constants.Grade;

import java.util.List;

public interface StudentServices extends UserServices{
<<<<<<< HEAD
    boolean addCourse(Student student, Semester semester, Course course);
    boolean dropCourse(RegisteredCourse registeredCourse);
    List<RegisteredCourse> viewRegisteredCourses(Student student, Semester semester);
    long calculateFee(Student student, Semester semester);
    boolean getRegistrationStatus(Student student);
=======
    boolean addCourse(String  studentId, String semester, String courseCode);
    boolean dropCourse(String registeredCourseId);
    List<Course> viewCourses(String catalogId);
    List<RegisteredCourse> viewRegisteredCourses(String studentId, String semester);
    // TODO List<Grade>
    long calculateFee(String studentId);
    boolean getRegistrationStatus(String studentId);

>>>>>>> af03542 (Modified student interface, added id in registered course class)
}
