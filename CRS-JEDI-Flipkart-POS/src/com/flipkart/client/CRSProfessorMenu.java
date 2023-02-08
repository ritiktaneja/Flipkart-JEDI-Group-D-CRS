package com.flipkart.client;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.constants.CRSColors;
import com.flipkart.constants.Grade;
import com.flipkart.data.MockDB;
import com.flipkart.service.*;

import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

/**
 * CRS Professor Menu
 */
public class CRSProfessorMenu {

    Scanner sc = new Scanner(System.in);
    ProfessorServices obj = new ProfessorOperations();
    String professorId;

    /**
     * Set professor id from the given input
     * @param facultyId
     */
    public CRSProfessorMenu(String facultyId) {
        // TODO Auto-generated constructor stub
        professorId = facultyId;
    }

    /**
     * Professor Menu in the CRS application
     * @param name
     */
    public void createMenu(String name) {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        Professor professor = obj.getProfessorById(professorId);
        System.out.println("********************************************************");
        System.out.println("******************* Welcome Professor ******************");
        System.out.println("********************************************************\n");
        System.out.println("\n\t Login Time : " + formattedDate + "\n");
        System.out.println("\t Hi, " + professor.getName() + " (" + professor.getFacultyId() + "). Have a Good Day!\n");
        while (true) {
            try {
                System.out.println("********************************************************");
                System.out.println("*******************   Professor Menu   *****************");
                System.out.println("********************************************************\n");
                System.out.println("\t1. View Enrolled Students");
                System.out.println("\t2. Add Grade");
                System.out.println("\t3. View Assigned Courses");
                System.out.println("\t4. Register Course");
                System.out.println("\t5. Logout");
                System.out.print("Enter your choice : ");
                int choice;
                Scanner sc = new Scanner(System.in);
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        viewEnrolledStudents();
                        break;
                    case 2:
                        addGrade();
                        break;
                    case 3:
                        viewAssignedCourses(professorId);
                        break;
                    case 4:
                        registerForCourses();
                        break;
                    case 5:
                        System.out.println("\nHeading to Main Menu . . .");
                        return;
                    default:
                        System.out.println("\nEnter a valid input");
                        break;
                }
            } catch (Exception e) {

            }
        }
    }

    /**
     * View enrolled students with the course id
     * @param courseID
     * @throws Exception
     */
    public void viewEnrolledStudents(String courseID) throws Exception {
        List<Student> studentList = null;
        ProfessorOperations professorOperations = new ProfessorOperations();
        studentList = professorOperations.viewEnrolledStudents(CRSApplication.currentSemester.getCurrentSemester(), courseID);
        System.out.println("\nThe enrolled students under " + CRSColors.GREEN + courseID + CRSColors.RESET + " are : \n");
        String stars = "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
        String plus = "|";
        String stars2 = "|                                                                      |";
        System.out.println(stars);
        System.out.println(stars2);
        String namePlate = String.format("|%45s" + "%26s", "Enrolled Students", plus);
        System.out.println(namePlate);
        System.out.println(stars2);
        studentList.forEach(student -> {
            String c = String.format("|%35s" + "%20s" + "%16s", student.getName(), " (" + student.getStudentId() + ")", plus);
            System.out.println(c);
        });
        System.out.println(stars);
        System.out.println();
    }

    /**
     * View enrolled students in the given course
     * @throws Exception
     */
    public void viewEnrolledStudents() throws Exception {
//        System.out.print("The courses taken by you are: ");
//        viewAssignedCourses(professorId);

        System.out.print("Enter the Course code for which you want to see enrolled students : ");
        String courseID = sc.next();
        viewEnrolledStudents(courseID);

    }

    /**
     * Add grade for the current student
     * @throws Exception
     */
    public void addGrade() throws Exception {
        //Show All courses
        //Professor Selects a Course
        //Display the student list in that course
        //Get RegisteredOCurse Object for each of this
        //Th1e Professor selects the student from the list
        //Assigns the grade which is then set in Registered COurse Object
        viewAssignedCourses(professorId);
        System.out.print("\nEnter the Course code for which you want to enter grade of students : ");
        String couCode = sc.next();
        viewEnrolledStudents(couCode);
        while (true) {
            System.out.print("Enter the student ID for which you want to add Grade: (-1 to abort) : ");
            String studentId = sc.next();
            if (studentId.equals("-1")) break;

            System.out.print("Enter Grade: ");
            String grade = sc.next();
            ProfessorOperations professorOperations = new ProfessorOperations();
            professorOperations.addGrade(studentId, grade, couCode);
        }
        System.out.println(CRSColors.GREEN + "Grade added Successfully!" + CRSColors.RESET);

    }

    /**
     * View assigned courses for the current professor
     * @param professorId
     * @throws Exception
     */
    public void viewAssignedCourses(String professorId) throws Exception {
        List<Course> coursesTaken = null;
        ProfessorOperations professorOperations = new ProfessorOperations();
        coursesTaken = professorOperations.viewCoursesTaken(professorId);
        System.out.println("\nCourses under the professor with professor ID " + CRSColors.GREEN +  professorId +  CRSColors.RESET + " are : ");
        coursesTaken.forEach(course -> System.out.println(course));

    }

    /**
     * Register for courses method for the professor
     * @throws Exception
     */
    public void registerForCourses() throws Exception {
        //View all the available courses
        //Select the course which is not taken by another professor
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter the Course ID : ");
        String courseId = sc.next();

        ProfessorOperations professorOperations = new ProfessorOperations();
        professorOperations.registerForCourse(professorId, courseId, CRSApplication.currentSemester.getCurrentSemester());
        viewAssignedCourses(professorId);

    }

}
