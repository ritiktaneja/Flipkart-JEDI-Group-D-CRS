package com.flipkart.client;

import java.util.Scanner;

public class AdminMenu {

    public void createMenu() {
        System.out.println("********************* Welcome Admin *********************");
        while (true) {
            System.out.println("********************** Admin Menu **********************");
            System.out.println("1. Add Course to catalog");
            System.out.println("2. Add Professor");
            System.out.println("3. Add Admin");
            System.out.println("4. Approve Student");
            System.out.println("5. Delete Course from catalog");
            System.out.println("6. Assign Course to professor");
            System.out.println("7. View Pending Admission");
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

    public void addProfessor() {

    }
    public void viewPendingAdmission(){

    }
    public void viewCoursesInCatalog(){

    }
    public void addAdmin() {

    }

    public void approveStudent() {
    }

    public void assignCourseToProfessor() {

    }

    public void addCourseToCatalog() {

    }

    public void deleteCourseFromCatalog() {

    }
}
