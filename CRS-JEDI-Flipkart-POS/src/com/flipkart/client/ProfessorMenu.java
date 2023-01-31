package com.flipkart.client;

import java.util.Scanner;

public class ProfessorMenu {

    public void createMenu() {

        System.out.println("********************* Welcome Professor *********************");
        while (true) {
            System.out.println("********************** Professor Menu **********************");
            System.out.println("1. View Enrolled Students");
            System.out.println("2. Add Grade");
            System.out.println("3. View Assigned Courses");
            System.out.println("4. Exit");
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
                    System.out.println("Exiting . . .");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter a valid input");
                    break;
            }
        }
    }

    public void viewEnrolledStudents() {

    }

    public void addGrade() {

    }

    public void viewAssignedCourses() {

    }
}
