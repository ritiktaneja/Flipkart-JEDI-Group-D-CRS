package com.flipkart.bean;

import com.flipkart.constants.Department;

public class Student extends User {

    private Department department;
    private int semester;

    public String getStudentId() {
        return super.getUserId();
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    private Student(StudentBuilder builder) {
        super(builder.studentId, builder.name, builder.password);
        this.semester = builder.semester;
        this.department = builder.department;
    }

    @Override
    public String toString() {
        return "Student name = " + this.getName() + ", id = " + this.getStudentId() + ", batch =  " + this.getSemester() + ", Department = " + this.getDepartment()
        ;
    }

    public static class StudentBuilder {
        private String studentId, name, password;
        private int semester;
        private Department department;

        public StudentBuilder setStudentId(String studentId) {
            this.studentId = studentId;
            return this;
        }

        public StudentBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public StudentBuilder setSemester(int semester) {
            this.semester = semester;
            return this;
        }

        public StudentBuilder setDepartment(Department department) {
            this.department = department;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}