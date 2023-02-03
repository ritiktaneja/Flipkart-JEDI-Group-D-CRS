package com.flipkart.bean;

import com.flipkart.constants.Grade;

import java.util.UUID;

public class RegisteredCourse {
  //  public int semester;

    private String registeredCourseId;
    private static int index = 0;
    private Course course;
    private Student student;

    private Grade grade;

    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    @Override
    public String toString() {
        return "Course Name = " + this.course.getName() + ", course code = " + this.course.getCourseCode() + " professor =  " +
                this.course.getProfessor();
    }

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

    private RegisteredCourse(RegisteredCourseBuilder builder) {
        this.course = builder.course;
        this.student = builder.student;

        this.grade = builder.grade;
        this.registeredCourseId = Integer.toString(index++);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if(!(object instanceof RegisteredCourse)) {
            return false;
        }
        RegisteredCourse registeredCourse = (RegisteredCourse) (object);
        return  this.getStudent() == registeredCourse.getStudent() && this.getCourse() == registeredCourse.getCourse();
    }

    public static class RegisteredCourseBuilder {
        private Course course;
        private Student student;
        private Grade grade = Grade.IN_PROGRESS;



        public RegisteredCourseBuilder setCourse(Course course) { this.course = course; return this; }
        public RegisteredCourseBuilder setStudent(Student student) {this.student = student; return this;}
       
        public RegisteredCourseBuilder setGrade(Grade grade) {this.grade = grade; return this;}
        public RegisteredCourse build() {
            return new RegisteredCourse(this);
        }

    }


}
