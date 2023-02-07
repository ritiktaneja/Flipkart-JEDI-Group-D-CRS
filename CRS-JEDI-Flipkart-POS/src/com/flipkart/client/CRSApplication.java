package com.flipkart.client;

import com.flipkart.bean.*;
import com.flipkart.dao.UserDao;
import com.flipkart.data.MockDB;
import com.flipkart.service.AdminOperations;
import com.flipkart.service.SelfRegistrationOperations;
import com.flipkart.service.UserOperations;
import com.flipkart.service.UserServices;

import java.io.IOException;
import java.util.*;

public class CRSApplication {

    public static Semester currentSemester;


    public static void main(String[] args) {
        currentSemester = AdminOperations.getCurrentSemester();

        System.out.println("Welcome to Course Registration System for semester : " + currentSemester);
        Scanner sc = new Scanner(System.in);
        String choice = "";//sc.nextInt();

        while (true) {
            playMusic();
            System.out.println("********************************************************");
            System.out.println("*****************      Main Menu       *****************");
            System.out.println("********************************************************");
            System.out.println("1. Login");
            System.out.println("2. Student Self Registration");
            System.out.println("3. Update Password");
            System.out.println("4. Logout");
            System.out.print("Enter your choice : ");
            choice = sc.next();
            switch (choice) {
                case "1":
                    System.out.print("Enter your UserId : ");
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
                            stuobj.createMenu(name);
                        }
                        break;
                    } else if (user instanceof Admin) {
                        try {
                            Admin a = (Admin) user;
                            System.out.println("Welcome to Admin Menu: ");
                            CRSAdminMenu stuobj = new CRSAdminMenu(a.getAdminId());
                            stuobj.createMenu(name);
                        } catch (Exception e) {

                        }
                        break;
                    } else if (user instanceof Professor) {
                        try {
                            Professor p = (Professor) user;
                            System.out.println("Welcome to Professor Menu: ");
                            CRSProfessorMenu stuobj = new CRSProfessorMenu(p.getFacultyId());
                            stuobj.createMenu(name);
                        } catch (Exception e) {

                        }
                        break;
                    } else {
                        System.out.println("Invalid Credentials, or user Not Verified");
                    }
                    break;
                case "2":
                    System.out.println("Enter details for registration: ");
                    String name1, password, department = "";
                    System.out.print("Enter Name : ");
                    name1 = sc.next();
                    System.out.print("Enter password : ");
                    password = sc.next();
                    while (true) {
                        System.out.println("Enter Department : ");
                        System.out.println("1. CSE");
                        System.out.println("2. EEE");
                        System.out.println("3. IT");
                        System.out.println("4. CIVIL");
                        System.out.println("5. MECH");
                        System.out.print("Enter your choice : ");
                        String choose = sc.next();
                        switch (choose) {
                            case "1":
                                department = "CSE";
                                break;
                            case "2":
                                department = "EEE";
                                break;
                            case "3":
                                department = "IT";
                                break;
                            case "4":
                                department = "CIVIL";
                                break;
                            case "5":
                                department = "MECH";
                                break;
                            default:
                                System.out.println("Please enter a correct option!");
                        }
                        if (department != "")
                            break;

                    }
                    SelfRegistrationOperations operations = new SelfRegistrationOperations();
                    Student student = operations.selfRegister(name1, password, currentSemester.getCurrentSemester(), department);
                    System.out.println(student);
                    System.out.println("Details added successfully. Waiting for admin approval");
                    break;

                case "3":
                    System.out.print("Enter your UserId : ");
                    String userName = sc.next();
                    System.out.print("Enter new Password : ");
                    String userPassword = sc.next();
                    UserServices userObject = new UserOperations();
                    boolean result = userObject.updatePassword(userName, userPassword);
                    if (result) {
                        System.out.println("Update Password Successful");
                        break;
                    } else {
                        System.out.println("Update Password Unsuccessful");
                        break;
                    }

                case "4":
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Input!! Enter Again: ");
            }
        }
    }

    private static void playMusic() {

    }

    private static User getRole(String id, String passwd) {

        UserOperations userOperations = new UserOperations();
        User user = userOperations.loginUser(id, passwd);
        return user;
    }
}
