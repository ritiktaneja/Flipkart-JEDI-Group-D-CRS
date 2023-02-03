package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.data.MockDB;

import java.util.ArrayList;
import java.util.List;

public class AdminOperations extends UserOperations implements AdminServices {
    public CourseCatalogServices courseCatalogServices = new CourseCatalogOperations();

    public void addCourse(String catalogId, String courseCode, String courseName) {
        courseCatalogServices.addCourseToCatalog(catalogId, courseCode, courseName);
    }

    public void deleteCourse(String catalogId, String courseCode) {
        courseCatalogServices.removeCourseFromCatalog(catalogId, courseCode);
    }

    public void approveStudent(String studentId) {
        Student student = MockDB.getStudentFromId(studentId);
        student.approve();
        System.out.println("Student approved Sucessfully");
    }

    public void addProfessor(String professorId, String professorName) {
        Professor.ProfessorBuilder builder = new Professor.ProfessorBuilder();
        builder.setFacultyId(professorId);
        builder.setName(professorName);
        MockDB.professors.add(builder.build());
    }

    public void assignCourse(String courseCode, String professorId) {
        for (CourseCatalog catalog : MockDB.catalogs) {
            for (Course course : catalog.getCourses()) {
                if (course.getCourseCode() == courseCode) {
                    course.setProfessor(MockDB.getProfessorFromId(professorId));
                }
            }
        }
    }

    public List<Course> viewCourses(String catalogId) {
        return MockDB.getCatalogFromId(catalogId).getCourses();
    }

    public List<Professor> viewProfessors() {
        return MockDB.professors;
    }

    public List<Student> viewStudents() {
        return MockDB.students;
    }

    public List<Student> viewPendingApprovals() {
        List<Student> unApprovedStudents = new ArrayList<>();
        for (Student student : MockDB.students) {
            if (student.isApproved() == false) {
                unApprovedStudents.add(student);
            }
        }
        return unApprovedStudents;
    }

    @Override
    public List<Admin> viewAdmins() throws Exception {
        return MockDB.admins;
    }

    @Override
    public void addAdmin(String adminId, String adminName) throws Exception {
        Admin.AdminBuilder builder = new Admin.AdminBuilder();
        builder.setAdminId(adminId);
        builder.setName(adminName);
        MockDB.admins.add(builder.build());
    }
}
