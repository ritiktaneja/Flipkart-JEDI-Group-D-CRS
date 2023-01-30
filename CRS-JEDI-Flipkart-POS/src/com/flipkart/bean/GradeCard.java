package com.flipkart.bean;

import com.flipkart.constants.Grade;

import java.util.List;

public class GradeCard {
    private Student student;
    private List<RegisteredCourse> registeredCourses;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<RegisteredCourse> getRegisteredCourses() {
        return registeredCourses;
    }

    public void setRegisteredCourses(List<RegisteredCourse> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }

    public float calculateCGPA() {
        float totalScore = 0;
        int totalCompletedCourse = 0;
        for(RegisteredCourse registeredCourse : registeredCourses) {
            if(registeredCourse.getGrade() != Grade.DROPPED && registeredCourse.getGrade() != Grade.IN_PROGRESS)
            {
                totalScore += registeredCourse.getGrade().getValue();
                totalCompletedCourse += 1;
            }
        }
        float CGPA = (totalCompletedCourse > 0) ?  totalScore / totalCompletedCourse : 0;
        return CGPA;
    }
}
