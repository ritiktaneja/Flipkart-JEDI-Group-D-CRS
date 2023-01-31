package com.flipkart.client;

import java.util.Scanner;

public class StudentMenu {

    public void createMenu() {
        System.out.println("********************* Welcome Student *********************");
        while (true) {
            System.out.println("********************** Student Menu **********************");
            System.out.println("1. Register for courses");
            System.out.println("2. Add Course");
            System.out.println("3. Drop Course");
            System.out.println("4. View Available courses");
            System.out.println("5. View Registered Courses");
            System.out.println("6. View Gradecard");
            System.out.println("7. Pay fees");
            System.out.println("8. Exit");
            int choice;
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
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

    }

    public void dropCourse() {

    }

    public void viewAvailableCourses() {

    }

    public void viewRegisteredCourses() {

    }

    public void viewGradesCard() {

    }

    public void payFees() {

    }
}
