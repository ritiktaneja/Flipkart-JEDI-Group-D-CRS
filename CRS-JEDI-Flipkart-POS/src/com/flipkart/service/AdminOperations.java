package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.constants.CRSColors;
import com.flipkart.dao.*;
import com.flipkart.data.MockDB;
import com.flipkart.exception.*;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Admin Operation Class
 */
public class AdminOperations extends UserOperations implements AdminServices {
    public CourseCatalogServices courseCatalogServices = new CourseCatalogOperations();

    /**
     * Using this method admin can add course in Catalog
     *
     * @param catalogId
     * @param courseCode
     * @param courseName
     * @throws CourseNotAddedException
     */
    public void addCourse(String catalogId, String courseCode, String courseName) throws CourseNotAddedException {

        courseCatalogServices.addCourseToCatalog(catalogId, courseCode, courseName);

    }

    /**
     * Using this method admin can delete course
     *
     * @param catalogId
     * @param courseCode
     * @throws CourseNotRemovedException
     */
    public void deleteCourse(String catalogId, String courseCode) throws CourseNotRemovedException {
        courseCatalogServices.removeCourseFromCatalog(catalogId, courseCode);
    }

    /**
     * Using this method admin can approve Student
     *
     * @param studentId
     * @throws StudentNotApprovedException
     */

    public void approveStudent(String studentId) throws StudentNotApprovedException {
        try {
            StudentDao studentDao = StudentDao.getInstance();
            Student student = studentDao.get(studentId);
            if (student == null) {
                System.out.println("No student exist with this ID");
                return;
            }
            System.out.print("Are you sure you want to approve this student ? Type yes to confirm : ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            if (input.equalsIgnoreCase("yes")) {
                AdminDao adminDao = AdminDao.getInstance();
                adminDao.approveStudent(studentId);
                System.out.println(CRSColors.GREEN + "\nStudent Registration approved Successfully\n" + CRSColors.RESET);
            } else {
                System.out.println("Student Registration not approved");
            }
        } catch (Exception e) {
            throw new StudentNotApprovedException(studentId);
        }
    }

    /**
     * Using this method admin can add professor
     *
     * @param professorId
     * @param professorName
     * @param password
     * @throws ProfessorNotAddedException
     */
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

    /**
     * Using this method admin can view courses
     *
     * @param catalogId
     * @return list of courses
     * @throws CatalogNotFoundException
     */
    public List<Course> viewCourses(String catalogId) throws CatalogNotFoundException {
        try {
            CourseCatalogDao dao = CourseCatalogDao.getInstance();
            return dao.get(catalogId).getCourses();
        } catch (Exception e) {
            throw new CatalogNotFoundException(catalogId);
        }

    }

    /**
     * Using this method admin can view professor
     *
     * @return list of professor
     */
    public List<Professor> viewProfessors() {
        ProfessorDao professorDao = ProfessorDao.getInstance();
        return professorDao.getAll();
    }

    /**
     * Using this method admin can view Student
     *
     * @return list of student
     */
    public List<Student> viewStudents() {
        return StudentDao.getInstance()
                .getAll()
                .stream()
                .filter(student -> student.isApproved())
                .collect(Collectors.toList());
    }

    /**
     * Using this method admin can view Student which one's request still pending to approve
     *
     * @return list of student
     */
    public List<Student> viewPendingApprovals() {
        List<Student> unApprovedStudents = new ArrayList<>();
        StudentDao dao = StudentDao.getInstance();
        unApprovedStudents = dao.getAll().stream().filter(student -> student.isApproved() == false).collect(Collectors.toList());
        return unApprovedStudents;
    }

    /**
     * View list of admin
     *
     * @return list of admin
     */
    @Override
    public List<Admin> viewAdmins() {
        AdminDao adminDao = AdminDao.getInstance();
        return adminDao.getAll();
    }

    /**
     * using this method admin can enroll itself
     *
     * @param adminId
     * @param adminName
     * @param password
     * @throws AdminNotAddedException
     */
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


    /**
     * using this method admin get info of current semester
     *
     * @return Current semester
     */
    public static Semester getCurrentSemester() {
        AdminDao adminDao = AdminDao.getInstance();
        return adminDao.getCurrentSemester();
    }

    /**
     * using this method admin can update semester
     *
     * @param semester
     */
    public void updateSemester(String semester) {
        AdminDao dao = AdminDao.getInstance();
        int rs = dao.updateSemester(semester);
        if (rs >= 1) {
            System.out.println("Semester updated successfully");
        } else {
            System.out.println("Semester not updated");
        }
    }

    /**
     * Get admin by id
     *
     * @param adminId
     * @return
     */
    @Override
    public Admin getAdminById(String adminId) {
        AdminDao adminDao = AdminDao.getInstance();
        return adminDao.get(adminId);
    }

}
