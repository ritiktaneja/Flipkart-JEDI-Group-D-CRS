package com.flipkart.bean;

import com.flipkart.constants.Department;
import com.flipkart.constants.Designation;

public class Professor extends User {


    private Department department;
    private Designation designation;

    public String getFacultyId() {
        return super.getUserId();
    }

    public Department getDepartment() {
        return department;
    }

    public Designation getDesignation() {
        return designation;
    }

    private Professor(ProfessorBuilder builder) {
         super(builder.facultyId, builder.name, builder.password);
         this.department = builder.department;
         this.designation = builder.designation;
    }

    public static class ProfessorBuilder {
        private String facultyId, name, password;
        private Department department;
        private Designation designation;

        public ProfessorBuilder setFacultyId(String facultyId) {this.facultyId = facultyId; return this;}
        public ProfessorBuilder setName(String name) {this.name = name; return this;}
        public ProfessorBuilder setPassword(String password) {this.password = password; return this;}
        public ProfessorBuilder setDepartment(Department department) {this.department = department; return this;}
        public ProfessorBuilder setDesignation(Designation designation) {this.designation = designation; return this;}

        public Professor build() {
            return new Professor(this);
        }
    }
}

