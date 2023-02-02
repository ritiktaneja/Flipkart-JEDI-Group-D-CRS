package com.flipkart.client;

import java.util.Scanner;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;

public class CRSStudentMenu {

	Scanner sc= new Scanner(System.in);
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
        System.out.println("Give the Student ID: ");
        String studentId = sc.next();

        System.out.println("Give the semester: ");
        String semester = sc.next();

        System.out.println("Give the Course Code: ");
        String courseCode = sc.next();

        if(addCourse(studentId, semester, courseCode))
            System.out.println("Course Added!");
        else
            System.out.println("Course could not be Added!");

    }

    public void dropCourse() {

        System.out.println("Give the Registered Course ID: ");
        String Id = sc.next();
        if(dropCourse(Id));
            System.out.println("Course Dropped!");
        else
            System.out.println("Course could not be Dropped!");

    }

    public void viewAvailableCourses() {

        System.out.println("Give the Catalog ID: ");
        String catalogId = sc.next();

        List<Course> ls = StudentServices.viewCourses(catalogId);
        for(Course c : ls) {
            System.out.println(c.getName());
        }
    }

    public void viewRegisteredCourses() {

        System.out.println("Give the Student ID: ");
        String  studentId = sc.next();

        System.out.println("Give the semester: ");
        String semester = sc.next();

        List<RegisteredCourse> ls = viewRegisteredCourses(studentId, semester);

        for(RegisteredCourse c : ls) {
            System.out.println(c.getcourse().getName());
        }

    }

    public void viewGradesCard() {
        // NOT YET IMPLEMENTED!
    }

    public void payFees() {
        System.out.println("Give the Student ID: ");
        String Id = sc.next();

        long fee = calculateFee(Id);
        System.out.println("The total fee for the Student with Student ID: " + Id + " is " + fee);

        // TODO : update the fee paid status somewhere in DB

    }
}
