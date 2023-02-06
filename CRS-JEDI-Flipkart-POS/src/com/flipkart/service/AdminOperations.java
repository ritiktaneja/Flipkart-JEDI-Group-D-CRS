package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.dao.*;
import com.flipkart.data.MockDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Admin Operation class
 */
public class AdminOperations extends UserOperations implements AdminServices {
    public CourseCatalogServices courseCatalogServices = new CourseCatalogOperations();

    /**
     * Add Course to Catalog
     * @param catalogId
     * @param courseCode
     * @param courseName
     */
    public void addCourse(String catalogId, String courseCode, String courseName) {
        courseCatalogServices.addCourseToCatalog(catalogId, courseCode, courseName);
    }

    /**
     * Delete Course to Catalog
     * @param catalogId
     * @param courseCode
     */
    public void deleteCourse(String catalogId, String courseCode) {
        courseCatalogServices.removeCourseFromCatalog(catalogId, courseCode);
    }

    /**
     * Approve Student
     * @param studentId
     */
    public void approveStudent(String studentId) {
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.get(studentId);
        System.out.println(student);
        System.out.print("Are you sure you want to approve this student ? Type yes to confirm : ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if (input.equalsIgnoreCase("yes")) {
            AdminDao adminDao = new AdminDao();
            adminDao.approveStudent(studentId);
            System.out.println("Student Registration approved Successfully");
        } else {
            System.out.println("Student Registration not approved");
        }
    }

    /**
     * Add professor
     * @param professorId
     * @param professorName
     * @param password
     */
    public void addProfessor(String professorId, String professorName, String password) {
        ProfessorDao professorDao = new ProfessorDao();
        Professor.ProfessorBuilder builder = new Professor.ProfessorBuilder();
        builder.setFacultyId(professorId);
        builder.setName(professorName);
        builder.setPassword(password);
        professorDao.insert(builder.build());
        UserDao dao = new UserDao();
        dao.insert(builder.build());
    }

    /**
     * Assign Courses
     * @param courseCode
     * @param professorId
     */
    public void assignCourse(String courseCode, String professorId) {
        for (CourseCatalog catalog : MockDB.catalogs) {
            for (Course course : catalog.getCourses()) {
                if (course.getCourseCode() == courseCode) {
                    course.setProfessor(MockDB.getProfessorFromId(professorId));
                }
            }
        }
    }

    /**
     *
     * @param catalogId
     * @return Courses
     */
    public List<Course> viewCourses(String catalogId) {
        CourseCatalogDao dao = new CourseCatalogDao();
        return dao.get(catalogId).getCourses();

    }

    /**
     *
     * @return List of Professor
     */
    public List<Professor> viewProfessors() {
        ProfessorDao professorDao = new ProfessorDao();
        return professorDao.getAll();
    }

    /**
     *
     * @return List of Students
     */
    public List<Student> viewStudents() {
        return MockDB.students;
    }

    /**
     *
     * @return Pending Approval
     */
    public List<Student> viewPendingApprovals() {
        List<Student> unApprovedStudents = new ArrayList<>();
        StudentDao dao = new StudentDao();
        for (Student student : dao.getAll()) {
            if (student.isApproved() == false) {
                unApprovedStudents.add(student);
            }
        }
        return unApprovedStudents;
    }

    /**
     * View Admins
     * @throws Exception
     */
    @Override
    public List<Admin> viewAdmins() throws Exception {
        AdminDao adminDao = new AdminDao();
        return adminDao.getAll();
    }

    /**
     *
     * @param adminId
     * @param adminName
     * @param password
     * @throws Exception
     */
    @Override
    public void addAdmin(String adminId, String adminName, String password) throws Exception {
        Admin.AdminBuilder builder = new Admin.AdminBuilder();
        builder.setAdminId(adminId);
        builder.setName(adminName);
        builder.setPassword(password);
        AdminDao adminDao = new AdminDao();
        adminDao.insert(builder.build());
        UserDao userDao = new UserDao();
        userDao.insert(builder.build());
    }
}
