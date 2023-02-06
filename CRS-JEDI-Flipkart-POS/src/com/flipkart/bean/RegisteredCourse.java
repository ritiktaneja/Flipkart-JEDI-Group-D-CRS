package com.flipkart.bean;

import com.flipkart.constants.Grade;

import java.util.UUID;

public class RegisteredCourse {
  //  public int semester;

    public String getRegisteredCourseId() {
        return registeredCourseId;
    }

    public void setRegisteredCourseId(String registeredCourseId) {
        this.registeredCourseId = registeredCourseId;
    }

    private String registeredCourseId;
    private static int index = 0;
    private Course course;
    private Student student;

    private Grade grade;

    /**
     *
     * @return Course
     */
    public Course getCourse() {
        return course;
    }

    /**
     *
     * @return student
     */
    public Student getStudent() {
        return student;
    }

    @Override
    public String toString() {
        return "Course Name = " + this.course.getName() + ", course code = " + this.course.getCourseCode() + " professor =  " +
                this.course.getProfessor();
    }

    /**
     *
     * @return grade
     */
    public Grade getGrade() {
        return grade;
    }

    /**
     *
     * @param grade
     */
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    /**
     * Drop This Course
     */
    public boolean dropCourse() {
        this.grade = Grade.DROPPED;
        return true;
    }

    /**
     *
     * @param builder
     */
    private RegisteredCourse(RegisteredCourseBuilder builder) {
        this.course = builder.course;
        this.student = builder.student;

        this.grade = builder.grade;
        this.registeredCourseId = Integer.toString(index++);
    }

    /**
     *
     * @param object
     * @return
     */
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

        /**
         * Set Course
         * @param course
         */
        public RegisteredCourseBuilder setCourse(Course course) { this.course = course; return this; }

        /**
         * Set Student
         * @param student
         */
        public RegisteredCourseBuilder setStudent(Student student) {this.student = student; return this;}

        /**
         * Set Grade
         * @param grade
         */
        public RegisteredCourseBuilder setGrade(Grade grade) {this.grade = grade; return this;}
        public RegisteredCourse build() {
            return new RegisteredCourse(this);
        }

    }


}
