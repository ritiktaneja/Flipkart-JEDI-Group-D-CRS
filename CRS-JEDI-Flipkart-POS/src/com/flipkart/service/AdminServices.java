package com.flipkart.service;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.List;

public interface AdminServices extends UserServices {

    /**
     *
     * @param catalogId
     * @param courseCode
     * @param CourseName
     * @throws Exception
     */
    void addCourse(String catalogId, String courseCode, String CourseName) throws Exception;

    /**
     *
     * @param catalogId
     * @param courseCode
     * @throws Exception
     */
    void deleteCourse(String catalogId, String courseCode) throws Exception;

    /**
     *
     * @param studentId
     * @throws Exception
     */
    void approveStudent(String studentId) throws Exception;

    /**
     *
     * @param professorId
     * @param professorName
     * @param password
     * @throws Exception
     */
    void addProfessor(String professorId, String professorName, String password) throws Exception;

    /**
     *
     * @param courseCode
     * @param professorId
     * @throws Exception
     */
    void assignCourse(String courseCode, String professorId) throws Exception;

    /**
     *
     * @param catalogId
     * @return
     * @throws Exception
     */
    List<Course> viewCourses(String catalogId) throws Exception;

    /**
     *
     * @return List of Professor
     */
    List<Professor> viewProfessors();

    /**
     *
     * @return List of Students
     */
    List<Student> viewStudents();

    /**
     *
     * @return view Pending Approval
     */
    List<Student> viewPendingApprovals();

    /**
     *
     * @return List of admin
     * @throws Exception
     */
    List<Admin> viewAdmins() throws Exception;

    /**
     *
     * @param adminId
     * @param adminName
     * @param password
     * @throws Exception
     */
    void addAdmin(String adminId, String adminName, String password) throws Exception;

}


