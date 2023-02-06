package com.flipkart.client;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.data.MockDB;
import com.flipkart.service.AdminOperations;
import com.flipkart.service.SelfRegistrationOperations;
import com.flipkart.service.UserOperations;

import java.util.*;

public class CRSApplication {

    public static String currentSemester;

    public static void main(String[] args) {
        currentSemester = AdminOperations.getCurrentSemester();

        System.out.println("Welcome to Course Registration System for semester : " + currentSemester);
        Scanner sc = new Scanner(System.in);
        int choice = 1;//sc.nextInt();
        int iterator;
        while (true) {
            System.out.println("********************************************************");
            System.out.println("*****************      Main Menu       *****************");
            System.out.println("********************************************************");
            System.out.println("1. Login");
            System.out.println("2. Student Self Registration");
            System.out.println("3. Update Password");
            System.out.println("4. Logout");
            System.out.print("Enter your choice : ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter your Username : ");
                    String name = sc.next();
                    System.out.print("Enter your Password : ");
                    String passwd = sc.next();
                    User user = getRole(name, passwd);
                    if (user instanceof Student) {
                        Student student = (Student) (user);
                        if (!student.isApproved()) {
                            System.out.println("Verification Pending.... Contact Administrator");
                        } else {
                            System.out.println("Welcome to Student Menu: ");
                            CRSStudentMenu stuobj = new CRSStudentMenu(student.getStudentId());
                            stuobj.createMenu();
                        }
                        break;
                    } else if (user instanceof Admin) {
                        try {
                            Admin a = (Admin) user;
                            System.out.println("Welcome to Admin Menu: ");
                            CRSAdminMenu stuobj = new CRSAdminMenu(a.getAdminId());
                            stuobj.createMenu();
                        } catch (Exception e) {

                        }
                        break;
                    } else if (user instanceof Professor) {
                        try {
                            Professor p = (Professor) user;
                            System.out.println("Welcome to Professor Menu: ");
                            CRSProfessorMenu stuobj = new CRSProfessorMenu(p.getFacultyId());
                            stuobj.createMenu();
                        } catch (Exception e) {

                        }
                        break;
                    } else {
                        System.out.println("Invalid Credentials, or user Not Verified");
                    }
                    break;
                case 2:
                    System.out.println("Enter details for registration: ");
                    String name1, password, department;
                    System.out.print("Enter Name : ");
                    name1 = sc.next();
                    System.out.print("Enter password : ");
                    password = sc.next();
                    System.out.print("Enter Department : ");
                    department = sc.next();
                    SelfRegistrationOperations operations = new SelfRegistrationOperations();
                    Student student = operations.selfRegister(name1, password, currentSemester, department);
                    System.out.println(student);
                    System.out.println("Details added successfully. Waiting for admin approval");
                    break;

                case 3:
                    System.out.println("Enter details to update password: ");
                    break;

                case 4:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invlaid Input!! Enter Again: ");
                    choice = sc.nextInt();
            }
        }
    }

    private static User getRole(String id, String passwd) {

        UserOperations userOperations = new UserOperations();
        User user = userOperations.loginUser(id, passwd);
        return user;
    }
}
