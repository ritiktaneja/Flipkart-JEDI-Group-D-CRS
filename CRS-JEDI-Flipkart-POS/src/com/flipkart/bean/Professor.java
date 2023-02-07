package com.flipkart.bean;

import com.flipkart.constants.Department;
import com.flipkart.constants.Designation;

public class Professor extends User {


    private Department department;
    private Designation designation;

    /**
     * Method to Faculty ID
     * @return faculty Id
     */
    public String getFacultyId() {
        return super.getUserId();
    }

    /**
     * Method to get Department
     * @return Professor department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Method to get professor designation
     * @return
     */
    public Designation getDesignation() {
        return designation;
    }

    /**
     * Professor Builder Function
     * @param builder
     */
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

    /**
     * Professor builder class
     */
    public static class ProfessorBuilder {
        private String facultyId, name, password;
        private Department department;
        private Designation designation;

        /**
         * Set the faculty Id to current object
         * @param facultyId
         */
        public ProfessorBuilder setFacultyId(String facultyId) {
            this.facultyId = facultyId;
            return this;
        }

        /**
         * Set the Professor name to the current object
         * @param name
         */
        public ProfessorBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Set password for the current object
         * @param password
         * @return
         */
        public ProfessorBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        /**
         * Assign department to the current professor
         * @param department
         */
        public ProfessorBuilder setDepartment(Department department) {
            this.department = department;
            return this;
        }

        /**
         * Set designation for the current object
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

