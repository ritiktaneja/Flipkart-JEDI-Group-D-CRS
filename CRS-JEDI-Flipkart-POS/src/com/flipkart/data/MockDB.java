package com.flipkart.data;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Semester;
import com.flipkart.bean.Student;

import java.util.*;

public class MockDB {
    public static Map<Semester, Map<Student, Set<RegisteredCourse>>> registeredCourses = new HashMap<>();
    public static List<Student> students = new ArrayList<>();
    public static List<Semester> semesters = new ArrayList<>();
    public static List<Course> courses = new ArrayList<>();


    


}
