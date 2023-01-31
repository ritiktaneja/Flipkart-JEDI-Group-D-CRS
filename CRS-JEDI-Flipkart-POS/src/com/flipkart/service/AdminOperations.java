package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.List;

public class AdminOperations implements AdminInterface {

    public void addCourse(Course course, String catalogId) {
        // DAO Operation
    }
    public void deleteCourse(String courseCode) {
        // DAO Operation
    }
    public void approveStudent(String studentId) {

    }
    public void addProfessor(Professor professor) {

    }
    public void assignCourse(String courseCode, String professorId) {

    }
    public List<Course> viewCourses(int catalogId) {
        return null;
    }
    public List<Professor> viewProfessors() {
        return null;
    }
    public List<Student> viewStudents() {
        return null;
    }
    public List<Student> viewPendingApprovals() {
        return null;
    }
}
