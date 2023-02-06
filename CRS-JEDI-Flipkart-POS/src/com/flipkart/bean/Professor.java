package com.flipkart.bean;

import com.flipkart.constants.Department;
import com.flipkart.constants.Designation;

public class Professor extends User {

    private Department department;
    private Designation designation;

    /**
     * Method to get Faculty Id
     * @return User Id
     */
    public String getFacultyId() {
        return super.getUserId();
    }

    /**
     * Method to get Department
     * @return Department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Method to get Professor Designation
     * @return Designation
     */
    public Designation getDesignation() {
        return designation;
    }

    /**
     * Parameterized constructor
     * @param department: Department
     * @param designation: Designation
     */
    private Professor(ProfessorBuilder builder) {
        super(builder.facultyId, builder.name, builder.password);
        this.department = builder.department;
        this.designation = builder.designation;
    }

    @Override
    public String toString() {
        return "Professor name = " + this.getName() + ", ID = " + this.getFacultyId() + ", Password =  " + this.getPassword()
                ;
    }

    /**
     * Professor Builder Class
     */
    public static class ProfessorBuilder {
        private String facultyId, name, password;
        private Department department;
        private Designation designation;

        /**
         *
         * @param facultyId
         */
        public ProfessorBuilder setFacultyId(String facultyId) {
            this.facultyId = facultyId;
            return this;
        }

        /**
         *
         * @param name
         */
        public ProfessorBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         *
         * @param password
         */
        public ProfessorBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        /**
         *
         * @param department
         */
        public ProfessorBuilder setDepartment(Department department) {
            this.department = department;
            return this;
        }

        /**
         *
         * @param designation
         */
        public ProfessorBuilder setDesignation(Designation designation) {
            this.designation = designation;
            return this;
        }

        public Professor build() {
            return new Professor(this);
        }
    }
}

