package com.flipkart.bean;

import com.flipkart.constants.Grade;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Grade Card
 */
public class GradeCard {

    @NotBlank
    private Student student;

    @NotNull
    private List<RegisteredCourse> registeredCourses;

    /**
     * Method to get Student
     * @return Student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Method to set Student
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Get list of registered course
     * @return Registered Courses
     */
    public List<RegisteredCourse> getRegisteredCourses() {
        return registeredCourses;
    }

    /**
     * Method to set Registered Courses
     * @param registeredCourses
     */
    public void setRegisteredCourses(List<RegisteredCourse> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }

    /**
     * Method to get CGPA
     * @return cgpa
     */
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
