package com.flipkart.client;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.data.MockDB;
import com.flipkart.service.*;

public class CRSStudentMenu {

    Scanner sc = new Scanner(System.in);
    StudentServices obj = new StudentOperations();
    String studentId;

    /**
     *
     * @param sId
     */
    public CRSStudentMenu(String sId) {
        this.studentId = sId;
    }

    /**
     * CRS Student Menu
     */
    public void createMenu() {
        System.out.println("********************************************************");
        System.out.println("******************* Welcome Student ********************");
        while (true) {
            System.out.println("********************************************************");
            System.out.println("********************   Student Menu   ******************");
            System.out.println("********************************************************");
            System.out.println("1. Semester Registration");
            System.out.println("2. Add Course");
            System.out.println("3. Drop Course");
            System.out.println("4. View Available courses");
            System.out.println("5. View Registered Courses");
            System.out.println("6. View Grade Card");
            System.out.println("7. Pay fees");
            System.out.println("8. Logout");
            System.out.print("Enter your choice : ");
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
                    System.out.println("Heading to Main Menu . . .");
                    return;
                default:
                    System.out.println("Enter a valid input");
                    break;
            }
        }
    }

    /**
     * Default Constructor
     */
    public void registerCourses() {

    }

    /**
     * Method to Add Course
     */
    public void addCourse() {
        System.out.print("Give the Course Code : ");
        String courseCode = sc.next();

        if (obj.addCourse(studentId, courseCode))
            System.out.println("Course Added!");
        else
            System.out.println("Course could not be Added!");

    }

    /**
     * Method to Drop Course
     */
    public void dropCourse() {

        System.out.print("Give the Registered Course ID : ");
        String Id = sc.next();
        if (obj.dropCourse(studentId, Id))
            System.out.println("Course Dropped!");
        else
            System.out.println("Course could not be Dropped!");

    }

    /**
     * Method to view available courses
     */
    public void viewAvailableCourses() {
        List<Course> ls = obj.viewCourses(studentId);
        for (Course c : ls) {
            System.out.println(c);
        }
    }

    /**
     * Method to view Registered Courses
     */
    public void viewRegisteredCourses() {
        List<RegisteredCourse> ls = obj.viewRegisteredCourses(studentId);

        for (RegisteredCourse c : ls) {
            System.out.println(c);
        }

    }

    /**
     * Method to view Grade Card
     */
    public void viewGradesCard() {
        // NOT YET IMPLEMENTED!
        Map<Student, Set<RegisteredCourse>> register_Courses = MockDB.registeredCourses;
        Student student = MockDB.getStudentFromId(studentId);
        if (student == null) {
            throw new RuntimeException("Student Not Found");
        }
        Set<RegisteredCourse> iterr = null;
        for (var entry : register_Courses.entrySet()) {
            if (entry.getKey() == student) {
                iterr = entry.getValue();
                break;
            }
        }
        Iterator<RegisteredCourse> value = iterr.iterator();
        while (value.hasNext()) {
            RegisteredCourse obj = (RegisteredCourse) value.next();
            System.out.println("Course: " + obj.getCourse().getName() + " Grade: " + obj.getGrade());
        }
    }

    public void payFees() {

        long fee = obj.calculateFee(studentId);
        System.out.println("The total fee for the Student with Student ID: " + studentId + " is " + fee);

        // TODO : update the fee paid status somewhere in DB

    }
}
