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
import com.flipkart.constants.Grade;
import com.flipkart.data.MockDB;
import com.flipkart.service.*;

public class CRSProfessorMenu {

    Scanner sc = new Scanner(System.in);
    ProfessorServices obj = new ProfessorOperations();
    String professorId;

    public CRSProfessorMenu(String facultyId) {
        // TODO Auto-generated constructor stub
        professorId = facultyId;
    }

    public void createMenu() {

        System.out.println("********************************************************");
        System.out.println("******************* Welcome Professor ******************");
        System.out.println("********************************************************");
        while (true) {
            try {
                System.out.println("********************************************************");
                System.out.println("*******************   Professor Menu   *****************");
                System.out.println("********************************************************");
                System.out.println("1. View Enrolled Students");
                System.out.println("2. Add Grade");
                System.out.println("3. View Assigned Courses");
                System.out.println("4. Register Course");
                System.out.println("5. Logout");
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

    public void viewEnrolledStudents() throws Exception {
        System.out.print("The courses taken by you are: ");
        viewAssignedCourses(professorId);

        System.out.print("Enter the Course code for which you want to see enrolled students : ");
        String courseID = sc.next();
        List<Student> studentList = null;
        ProfessorOperations professorOperations = new ProfessorOperations();
        studentList = professorOperations.viewEnrolledStudents(CRSApplication.currentSemester, courseID);
        System.out.println("The enrolled students under" + courseID + " are : ");
        for (Student student : studentList) {
            System.out.println(student.getUserId() + " : " + student.getName());
        }

    }

    public void addGrade() throws Exception {
        //Show All courses
        //Professor Selects a Course
        //Display the student list in that course
        //Get RegisteredOCurse Object for each of this
        //The Professor selects the student from the list
        //Assigns the grade which is then set in Registered COurse Object
        viewAssignedCourses(professorId);
        System.out.print("Enter the Course code for which you want to enter grade of students : ");
        String couCode = sc.next();
        viewEnrolledStudents();
        while (true) {
            System.out.print("Enter the student ID for which you want to add Grade: (-1 to abort) : ");
            String studentId = sc.next();
            if (studentId.equals("-1")) break;
            System.out.print("Enter Grade: ");
            String grade = sc.next();
            ProfessorOperations professorOperations = new ProfessorOperations();
            professorOperations.addGrade(studentId, grade, couCode);
        }
        System.out.println("Grade added succesfully");

    }

    public void viewAssignedCourses(String professorId) throws Exception {
        List<Course> coursesTaken = null;
        ProfessorOperations professorOperations = new ProfessorOperations();
        coursesTaken = professorOperations.viewCoursesTaken(professorId);
        System.out.println("Courses under the professor with professor ID " + professorId + " are : ");
        for (Course course : coursesTaken) {
            System.out.println(course);
        }
    }

    public void registerForCourses() throws Exception {
        //View all the available courses
        //Select the course which is not taken by another professor
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Course ID : ");
        String courseId = sc.next();

        ProfessorOperations professorOperations = new ProfessorOperations();
        professorOperations.registerForCourse(professorId, courseId, CRSApplication.currentSemester);
        viewAssignedCourses(professorId);

    }

}
