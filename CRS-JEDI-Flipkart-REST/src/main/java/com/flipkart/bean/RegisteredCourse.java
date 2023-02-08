package com.flipkart.bean;

import com.flipkart.constants.CRSColors;
import com.flipkart.constants.Grade;

import java.util.List;
import java.util.UUID;

/**
 * Registered class
 */
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
     * Get course object
     *
     * @return Course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Method to get student object
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
     * Method to get grade of the current course
     *
     * @return grade
     */
    public Grade getGrade() {
        return grade;
    }

    /**
     * Method to set grade for the current object
     *
     * @param grade
     */
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    /**
     * Drop current Course
     */
    public boolean dropCourse() {
        this.grade = Grade.DROPPED;
        return true;
    }

    /**
     * Course builder class
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
     * Check if the object belongs to the current class
     *
     * @param object
     * @return status of registration
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof RegisteredCourse)) {
            return false;
        }
        RegisteredCourse registeredCourse = (RegisteredCourse) (object);
        return this.getStudent() == registeredCourse.getStudent() && this.getCourse() == registeredCourse.getCourse();
    }

    public static class RegisteredCourseBuilder {
        private Course course;
        private Student student;
        private Grade grade = Grade.IN_PROGRESS;

        /**
         * Set Course
         *
         * @param course
         */
        public RegisteredCourseBuilder setCourse(Course course) {
            this.course = course;
            return this;
        }

        /**
         * Set Student
         *
         * @param student
         */
        public RegisteredCourseBuilder setStudent(Student student) {
            this.student = student;
            return this;
        }

        /**
         * Set Grade
         *
         * @param grade
         */
        public RegisteredCourseBuilder setGrade(Grade grade) {
            this.grade = grade;
            return this;
        }

        public RegisteredCourse build() {
            return new RegisteredCourse(this);
        }

    }

    /**
     * Print all the registered courses
     *
     * @param header
     * @param courseList
     */
    public static void printRegisteredCourseList(String header, List<RegisteredCourse> courseList) {
        String stars = "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
        System.out.println(stars);
        String namePlate = String.format("%45s", header);

        System.out.println(namePlate + "\n");

        String s = String.format("%-25s" + "%-15s" + "%-20s" + "%-10s", "Course", "Course Code", "Professor", "Grade");

        System.out.println(s);
        System.out.println("");
        for (RegisteredCourse c : courseList) {
            if (c.getCourse().getProfessor() == null) {
                Professor.ProfessorBuilder builder = new Professor.ProfessorBuilder();
                builder.setName(CRSColors.RED + "NOT ASSIGNED TO PROFESSOR" + CRSColors.RESET);
                c.getCourse().setProfessor(builder.build());
            }
            String stmt = String.format("%-25s" + "%-15s" + "%-20s" + "%-10s", c.getCourse().getName(), c.getCourse().getCourseCode(), c.getCourse().getProfessor().getName(), c.getGrade().toString());
            System.out.println(stmt);
        }

        System.out.println(stars + "\n");

    }

}
