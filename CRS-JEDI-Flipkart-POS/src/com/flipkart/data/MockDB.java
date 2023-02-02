package com.flipkart.data;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Semester;
import com.flipkart.bean.Student;

import java.util.*;

public class MockDB {
    public static Map<Semester, Map<Student, Set<RegisteredCourse>>> registeredCourses = new HashMap<>();
}
