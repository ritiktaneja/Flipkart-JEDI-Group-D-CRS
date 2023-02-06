package com.flipkart.bean;

import com.flipkart.constants.Department;
import com.flipkart.constants.Designation;

public class Professor extends User {


    private Department department;
    private Designation designation;

    //Getting the faculty id
    public String getFacultyId() {
        return super.getUserId();
    }

    //getting the professor department
    public Department getDepartment() {
        return department;
    }

    //getting the designation of the current professor
    public Designation getDesignation() {
        return designation;
    }

    //Assigning the given inputs from the scanner
    private Professor(ProfessorBuilder builder) {
        super(builder.facultyId, builder.name, builder.password);
        this.department = builder.department;
        this.designation = builder.designation;
    }

    @Override
    public String toString() {
        //Printing the current object of the class
        return "Professor name = " + this.getName() + ", ID = " + this.getFacultyId() ;
    }

    public static class ProfessorBuilder {
        private String facultyId, name, password;
        private Department department;
        private Designation designation;

        //Assigning the object value
        public ProfessorBuilder setFacultyId(String facultyId) {
            this.facultyId = facultyId;
            return this;
        }

        public ProfessorBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ProfessorBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public ProfessorBuilder setDepartment(Department department) {
            this.department = department;
            return this;
        }

        public ProfessorBuilder setDesignation(Designation designation) {
            this.designation = designation;
            return this;
        }

        public Professor build() {
            return new Professor(this);
        }
    }
}

