package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.List;

public class AdminOperations extends  UserOperations implements AdminServices {
    public void addCourse(Course course, String catalogId) {
        System.out.println("Course " + course + " added in catalog "+catalogId);
        // DAO Operation
    }
    public void deleteCourse(String courseCode) {
        System.out.println("Deleted Course " + courseCode );
        // DAO Operation
    }
    public void approveStudent(String studentId) {
        System.out.println("Approved Student" + studentId);

    }
    public void addProfessor(Professor professor) {
        System.out.println("Added Professor" + professor);
    }
    public void assignCourse(String courseCode, String professorId) {
        System.out.println("Assigned " +courseCode + " to professor " + professorId);
    }
    public List<Course> viewCourses(int catalogId) {
        System.out.println("Course list in catalogId " + catalogId);
        return null;
    }
    public List<Professor> viewProfessors() {
        System.out.println("Professors List :");
        return null;
    }
    public List<Student> viewStudents() {
        System.out.println("Students List :");
        return null;
    }
    public List<Student> viewPendingApprovals() {
        System.out.println("Pending Approvals :");
        return null;
    }
}
