package com.flipkart.service;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.List;

/**
 * AdminServices Class
 */
public interface AdminServices extends UserServices {

    void addCourse(String catalogId, String courseCode, String CourseName) throws Exception;

    void deleteCourse(String catalogId, String courseCode) throws Exception;

    void approveStudent(String studentId) throws Exception;

    void addProfessor(String professorId, String professorName, String password,String department) throws Exception;


    List<Course> viewCourses(String catalogId) throws Exception;

    List<Professor> viewProfessors();

    List<Student> viewStudents();

    List<Student> viewPendingApprovals();

    List<Admin> viewAdmins() throws Exception;

    void addAdmin(String adminId, String adminName, String password) throws Exception;

    Admin getAdminById(String adminId);

    void updateSemester(String semester);


}


