package com.flipkart.client;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.data.MockDB;
import com.flipkart.service.AdminOperations;


import java.util.List;
import java.util.Scanner;

import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

public class CRSAdminMenu {

    AdminOperations adminOperations = new AdminOperations();
    private String adminId;

    public CRSAdminMenu(String aId) {
        this.adminId = aId;
    }

    public void createMenu(String name) {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);


        System.out.println("********************************************************");
        System.out.println("******************** Welcome Admin *********************");
        System.out.println("********************************************************");
        System.out.println("\n\t Login Time : " + formattedDate + "\n");
        System.out.println("\t Welcome to the Admin Menu, " + name + "\n");
        while (true) {
            try {
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
                System.out.println("11. Update Semester");
                System.out.println("12, Open Semester Registration");
                System.out.println("13. Close Semester Registration");
                System.out.println("12. Logout");
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
                        System.out.println("Heading to Main Menu . . .");
                        return;
                    default:
                        System.out.println("Enter a valid input");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void closeSemesterRegistration() {
    }

    private void openSemesterRegistration() {


    }

    private void updateSemester() {
        System.out.print("Enter new semester number : ");
        Scanner scanner = new Scanner(System.in);
        String semester = scanner.nextLine();
        adminOperations.updateSemester(semester);
    }

    private void showRegisteredStudents() {
        List<Student> list = adminOperations.viewStudents();
        if (list == null) {
            System.out.println("No Student with pending admission");
            return;
        }
        list.forEach(student -> System.out.println(student));
//        for (Student c : list) {
//            System.out.println(c);
//        }
    }

    private void showAddedAdmins() throws Exception {
        List<Admin> admins = adminOperations.viewAdmins();
        if (admins == null) {
            System.out.println("No admin except you");
            return;
        }
        admins.forEach(admin -> System.out.println(admin));
//        for (Admin admin : admins) {
//            System.out.println(admin);
//        }

    }

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

    private void showProfessor() {
        List<Professor> professors = adminOperations.viewProfessors();
        if (professors == null) {
            System.out.println("No Professor Added");
        }
        professors.forEach(professor -> System.out.println(professor));
//        for (Professor p : professors) {
//            System.out.println(p);
//        }
    }

    private void viewPendingAdmission() {
        List<Student> list = adminOperations.viewPendingApprovals();
        if (list == null) {
            System.out.println("No Student with pending addmission");
            return;
        }
        list.forEach(student -> System.out.println(student));
//        for (Student c : list) {
//            System.out.println(c);
//        }
    }

    private void viewCoursesInCatalog() throws Exception {
        List<Course> list = adminOperations.viewCourses(AdminOperations.getCurrentSemester().getCurrentSemester());
        if (list == null) {
            System.out.println("No course Exist in catalog");
            return;
        }
        list.forEach(course -> System.out.println(course));
//        for (Course c : list) {
//            System.out.println(c);
//        }
    }

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

    private void approveStudent() throws Exception {
        viewPendingAdmission();
        System.out.print("Enter the student Id that is to be approved : ");// using semester as catalog id
        Scanner sc = new Scanner(System.in);
        String studentId = sc.nextLine();
        adminOperations.approveStudent(studentId);
    }

    private void addCourseToCatalog() throws Exception {
        System.out.print("Enter the course id  : ");
        Scanner sc = new Scanner(System.in);
        String courseId = sc.next();
        System.out.print("Enter the course name  : ");
        sc = new Scanner(System.in);
        String courseName = sc.next();
        adminOperations.addCourse(CRSApplication.currentSemester.getCurrentSemester(), courseId, courseName);
    }

    private void deleteCourseFromCatalog() throws Exception {
        System.out.print("Enter the course ID  : ");
        Scanner sc = new Scanner(System.in);
        String courseId = sc.next();
        adminOperations.deleteCourse(AdminOperations.getCurrentSemester().getCurrentSemester(), courseId);

    }
}
