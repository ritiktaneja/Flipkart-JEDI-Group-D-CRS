package com.flipkart.bean;

import com.flipkart.constants.Department;

public class Student extends User{

    private Department department;
    private int batch;
    public String getStudentId() {
        return super.getUserId();
    }

    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public int getBatch() {
        return batch;
    }
    public void setBatch(int batch) {
        this.batch = batch;
    }

    private Student(StudentBuilder builder) {
        super(builder.studentId, builder.name, builder.password);
        this.batch = builder.batch;
        this.department = builder.department;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static class StudentBuilder {
        private String studentId, name, password;
        private int batch;
        private Department department;
        public StudentBuilder setStudentId(String studentId) { this.studentId = studentId; return this; }

        public StudentBuilder setName(String name) {this.name = name; return this;}
        public StudentBuilder setPassword(String password) {this.password = password; return this;}

        public StudentBuilder setBatch(int batch) {this.batch = batch; return this;}
        public StudentBuilder setDepartment(Department department){this.department = department; return this;}

        public Student build() {
            return new Student(this);
        }
    }
}