package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;

import java.util.List;

public class ProfessorOperations extends UserOperations implements ProfessorServices {

    private static volatile ProfessorOperations instance = null;

    private ProfessorOperations() {

    }

    public static ProfessorOperations getInstance() {
        if(instance == null) {
            synchronized (ProfessorOperations.class){
                instance = new ProfessorOperations();
            }
        }
        return instance;
    }

    @Override
    public boolean addGrade(RegisteredCourse course) throws Exception{
        // dao call
        return true;
    }

    public List<Student> viewEnrolledStudents(Course course) throws Exception {
        return null;
    }

    public List<Course> viewCoursesTaken() throws Exception{
        return null;
    }

}
