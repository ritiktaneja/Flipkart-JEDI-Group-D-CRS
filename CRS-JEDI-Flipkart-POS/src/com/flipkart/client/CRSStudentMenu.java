package com.flipkart.client;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.flipkart.bean.Course;

import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.constants.CRSColors;
import com.flipkart.exception.CourseNotRegisteredException;
import com.flipkart.service.*;

public class CRSStudentMenu {

    Scanner sc = new Scanner(System.in);
    StudentServices studentServices = new StudentOperations();
    String studentId;
    public static final String ANSI_RED = "\u001B[31m";

    public CRSStudentMenu(String sId) {
        this.studentId = sId;
    }

    public void createMenu() {

        System.out.println("********************************************************");
        System.out.println("******************* Welcome Student ********************");
        while (true) {
            try {
                System.out.println("********************************************************");
                System.out.println("********************   Student Menu   ******************");
                System.out.println("********************************************************");
                System.out.println("1. Add Course");
                System.out.println("2. Drop Course");
                System.out.println("3. View Available courses");
                System.out.println("4. View Registered Courses");
                System.out.println("5. View Grade Card");
                System.out.println("6. Pay fees");
                System.out.println("7. Logout");
                System.out.print("Enter your choice : ");
                int choice;
                Scanner sc = new Scanner(System.in);
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        addCourse();
                        break;
                    case 2:
                        dropCourse();
                        break;
                    case 3:
                        viewAvailableCourses();
                        break;
                    case 4:
                        viewRegisteredCourses();
                        break;
                    case 5:
                        viewGradesCard();
                        break;
                    case 6:
                        payFees();
                        break;
                    case 7:
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

    public void addCourse() throws CourseNotRegisteredException {
        System.out.print("Give the Course Code : ");
        String courseCode = sc.next();
        if (studentServices.addCourse(studentId, courseCode))
            System.out.println("Course Added!");
        else
            System.out.println("Course could not be Added!");

    }

    public void dropCourse() {
        viewRegisteredCourses();
        System.out.print("Give the Registered Course ID : ");
        String Id = sc.next();
        if (studentServices.dropCourse(studentId, Id))
            System.out.println("Course Dropped!");
        else
            System.out.println("Course could not be Dropped!");

    }

    public void viewAvailableCourses() {
        List<Course> ls = studentServices.viewAvailableCourses(studentId);
        for (Course c : ls) {
            System.out.println(c);
        }
    }

    public void viewRegisteredCourses() {
        List<RegisteredCourse> ls = studentServices.viewRegisteredCourses(studentId);

        for (RegisteredCourse c : ls) {
            System.out.println(c);
        }

    }

    public void viewGradesCard() {
        Student student = studentServices.getStudentByID(studentId);
        List<RegisteredCourse> registeredCourses = studentServices.viewRegisteredCourses(studentId);
        String name = student.getName();
        String stars = "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
        String plus = "|";
        String stars2 = "|                                                                      |";
        System.out.println(stars);
        System.out.println(stars2);
        String namePlate = String.format("|%30s" + "%30s " + "%10s", "Student Name : " + name, "Student ID : " + studentId, plus);
        System.out.println(namePlate);
        System.out.println(stars2);
        Iterator<RegisteredCourse> value = registeredCourses.iterator();
        while (value.hasNext()) {
            RegisteredCourse obj = (RegisteredCourse) value.next();
            name = obj.getCourse().getName() + " (" + obj.getCourse().getCourseCode() + ")";
            String coursePlate = String.format("|%35s" + "%10s " + "%25s", name, " :  " + obj.getGrade(), plus);
            System.out.println(coursePlate);
        }
        System.out.println(stars);
        System.out.println();
    }

    public void payFees() {

        long fee = studentServices.calculateFee(studentId);
        System.out.println("The total fee for the Student with Student ID: " + studentId + " is " + fee);
        System.out.println("Would you like to pay now ? Type yes to confirm ");
        Scanner sc = new Scanner(System.in);
        if (sc.next().equalsIgnoreCase("yes")) {
            System.out.println("Select payment method");
            System.out.println("1. Online ");
            System.out.println("2. Offline ");
            System.out.print("Enter your choice : ");
            sc = new Scanner(System.in);
            String refId = String.valueOf(UUID.randomUUID()), status = "Initiated", payDesc = "Payment has been initiated . . . ";
            PaymentServices paymentServices = new PaymentOperations();
            switch (sc.next()) {
                case "1":
                    System.out.println("Redirecting to payment gateway . . . ");
                    System.out.println(CRSColors.GREEN + "Payment initiated." + CRSColors.RESET);
                    paymentServices.initPayment(studentId, refId, "Online", fee, CRSApplication.currentSemester, "Pending", payDesc);
                    break;
                case "2":
                    System.out.println(CRSColors.GREEN + "Payment initiated." + CRSColors.RESET);
                    paymentServices.initPayment(studentId, refId, "Offline", fee, CRSApplication.currentSemester, "Pending", payDesc);
                    break;
                default:
                    System.out.println(CRSColors.RED + "You entered an Invalid Input" + CRSColors.RESET);
                    System.out.println(CRSColors.RED + "Payment aborted . . ." + CRSColors.RESET);
                    break;
            }
        } else {
            System.out.println(CRSColors.RED + "Payment aborted . . ." + CRSColors.RESET);
        }

    }
}
