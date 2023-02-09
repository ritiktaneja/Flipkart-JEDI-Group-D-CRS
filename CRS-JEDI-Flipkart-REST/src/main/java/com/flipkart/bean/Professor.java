package com.flipkart.bean;

import com.flipkart.constants.Department;
import com.flipkart.constants.Designation;


import java.util.List;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
/**
 * Professor class
 */
public class Professor extends User {


    @NotNull
    private Department department;

    @NotNull
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
     * @return object of designation
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
        if(builder.designation != null)
            this.designation = builder.designation;
        else
            this.designation = Designation.PROFESSOR;
    }

    @Override
    public String toString() {
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
         * @return object of professor
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

        /**
         *
         * @return object of professor
         */
        public Professor build() {
            return new Professor(this);
        }

    }

    /**
     * Print list of professsor
     * @param header
     * @param professorList
     */
    public static void printProfessorList(String header, List<Professor> professorList) {
        String stars = "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
        System.out.println(stars);
        String namePlate = String.format("%45s", header);
        System.out.println(namePlate + "\n");
        String s = String.format("%-25s" + "%-25s" + "%-20s", "Name", "ID", "Department");
        System.out.println(s+ "\n");

        for(Professor professor: professorList) {
            String stmt = String.format("%-25s" + "%-25s" + "%-20s", professor.getName(), "" + professor.getFacultyId() + "", professor.getDepartment());
            System.out.println(stmt);
        }

        System.out.println(stars+"\n");

    }
}

