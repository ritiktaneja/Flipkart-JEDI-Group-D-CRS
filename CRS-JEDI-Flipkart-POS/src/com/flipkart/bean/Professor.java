package com.flipkart.bean;

import com.flipkart.constants.Department;
import com.flipkart.constants.Designation;

import java.util.ArrayList;
import java.util.List;

public class Professor extends User {

    private String facultyId;
    private Department department;
    private Designation designation;

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }


}

