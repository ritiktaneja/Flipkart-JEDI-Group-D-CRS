package com.flipkart.client;

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
    public void createMenu() throws Exception {

        System.out.println("********************* Welcome Professor *********************");
        
        System.out.println("Enter the Professor ID: ");
    	Scanner scc = new Scanner(System.in);
    	
    	professorId = scc.next();
        
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
    	//Show All courses
    	//Professor Selects a Course
    	//Display the student list in that course
    	//Get RegisteredOCurse Object for each of this
    	//The Professor selects the student from the list 
    	//Assigns the grade which is then set in Registered COurse Object
    	viewAssignedCourses(professorId);
    	System.out.println("Enter the Course code for which you want to enter grade of students: ");
    	String couCode = sc.next();
    	Map<Student, Set<RegisteredCourse>> tempMap = MockDB.registeredCourses;
    	List<Student> stu_list = new ArrayList<Student>();
    	for (var entry : tempMap.entrySet()) {
    		Set<RegisteredCourse> iterr = entry.getValue();
            Iterator<RegisteredCourse> value = iterr.iterator();
            while (value.hasNext()) {
            	RegisteredCourse obj = (RegisteredCourse) value.next();
            	if(obj.getCourse().getCourseCode().equals(couCode))
            	{
            		stu_list.add(entry.getKey());
            	}
            }
    	}
    	
    	System.out.println("Student List: ");
    	Iterator<Student> i=stu_list.iterator();
        while(i.hasNext()){
      	  
      	  Student test=i.next();
      	  System.out.println(test);
        }
        
        while(true)
        {
	        System.out.println("Enter the student ID for which you want to add Grade: (-1 to abort)");
	        String studentId = sc.next();
	        if(studentId.equals("-1"))
	        	break;
	        System.out.println("Enter Grade: ");
	        String grade = sc.next();
	        obj.addGrade(studentId, grade, couCode);
        } 	
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
