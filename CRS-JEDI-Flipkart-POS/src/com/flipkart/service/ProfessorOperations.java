package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.data.MockDB;

import java.awt.dnd.DragGestureEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProfessorOperations extends UserOperations implements ProfessorServices {

    private static volatile ProfessorOperations instance = null;

    private ProfessorOperations() {

    }

    public static ProfessorOperations getInstance() {
        if (instance == null) {
            synchronized (ProfessorOperations.class) {
                instance = new ProfessorOperations();
            }
        }
        return instance;
    }

    @Override
    public boolean addGrade(RegisteredCourse course) throws Exception {

        return true;
    }

    public List<Student> viewEnrolledStudents(Course course) throws Exception {
        List<Student> students = new ArrayList<>();

        for (Map.Entry<Semester, Map<Student, Set<RegisteredCourse>>> e : MockDB.registeredCourses.entrySet()) {
            for (Map.Entry<Student, Set<RegisteredCourse>> y : e.getValue().entrySet()) {
                if (y.getValue().contains(course)) {
                    students.add(y.getKey());
                }
            }
        }
        return students;
    }

    @Override
    public List<Course> viewCoursesTaken(Professor professor) throws Exception {
        List<Course> list = new ArrayList<>();
        for (CourseCatalog c : MockDB.catalogs) {
            for (Course course : c.getCourseList()) {
                if (course.professor == professor) {
                    list.add(course);
                }
            }
        }
        return list;
    }

    @Override
    public boolean registerForCourse(Professor professor, Course course) throws Exception {
        if (course.professor == null) {
            course.professor = professor;
            System.out.println(course.getName() + " is Successfully assigned to " + professor.getName());
            return true;
        }
        System.out.println("Error -> " + course.getName() + " is already assigned to " + professor.getName());
        return false;
    }

}
