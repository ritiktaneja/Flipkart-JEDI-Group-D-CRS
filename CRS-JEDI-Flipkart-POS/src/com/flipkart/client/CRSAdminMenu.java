package com.flipkart.client;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.data.MockDB;
import com.flipkart.service.AdminOperations;


import java.util.List;
import java.util.Scanner;

public class CRSAdminMenu {

    AdminOperations adminOperations = new AdminOperations();
    private String adminId;

    /**
     *
     * @param aId
     */
    public CRSAdminMenu(String aId) {
        this.adminId = aId;
    }

    /**
     *
     * @throws Exception
     */
    public void createMenu() throws Exception {
        System.out.println("********************************************************");
        System.out.println("******************** Welcome Admin *********************");
        while (true) {
            System.out.println("********************************************************");
            System.out.println("*****************      Admin Menu      *****************");
            System.out.println("********************************************************");
            System.out.println("1. Add Course to catalog");
            System.out.println("2. Add Professor");
            System.out.println("3. Add Admin");
            System.out.println("4. Approve Student");
            System.out.println("5. Delete Course from catalog");
            System.out.println("6. View Added Professors");
            System.out.println("7. Show Courses in catalog");
            System.out.println("8. Show registered students");
            System.out.println("9. Show Pending Admissions");
            System.out.println("10. Show Added Admins");
            System.out.println("11. Logout");
            System.out.print("Enter your choice : ");
            int choice;
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addCourseToCatalog();
                    break;
                case 2:
                    addProfessor();
                    break;
                case 3:
                    addAdmin();
                    break;
                case 4:
                    approveStudent();
                    break;
                case 5:
                    deleteCourseFromCatalog();
                    break;
                case 6:
                    showProfessor();
                    break;
                case 7:
                    viewCoursesInCatalog();
                    break;
                case 8:
                    showRegisteredStudents();
                    break;
                case 9:
                    viewPendingAdmission();
                    break;
                case 10:
                    showAddedAdmins();
                    break;
                case 11:
                    System.out.println("Heading to Main Menu . . .");
                    return;
                default:
                    System.out.println("Enter a valid input");
                    break;
            }
        }
    }

    /**
     * Show Registered Student
     */
    private void showRegisteredStudents() {
        List<Student> list = adminOperations.viewStudents();
        if (list == null) {
            System.out.println("No Student with pending addmission");
            return;
        }
        for (Student c : list) {
            System.out.println(c);
        }
    }

    /**
     * Show Added Admins
     * @throws Exception
     */
    private void showAddedAdmins() throws Exception {
        List<Admin> admins = adminOperations.viewAdmins();
        if (admins == null) {
            System.out.println("No admin except you");
            return;
        }
        for (Admin admin : admins) {
            System.out.println(admin);
        }

    }

    /**
     * Added Professor
     */
    private void addProfessor() {
        System.out.print("Set professor Id ");// using semester as catalog id
        Scanner sc = new Scanner(System.in);
        String professorId = sc.nextLine();
        System.out.print("Enter the Professor Name : ");
        sc = new Scanner(System.in);
        String professorName = sc.nextLine();
        System.out.print("Set the password for Professor : ");
        sc = new Scanner(System.in);
        String password = sc.nextLine();
        adminOperations.addProfessor(professorId, professorName, password);
    }

    /**
     * Show Professor
     */
    private void showProfessor() {
        List<Professor> professors = adminOperations.viewProfessors();
        if (professors == null) {
            System.out.println("No Professor Added");
        }
        for (Professor p : professors) {
            System.out.println(p);
        }
    }

    /**
     * View pending Admissions
     */
    private void viewPendingAdmission() {
        List<Student> list = adminOperations.viewPendingApprovals();
        if (list == null) {
            System.out.println("No Student with pending addmission");
            return;
        }
        for (Student c : list) {
            System.out.println(c);
        }
    }

    /**
     * View Courses In Catalog
     */
    private void viewCoursesInCatalog() {
        System.out.print("Enter the Semester for getting catalog courses : ");// using semester as catalog id
        Scanner sc = new Scanner(System.in);
        String catalogId = sc.nextLine();
        List<Course> list = adminOperations.viewCourses(catalogId);
        if (list == null) {
            System.out.println("No course Exist in catalog");
            return;
        }
        for (Course c : list) {
            System.out.println(c);
        }
    }

    /**
     * Add Admin
     * @throws Exception
     */
    private void addAdmin() throws Exception {
        System.out.print("Enter Admin Id ");// using semester as catalog id
        Scanner sc = new Scanner(System.in);
        String adminId = sc.nextLine();
        System.out.print("Enter the Admin Name : ");
        sc = new Scanner(System.in);
        String adminName = sc.nextLine();
        System.out.print("Set the password for new Admin : ");
        sc = new Scanner(System.in);
        String password = sc.nextLine();
        adminOperations.addAdmin(adminId, adminName, password);
    }

    /**
     * Approve Student
     */
    private void approveStudent() {
        System.out.print("Enter the student Id that is to be approved : ");// using semester as catalog id
        Scanner sc = new Scanner(System.in);
        String studentId = sc.nextLine();
        adminOperations.approveStudent(studentId);
    }

    /**
     * Add course to Catalog
     */
    private void addCourseToCatalog() {
        System.out.print("Enter the Semester for creating catalog  : ");// using semester as catalog id
        Scanner sc = new Scanner(System.in);
        String catalogId = sc.nextLine();
        System.out.print("Enter the course id  : ");
        sc = new Scanner(System.in);
        String courseId = sc.next();
        System.out.print("Enter the course name  : ");
        sc = new Scanner(System.in);
        String courseName = sc.next();
        adminOperations.addCourse(catalogId, courseId, courseName);
    }

    /**
     * Delete Course from Catalog
     */
    private void deleteCourseFromCatalog() {
        System.out.print("Enter the Semester for deleting catalog from catalog : ");// using semester as catalog id
        Scanner sc = new Scanner(System.in);
        String catalogId = sc.nextLine();
        System.out.print("Enter the course ID  : ");
        sc = new Scanner(System.in);
        String courseId = sc.next();
        adminOperations.deleteCourse(catalogId, courseId);

    }
}
