package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.constants.Grade;

import java.util.List;

public class ProfessorImpl implements ProfessorInterface {

    private static volatile ProfessorImpl instance = null;

    private ProfessorImpl() {

    }

    public static ProfessorImpl getInstance() {
        if(instance == null) {
            synchronized (ProfessorImpl.class){
                instance = new ProfessorImpl();
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
