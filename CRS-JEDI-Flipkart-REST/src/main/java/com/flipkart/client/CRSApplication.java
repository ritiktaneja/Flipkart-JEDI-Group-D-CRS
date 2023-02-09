package com.flipkart.client;

import com.flipkart.bean.*;
import com.flipkart.constants.CRSColors;
import com.flipkart.dao.UserDao;
import com.flipkart.data.MockDB;
import com.flipkart.service.AdminOperations;
import com.flipkart.service.SelfRegistrationOperations;
import com.flipkart.service.UserOperations;
import com.flipkart.service.UserServices;

import java.io.IOException;
import java.util.*;

/**
 * CRS Application Class
 */
public class CRSApplication {

    public static Semester currentSemester;

    /**
     * method of Course Registration System for semester
     *
     * @param args
     */
    static {
        currentSemester = AdminOperations.getCurrentSemester();
    }
//
//    public static void main(String[] args) {
//        System.out.println("Welcome to Course Registration System for semester : " + currentSemester.getCurrentSemester() + "\n");
//        Scanner sc = new Scanner(System.in);
//        String choice = "";
//
//        while (true) {
//            playMusic();
//            System.out.println("********************************************************");
//            System.out.println("*****************      Main Menu       *****************");
//            System.out.println("********************************************************\n");
//            System.out.println("\t1. Login");
//            System.out.println("\t2. Student Self Registration");
//            System.out.println("\t3. Update Password");
//            System.out.println("\t4. Logout\n");
//            System.out.print("Enter your choice : ");
//            choice = sc.next();
//            switch (choice) {
//                case "1":
//                    System.out.print("\n\tEnter your UserId : ");
//                    String name = sc.next();
//                    System.out.print("\tEnter your Password : ");
//                    String passwd = sc.next();
//                    User user = getRole(name, passwd);
//                    if (user instanceof Student) {
//                        Student student = (Student) (user);
//                        if (!student.isApproved()) {
//                            System.out.println(CRSColors.YELLOW + "Verification Pending....Contact to Administrator" + CRSColors.RESET);
//                        } else {
//                            CRSStudentMenu stuobj = new CRSStudentMenu(student.getStudentId());
//                            stuobj.createMenu(name);
//                        }
//                        break;
//                    } else if (user instanceof Admin) {
//                        try {
//                            Admin a = (Admin) user;
//                            CRSAdminMenu stuobj = new CRSAdminMenu(a.getAdminId());
//                            stuobj.createMenu(name);
//                        } catch (Exception e) {
//
//                        }
//                        break;
//                    } else if (user instanceof Professor) {
//                        try {
//                            Professor p = (Professor) user;
//                            CRSProfessorMenu stuobj = new CRSProfessorMenu(p.getFacultyId());
//                            stuobj.createMenu(name);
//                        } catch (Exception e) {
//
//                        }
//                        break;
//                    } else {
//                        System.out.println(CRSColors.YELLOW + "Invalid Credentials, or user Not Verified" + CRSColors.RESET);
//                    }
//                    break;
//                case "2":
//                    System.out.println("\nEnter details for registration: ");
//                    String name1, password, department = "";
//                    System.out.print("\tEnter Name : ");
//                    name1 = sc.next();
//                    System.out.print("\tEnter password : ");
//                    password = sc.next();
//                    while (true) {
//                        System.out.println("\nEnter Department : ");
//                        System.out.println("\t1. CSE");
//                        System.out.println("\t2. EEE");
//                        System.out.println("\t3. IT");
//                        System.out.println("\t4. CIVIL");
//                        System.out.println("\t5. MECH");
//                        System.out.print("Enter your choice : ");
//                        String choose = sc.next();
//                        switch (choose) {
//                            case "1":
//                                department = "CSE";
//                                break;
//                            case "2":
//                                department = "EEE";
//                                break;
//                            case "3":
//                                department = "IT";
//                                break;
//                            case "4":
//                                department = "CIVIL";
//                                break;
//                            case "5":
//                                department = "MECH";
//                                break;
//                            default:
//                                System.out.println(CRSColors.YELLOW + "Please enter a correct option!\n" + CRSColors.RESET);
//                        }
//                        if (department != "")
//                            break;
//
//                    }
//                    SelfRegistrationOperations operations = new SelfRegistrationOperations();
//                    Student student = operations.selfRegister(name1, password, currentSemester.getCurrentSemester(), department);
//                    System.out.println(student);
//                    System.out.println(CRSColors.GREEN + "Details added successfully. Waiting for admin approval\n" + CRSColors.RESET);
//                    break;
//
//                case "3":
//                    System.out.print("\tEnter your UserId : ");
//                    String userName = sc.next();
//                    System.out.print("\tEnter new Password : ");
//                    String userPassword = sc.next();
//                    UserServices userObject = new UserOperations();
//                    boolean result = userObject.updatePassword(userName, userPassword);
//                    if (result) {
//                        System.out.println(CRSColors.GREEN + "Update Password Successful\n" + CRSColors.RESET);
//                        break;
//                    } else {
//                        System.out.println(CRSColors.YELLOW + "Update Password Unsuccessful\n" + CRSColors.RESET);
//                        break;
//                    }
//
//                case "4":
//                    System.exit(0);
//                    break;
//
//                default:
//                    System.out.println(CRSColors.RED + "Invalid Input!! Enter Again: \n" + CRSColors.RESET);
//            }
//        }
//    }
//
//    private static void playMusic() {
//
//    }
//
//    /**
//     * method to get role of user
//     *
//     * @param id
//     * @param passwd
//     * @return
//     */
//    private static User getRole(String id, String passwd) {
//
//        UserOperations userOperations = new UserOperations();
//        User user = userOperations.loginUser(id, passwd);
//        return user;
//    }user
}
