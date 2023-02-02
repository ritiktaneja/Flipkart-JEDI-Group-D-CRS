package com.flipkart.client;

import java.util.Scanner;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Semester;
import com.flipkart.bean.Student;
import com.flipkart.constants.Grade;
import com.flipkart.data.MockDB;
import com.flipkart.service.StudentOperations;

public class CRSStudentMenu {

    public void createMenu() {
        System.out.println("********************* Welcome Student *********************");
        while (true) {
            System.out.println("********************** Student Menu **********************");
            System.out.println("1. Semester Registration");
            System.out.println("2. Add Course");
            System.out.println("3. Drop Course");
            System.out.println("4. View Available courses");
            System.out.println("5. View Registered Courses");
            System.out.println("6. View Grade Card");
            System.out.println("7. Pay fees");
            System.out.println("8. Logout");
            int choice;
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    addCourse();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    viewAvailableCourses();
                    break;
                case 5:
                    viewRegisteredCourses();
                    break;
                case 6:
                    viewGradesCard();
                    break;
                case 7:
                    payFees();
                    break;
                case 8:
                    System.out.println("Exiting . . .");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter a valid input");
                    break;
            }
        }
    }

    public void registerCourses() {

    }

    public void addCourse() {

        Scanner sc = Scanner(System.in);
        System.out.println("Give the Student ID: ");
        String Id = sc.next();

        Student student = null;
        student = StudentOperations.getStudentfronId(Id);

        Semester semester = null;

        Course course = null;
        addCourse(Student student, Semester semester, Course course);
        System.out.println("Course Added to Student with Student ID: " + id);

    }

    public void dropCourse() {
        Scanner sc = Scanner(System.in);

        System.out.println("Give the Student ID: ");
        String Id = sc.next();

        Student student = null;
        student = StudentOperations.getStudentfronId(Id);

        dropCourse(RegisteredCourse registeredCourse);

//        System.out.println( Student with Student ID: " + id);
    }

    public void viewAvailableCourses() {

        Scanner sc = Scanner(System.in);
        System.out.println("Give the Catalog ID: ");
        String catalogId = sc.next();
        StudentServices.viewCourses(catalogId);
    }

    public void viewRegisteredCourses() {

        Scanner sc = Scanner(System.in);
        System.out.println("Give the Student ID: ");
        String Id = sc.next();

        Student student = null;
        student = StudentOperations.getStudentfronId(Id);

        Semester semester = null;
        viewRegisteredCourses(Student student, Semester semester)
    }

    public void viewGradesCard() {

    }

    public void payFees() {
        Scanner sc = Scanner(System.in);
        System.out.println("Give the Student ID: ");
        String Id = sc.next();

        Student student = null;
        student = StudentOperations.getStudentfronId(Id);
        long fee = calculateFee(Student student);
        System.out.println("The total fee for the Student with Student ID: " + id + " is " + fee);

        // TODO : update the fee paid status somewhere in DB

    }
}
