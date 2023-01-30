package com.flipkart.bean;

import com.flipkart.constants.Department;

public class Student extends User{
    private String studentId;

    private Department department;

    private int batch;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public void getCourseDetails() {
        // TODO
    }
    public void payFees() {
        // TODO
    }

}
