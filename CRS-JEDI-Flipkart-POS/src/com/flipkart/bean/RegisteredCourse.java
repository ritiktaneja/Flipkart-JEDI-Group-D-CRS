package com.flipkart.bean;

import com.flipkart.constants.Grade;

import java.util.UUID;

public class RegisteredCourse {
  //  public int semester;

    private String registeredCourseId;
    private static int index = 0;
    private Course course;
    private Student student;
    private Semester semester;
    private Grade grade;

    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    public Semester getSemester() {
        return semester;
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
        this.semester = builder.semester;
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
        return this.getSemester() == registeredCourse.getSemester() && this.getStudent() == registeredCourse.getStudent() && this.getCourse() == registeredCourse.getCourse();
    }

    public static class RegisteredCourseBuilder {
        private Course course;
        private Student student;
        private Semester semester;
        private Grade grade = Grade.IN_PROGRESS;



        public RegisteredCourseBuilder setCourse(Course course) { this.course = course; return this; }
        public RegisteredCourseBuilder setStudent(Student student) {this.student = student; return this;}
        public RegisteredCourseBuilder setSemester(Semester semester) {this.semester = semester; return this;}
        public RegisteredCourseBuilder setGrade(Grade grade) {this.grade = grade; return this;}
        public RegisteredCourse build() {
            return new RegisteredCourse(this);
        }

    }


}
