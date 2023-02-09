package com.flipkart.bean;

import com.flipkart.constants.CRSColors;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Course Class
 */
public class Course {

    @NotBlank
    private String courseCode;

    @NotBlank
    private String Name;

    @NotBlank
    private Professor professor;

    /**
     * Method to course code
     * @return Course Code
     */
    public String getCourseCode() {
        return courseCode;
    }

    @Override
    public String toString() {
        return "Course Name = " + this.Name + ", Course code = " + this.courseCode + ", Professor =  " +
                this.getProfessor();
    }

    /**
     * Method to set Course Code
     * @param courseCode
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Method to get Course Name
     * @return Course Name
     */
    public String getName() {
        return Name;
    }

    /**
     * Method to set Name
     * @param name
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * Method to get Professor Name
     * @return Professor Name
     */
    public Professor getProfessor() {
        return professor;
    }

    /**
     * Method to set Professor Name
     * @param professor
     */
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    /**
     * Method to check object belongs to same class
     * @param obj
     * @return If the object belongs to same class
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Course)) return false;

        Course course = (Course) obj;
        return this.getCourseCode() == course.getCourseCode();
    }

    /**
     * Print list of courses
     * @param header
     * @param courseList
     */
    public static void printCourseList(String header, List<Course> courseList) {
        String stars = "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
        System.out.println(stars);
        String namePlate = String.format("%45s", header);
        System.out.println(namePlate + "\n");
        String s = String.format("%-25s" + "%-25s" + "%-20s", "Course", "Course Code", "Professor");
        System.out.println(s+ "\n");

        for(Course c: courseList) {
            if (c.getProfessor() == null) {
                Professor.ProfessorBuilder builder = new Professor.ProfessorBuilder();
                builder.setName(CRSColors.RED + "NOT ASSIGNED TO PROFESSOR" + CRSColors.RESET);
                c.setProfessor(builder.build());
            }
            String stmt = String.format("%-25s" + "%-25s" + "%-20s", c.getName(), "" + c.getCourseCode() + "", c.getProfessor().getName());
            System.out.println(stmt);
        }

        System.out.println(stars+"\n");

    }
}
