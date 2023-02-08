package com.flipkart.client;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.flipkart.bean.Course;

import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.constants.CRSColors;
import com.flipkart.exception.CourseNotRegisteredException;
import com.flipkart.service.*;

import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

/**
 * CRS Student Menu
 */
public class CRSStudentMenu {

    Scanner sc = new Scanner(System.in);
    StudentServices studentServices = new StudentOperations();
    String studentId;

    public CRSStudentMenu(String sId) {
        this.studentId = sId;
    }

    /**
     * Student Menu class
     *
     * @param name
     */
    public void createMenu(String name) {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        Student student = studentServices.getStudentByID(studentId);
        System.out.println("********************************************************");
        System.out.println("******************* Welcome Student ********************");
        System.out.println("********************************************************\n");
        System.out.println("\n\t Login Time : " + formattedDate + "\n");
        System.out.println("\t Hi, " + student.getName() + " (" + student.getStudentId() + "). Have a Good Day!\n");
        while (true) {
            try {
                System.out.println("********************************************************");
                System.out.println("********************   Student Menu   ******************");
                System.out.println("********************************************************\n");
                System.out.println("\t1. Add Course");
                System.out.println("\t2. Drop Course");
                System.out.println("\t3. View Available courses");
                System.out.println("\t4. View Registered Courses");
                System.out.println("\t5. View Grade Card");
                System.out.println("\t6. Pay fees");
                System.out.println("\t7. Logout\n");
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
                        System.out.println("\nHeading to Main Menu . . .");
                        return;
                    default:
                        System.out.println("\nEnter a valid input");
                        break;
                }
            } catch (Exception e) {
                //System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Add course from the student menu
     *
     * @throws CourseNotRegisteredException
     */
    public void addCourse() throws CourseNotRegisteredException {
        viewAvailableCourses();
        List<RegisteredCourse> ls = studentServices.viewRegisteredCourses(studentId);
        if (ls.size() == 6) {
            System.out.println(CRSColors.YELLOW + "\nMaximum course registration limit reached!!. Drop a course to register." + CRSColors.RESET);

            return;
        }
        System.out.print("\nEnter the Course Code : ");
        String courseCode = sc.next();
        if (studentServices.addCourse(studentId, courseCode))
            System.out.println(CRSColors.GREEN + "Course Added!" + CRSColors.RESET);
        else
            System.out.println(CRSColors.YELLOW + "Course could not be Added!" + CRSColors.RESET);

    }

    /**
     * Drop course from the given input in the menu
     */
    public void dropCourse() {
        viewRegisteredCourses();
        System.out.print("\nGive the Registered Course ID : ");
        String Id = sc.next();
        if (studentServices.dropCourse(studentId, Id))
            System.out.println(CRSColors.GREEN + "Course Dropped!" + CRSColors.RESET);
        else
            System.out.println(CRSColors.YELLOW + "Course could not be Dropped!" + CRSColors.RESET);

    }

    /**
     * View available courses
     */
    public void viewAvailableCourses() {
        List<Course> ls = studentServices.viewAvailableCourses(studentId);
        Course.printCourseList("Available Courses", ls);
    }

    /**
     * View registered courses for the given student
     */
    public void viewRegisteredCourses() {

        List<RegisteredCourse> ls = studentServices.viewRegisteredCourses(studentId);
        RegisteredCourse.printRegisteredCourseList("Registered Courses", ls);
    }

    /**
     * View grade card for the current student
     */
    public void viewGradesCard() {
        Student student = studentServices.getStudentByID(studentId);
        List<RegisteredCourse> registeredCourses = studentServices.viewRegisteredCourses(studentId);
        RegisteredCourse.printRegisteredCourseList("Grade Card", registeredCourses);
    }

    /**
     * Pay fee method for the current student
     */
    public void payFees() {

        long fee = studentServices.calculateFee(studentId);
        System.out.println("\nThe total fee for the Student with Student ID: " + studentId + " is " + fee);
        System.out.println("Would you like to pay now ? Type " + CRSColors.GREEN + "yes" + CRSColors.RESET + " to confirm ");
        Scanner sc = new Scanner(System.in);
        if (sc.next().equalsIgnoreCase("yes")) {
            System.out.println("Select payment method");
            System.out.println("\t1. Online ");
            System.out.println("\t2. Offline ");
            System.out.print("Enter your choice : ");
            sc = new Scanner(System.in);
            String refId = String.valueOf(UUID.randomUUID()), status = "Initiated", payDesc = "Payment has been initiated . . . ";
            PaymentServices paymentServices = new PaymentOperations();
            switch (sc.next()) {
                case "1":
                    System.out.println("Redirecting to payment gateway . . . ");
                    System.out.println(CRSColors.GREEN + "Payment initiated.\n" + CRSColors.RESET);
                    paymentServices.initPayment(studentId, refId, "Online", fee, CRSApplication.currentSemester.getCurrentSemester(), "Pending", payDesc);
                    break;
                case "2":
                    System.out.println(CRSColors.GREEN + "Payment initiated.\n" + CRSColors.RESET);
                    paymentServices.initPayment(studentId, refId, "Offline", fee, CRSApplication.currentSemester.getCurrentSemester(), "Pending", payDesc);
                    break;
                default:
                    System.out.println(CRSColors.YELLOW + "You entered an Invalid Input" + CRSColors.RESET);
                    System.out.println(CRSColors.RED + "Payment aborted . . .\n" + CRSColors.RESET);
                    break;
            }
        } else {
            System.out.println(CRSColors.RED + "Payment aborted . . ." + CRSColors.RESET);
        }

    }
}
