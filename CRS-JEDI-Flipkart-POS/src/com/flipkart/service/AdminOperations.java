package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.dao.*;
import com.flipkart.data.MockDB;
import com.flipkart.exception.*;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminOperations extends UserOperations implements AdminServices {
    public CourseCatalogServices courseCatalogServices = new CourseCatalogOperations();

    public void addCourse(String catalogId, String courseCode, String courseName) throws CourseNotAddedException {

        courseCatalogServices.addCourseToCatalog(catalogId, courseCode, courseName);

    }

    public void deleteCourse(String catalogId, String courseCode) throws CourseNotRemovedException {
        courseCatalogServices.removeCourseFromCatalog(catalogId, courseCode);
    }

    public void approveStudent(String studentId) throws StudentNotApprovedException {
        try {
            StudentDao studentDao = StudentDao.getInstance();
            Student student = studentDao.get(studentId);
            System.out.println(student);
            System.out.print("Are you sure you want to approve this student ? Type yes to confirm : ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            if (input.equalsIgnoreCase("yes")) {
                AdminDao adminDao = AdminDao.getInstance();
                adminDao.approveStudent(studentId);
                System.out.println("Student Registration approved Successfully");
            } else {
                System.out.println("Student Registration not approved");
            }
        } catch (Exception e) {
            throw new StudentNotApprovedException(studentId);
        }
    }

    public void addProfessor(String professorId, String professorName, String password) throws ProfessorNotAddedException {
        try {
            ProfessorDao professorDao = ProfessorDao.getInstance();

            Professor.ProfessorBuilder builder = new Professor.ProfessorBuilder();

            builder.setFacultyId(professorId);
            builder.setName(professorName);
            builder.setPassword(password);

            UserDao dao = UserDao.getInstance();
            dao.insert(builder.build());
            professorDao.insert(builder.build());

        } catch (Exception e) {
            throw new ProfessorNotAddedException(professorId, professorName, password);
        }
    }

    public void assignCourse(String courseCode, String professorId) throws CourseNotAssignedToProfessorException {
        // Not required
    }

    public List<Course> viewCourses(String catalogId) throws CatalogNotFoundException {
        try {
            CourseCatalogDao dao = CourseCatalogDao.getInstance();
            return dao.get(catalogId).getCourses();
        } catch (Exception e) {
            throw new CatalogNotFoundException(catalogId);
        }

    }

    public List<Professor> viewProfessors() {
        ProfessorDao professorDao = ProfessorDao.getInstance();
        return professorDao.getAll();
    }

    public List<Student> viewStudents() {
        return MockDB.students;
    }

    public List<Student> viewPendingApprovals() {
        List<Student> unApprovedStudents = new ArrayList<>();
        StudentDao dao = StudentDao.getInstance();
        for (Student student : dao.getAll()) {
            if (student.isApproved() == false) {
                unApprovedStudents.add(student);
            }
        }
        return unApprovedStudents;
    }

    @Override
    public List<Admin> viewAdmins() {
        AdminDao adminDao = AdminDao.getInstance();
        return adminDao.getAll();
    }

    @Override
    public void addAdmin(String adminId, String adminName, String password) throws AdminNotAddedException {
        try {
            Admin.AdminBuilder builder = new Admin.AdminBuilder();
            builder.setAdminId(adminId);
            builder.setName(adminName);
            builder.setPassword(password);
            AdminDao adminDao = AdminDao.getInstance();
            adminDao.insert(builder.build());
            UserDao userDao = UserDao.getInstance();
            userDao.insert(builder.build());
        } catch (Exception e) {
            throw new AdminNotAddedException(adminId, adminName, password);
        }
    }

    public static String getCurrentSemester() {
        AdminDao adminDao = AdminDao.getInstance();
        return adminDao.getCurrentSemester();
    }

    public void updateSemester(String semester) {
        AdminDao dao = AdminDao.getInstance();
        int rs = dao.updateSemester(semester);
        if (rs >= 1) {
            System.out.println("Semester updated successfully");
        } else {
            System.out.println("Semester not updated");
        }
    }
}
