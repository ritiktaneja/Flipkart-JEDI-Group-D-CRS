package com.flipkart.client;

import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.constants.Grade;
import com.flipkart.service.*;

public class CRSProfessorMenu {
	
	
	ProfessorServices obj = new ProfessorOperations();

    public void createMenu() throws Exception {

        System.out.println("********************* Welcome Professor *********************");
        
        System.out.println("Enter the Professor ID: ");
    	Scanner scc = new Scanner(System.in);
    	
    	String professorId = scc.next();
        
        while (true) {
            System.out.println("********************** Professor Menu **********************");
            System.out.println("1. View Enrolled Students");
            System.out.println("2. Add Grade");
            System.out.println("4. View Assigned Courses");
            System.out.println("3. Logout");
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
                    System.out.println("Exiting . . .");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter a valid input");
                    break;
            }
        }
    }

    public void viewEnrolledStudents() throws Exception {
    	List<Student> studentList = null;
    	
    	
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.println("Enter the Course ID: ");
    	String semester = sc.next();
    	
    	System.out.println("Enter the Semester: ");
    	String courseID = sc.next();
    	
    	studentList = obj.viewEnrolledStudents(semester, courseID);
    	
    	System.out.println("The enrolled students under" + courseID + " are : ");
    	
    	for(Student student : studentList) {
    		System.out.println(student.getUserId() + " : " + student.getName());
    	}
    	
    }

    public void addGrade() throws Exception {
    	
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.println("Enter the Student ID for the student: ");
    	String studentID = sc.next();
    	
    	System.out.println("Enter the Course ID for the student: ");
    	String courseID = sc.next();
    	
    	System.out.println("Enter the grade : ");
    	String grade = sc.next();
    	
    	obj.addGrade(studentID, courseID, Grade.valueOf(grade));
    	System.out.println("Grade adeed succesfully");
    	
    }

    public void viewAssignedCourses(String professorId) throws Exception {
    
    	List<Course> courseTaken = null;
    	courseTaken = obj.viewCoursesTaken(professorId);	
    	
    	System.out.println("Course under the professor with professor ID " + professorId + " are : ");
    	for(Course course : courseTaken) {
    		System.out.println(course.getCourseCode() + " : " + course.getName());
    	}
    			
    			
    }
}
