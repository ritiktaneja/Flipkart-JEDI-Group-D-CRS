package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.List;

public interface AdminServices extends UserServices{

    void addCourse(String catalogId, String courseId) throws Exception;
    void deleteCourse(String courseCode) throws Exception;
    void approveStudent(String studentId) throws Exception;
    void addProfessor(String professorId) throws Exception;
    void assignCourse(String courseCode, String professorId) throws Exception;
    List<Course> viewCourses(String catalogId) throws Exception;
    List<Professor> viewProfessors();
    List<Student> viewStudents();
    List<Student> viewPendingApprovals();

}
