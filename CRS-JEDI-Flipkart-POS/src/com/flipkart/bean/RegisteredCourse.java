package com.flipkart.bean;

import com.flipkart.constants.Grade;

import java.util.List;

public class RegisteredCourse {
    public int semester;

    public Course course;
    public Student student;

    public Grade grade;


    public Grade getGrade() {
        return grade;
    }
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public boolean dropCourse() {
        this.grade = Grade.DROPPED;
        return true;
    }


}
