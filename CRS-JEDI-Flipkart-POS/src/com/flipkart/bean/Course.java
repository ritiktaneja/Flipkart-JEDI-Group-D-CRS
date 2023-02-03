package com.flipkart.bean;

public class Course {
    public String courseCode;
    public String Name;
    public Professor professor;

    public String getCourseCode() {
        return courseCode;
    }

    @Override
    public String toString() {
        return "Course Name = " + this.Name + ", Course code = " + this.courseCode + ", Professor =  " +
                this.getProfessor();
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Course)) return false;

        Course course = (Course) obj;
        return this.getCourseCode() == course.getCourseCode();
    }
}
