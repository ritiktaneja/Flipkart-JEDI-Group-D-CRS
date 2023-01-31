package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;

import java.util.List;

public interface AdminInterface {

    void addCourse(Course course, String catalogId) throws Exception;
    void deleteCourse(String courseCode) throws Exception;
    void approveStudent(String studentId) throws Exception;
    void addProfessor(Professor professor) throws Exception;
    void assignCourse(String courseCode, String professorId) throws Exception;
    List<Course> viewCourses(int catalogId) throws Exception;
    List<Professor> viewProfessors();
    List<Student> viewStudents();
    List<Student> viewPendingApprovals();

}
