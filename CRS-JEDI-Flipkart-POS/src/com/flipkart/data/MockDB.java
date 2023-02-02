package com.flipkart.data;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Semester;
import com.flipkart.bean.Student;

import java.util.*;

import com.flipkart.constants.Department;

public class MockDB {
    public static Map<Semester, Map<Student, Set<RegisteredCourse>>> registeredCourses = new HashMap<>();
    public static List<Student> students = new ArrayList<>();
    public static List<Semester> semesters = new ArrayList<>();
    public static List<Course> courses = new ArrayList<>();

    public static void populateStudent() {


<<<<<<< HEAD
=======
        Student.StudentBuilder studentBuilder = new Student.StudentBuilder();
        ArrayList<String> names = new ArrayList<>(Arrays.asList("Geeks", "Jean", "John", "Francis", "Daniel", "Dave", "Seth", "Wade", "Miles", "Brain", "Ben", "parker"));
        ArrayList<Department> departments = new ArrayList<>(Arrays.asList(Department.IT, Department.CIVIL, Department.CSE, Department.CIVIL, Department.EEE, Department.MECH));
        int id = 20231010;
        for (int i = 0; i < names.size(); i++) {
            studentBuilder.setName(names.get(i));
            studentBuilder.setBatch(2023);
            studentBuilder.setDepartment(departments.get(i % 7));
            students.add(studentBuilder.build());
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public static void populateSemester() {

        Semester semester = new Semester();
        s.
    }

    public static void main(String[] args) {
        populateStudent();
    }
>>>>>>> 294ddec (adding student populate function)


}
