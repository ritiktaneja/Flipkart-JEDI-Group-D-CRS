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

    public Department getDepartment() {
        return department;
    }

    public Designation getDesignation() {
        return designation;
    }

    private Professor(ProfessorBuilder builder) {
         super(builder.userId, builder.name, builder.password);
         this.facultyId = builder.userId;
         this.department = builder.department;
         this.designation = builder.designation;
    }

    public class ProfessorBuilder {
        private String userId, name, password;
        private Department department;
        private Designation designation;

        public ProfessorBuilder setUserId(String userId) {this.userId = userId; return this;}
        public ProfessorBuilder setName(String name) {this.name = name; return this;}
        public ProfessorBuilder setPassword(String password) {this.password = password; return this;}
        public ProfessorBuilder setDepartment(Department department) {this.department = department; return this;}
        public ProfessorBuilder setDesignation(Designation designation) {this.designation = designation; return this;}

        public Professor build() {
            return new Professor(this);
        }
    }
}

