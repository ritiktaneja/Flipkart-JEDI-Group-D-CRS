package com.flipkart.client;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constants.CRSColors;
import com.flipkart.data.MockDB;
import com.flipkart.service.AdminOperations;


import java.util.List;
import java.util.Scanner;

import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

/**
 * CRS Admin Menu
 */
public class CRSAdminMenu {

    AdminOperations adminOperations = new AdminOperations();
    private String adminId;

    /**
     * method of CRS admin menu
     * @param aId
     */
    public CRSAdminMenu(String aId) {
        this.adminId = aId;
    }

    /**
     * Method to create Menu for CRS Application
     * @param name
     */
    public void createMenu(String name) {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        Admin admin = adminOperations.getAdminById(adminId);
        System.out.println("********************************************************");
        System.out.println("******************** Welcome Admin *********************");
        System.out.println("********************************************************\n");
        System.out.println("\n\t Login Time : " + formattedDate + "\n");
        System.out.println("\t Hi, " + admin.getName() + " (" + admin.getAdminId() + "). Have a Good Day!\n");
        while (true) {
            try {
                System.out.println("********************************************************");
                System.out.println("*****************      Admin Menu      *****************");
                System.out.println("********************************************************\n");
                System.out.println("\t1. Add Course to catalog");
                System.out.println("\t2. Add Professor");
                System.out.println("\t3. Add Admin");
                System.out.println("\t4. Approve Student");
                System.out.println("\t5. Delete Course from catalog");
                System.out.println("\t6. View Added Professors");
                System.out.println("\t7. Show Courses in catalog");
                System.out.println("\t8. Show registered students");
                System.out.println("\t9. Show Pending Admissions");
                System.out.println("\t10. Show Added Admins");
                System.out.println("\t11. Add new Semester");
                System.out.println("\t12, Open Semester Registration");
                System.out.println("\t13. Close Semester Registration");
                System.out.println("\t14. Logout\n");
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
                        updateSemester();
                        break;
                    case 12:
                        openSemesterRegistration();
                        break;
                    case 13:
                        closeSemesterRegistration();
                        break;
                    case 14:
                        System.out.println("\nHeading to Main Menu . . .");
                        return;
                    default:
                        System.out.println("\nEnter a valid input");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void closeSemesterRegistration() {
        System.out.println("Under Development");
    }

    private void openSemesterRegistration() {
        System.out.println("Under Development");
    }

    /**
     * Method to Update Semester
     */
    private void updateSemester() {
        System.out.print("Enter new semester number : ");
        Scanner scanner = new Scanner(System.in);
        String semester = scanner.nextLine();
        adminOperations.updateSemester(semester);
    }

    /**
     * Method to Show Registered Student
     */
    private void showRegisteredStudents() {
        List<Student> list = adminOperations.viewStudents();
        if (list == null) {
            System.out.println(CRSColors.GREEN + "No Registered Student Found" + CRSColors.RESET);
            return;
        } else {
            Student.printStudentList("Registered Students", list);
        }
    }

    /**
     * Method to show added Admin
     * @throws Exception
     */
    private void showAddedAdmins() throws Exception {
        List<Admin> adminList = adminOperations.viewAdmins();
        if (adminList == null) {
            System.out.println("No admin except you");
            return;
        } else {
            Admin.printAdminList("Admin List", adminList);
        }
    }

    /**
     * Method to add professor
     * @throws Exception
     */
    private void addProfessor() throws Exception {
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
     * Method to show professor
     */
    private void showProfessor() {
        List<Professor> professors = adminOperations.viewProfessors();
        if (professors == null) {
            System.out.println(CRSColors.RED + "No Professor Added" + CRSColors.RESET);
        } else {
            Professor.printProfessorList("Professors List", professors);
        }
    }

    /**
     * Method to view Pending Admission
     */
    private void viewPendingAdmission() {
        List<Student> list = adminOperations.viewPendingApprovals();
        if (list == null) {
            System.out.println(CRSColors.GREEN + "No Student with pending admission" + CRSColors.RESET);
            return;
        } else {
            Student.printStudentList("Pending Admissions", list);
        }

    }

    /**
     * Method to view course in catalog
     * @throws Exception
     */
    private void viewCoursesInCatalog() throws Exception {
        List<Course> list = adminOperations.viewCourses(AdminOperations.getCurrentSemester().getCurrentSemester());
        if (list == null) {
            System.out.println("No course Exist in catalog");
            return;
        } else {
            Course.printCourseList("Courses Catalog", list);
        }
    }

    /**
     * Method to add admin
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
     * Method to approve student
     * @throws Exception
     */
    private void approveStudent() throws Exception {
        viewPendingAdmission();
        System.out.print("Enter the student Id that is to be approved : ");// using semester as catalog id
        Scanner sc = new Scanner(System.in);
        String studentId = sc.nextLine();
        adminOperations.approveStudent(studentId);
    }

    /**
     * method to add course to catalog
     * @throws Exception
     */
    private void addCourseToCatalog() throws Exception {
        System.out.print("Enter the course id  : ");
        Scanner sc = new Scanner(System.in);
        String courseId = sc.next();
        System.out.print("Enter the course name  : ");
        sc = new Scanner(System.in);
        String courseName = sc.next();
        adminOperations.addCourse(CRSApplication.currentSemester.getCurrentSemester(), courseId, courseName);
    }

    /**
     * method to delete course from catalog
     * @throws Exception
     */
    private void deleteCourseFromCatalog() throws Exception {
        System.out.print("Enter the course ID  : ");
        Scanner sc = new Scanner(System.in);
        String courseId = sc.next();
        adminOperations.deleteCourse(AdminOperations.getCurrentSemester().getCurrentSemester(), courseId);

    }
}
