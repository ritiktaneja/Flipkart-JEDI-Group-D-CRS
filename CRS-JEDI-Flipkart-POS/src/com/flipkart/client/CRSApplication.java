package com.flipkart.client;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.data.MockDB;

import java.util.*;

public class CRSApplication {


    public static void main(String[] args) {
        MockDB.main(args);
        System.out.println("Welcome to Course Registration System! choose the Option given below!");

        Scanner sc = new Scanner(System.in);
        int choice = 1;//sc.nextInt();
        int iterator;
        while (true) {
            System.out.println("********************************************************");
            System.out.println("*****************      Main Menu       *****************");
            System.out.println("********************************************************");
            System.out.println("1. Login");
            System.out.println("2. Registration of the Student");
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
        for (Admin a : MockDB.admins) {
            if (a.getAdminId().equals(id) && a.getPassword().equals(passwd)) {
                return a;
            }
        }
        for (Professor p : MockDB.professors) {
            if (p.getFacultyId().equals(id) && p.getPassword().equals(passwd)) {
                return p;
            }
        }
        for (Student p : MockDB.students) {
            System.out.println(p.getStudentId() + " - " + p.getPassword());
            if (p.getStudentId().equals(id) && p.getPassword().equals(passwd)) {
                return p;
            }
        }
        return null;
    }

//    public void createMainMenu() {
//
//    }
//
//    public void loginUser() {
//
//    }
//
//    public void registerStudent() {
//
//    }
//
//    public void updatePassword() {
//
//    }

}
