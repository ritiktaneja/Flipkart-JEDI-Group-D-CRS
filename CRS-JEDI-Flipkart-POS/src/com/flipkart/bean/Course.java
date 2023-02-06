package com.flipkart.bean;

public class Course {
    private String courseCode;
    private String Name;
    private Professor professor;

    /**
     *
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
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Course)) return false;

        Course course = (Course) obj;
        return this.getCourseCode() == course.getCourseCode();
    }
}
