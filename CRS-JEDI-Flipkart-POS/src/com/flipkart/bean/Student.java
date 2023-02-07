package com.flipkart.bean;

import com.flipkart.constants.Department;

public class Student extends User {

    private Department department;

    private boolean approvalStatus;
    private String semester;

    /**
     * Method to get student id
     * @return student Id
     */
    public String getStudentId() {
        return super.getUserId();
    }

    /**
     * Method to get student department
     * @return student department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * method to set student department
     * @param department
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Method to get semester details of the current student
     * @return
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Method to set semester for the current student
     * @param semester
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * Method to check weather student is approved or not
     * @return Approval status
     */
    public boolean isApproved() {
        return this.approvalStatus;
    }

    /**
     * Method to set student approval status
     */
    public void approve() {
        this.approvalStatus = true;
    }

    /**
     * Student builder function
     * @param builder
     */
    private Student(StudentBuilder builder) {
        super(builder.studentId, builder.name, builder.password);
        this.semester = builder.semester;
        this.department = builder.department;
        this.approvalStatus = builder.approvalStatus;
    }

    @Override
    public String toString() {
        return "Student name = " + this.getName() + ", id = " + this.getStudentId() + ", batch =  " + this.getSemester() + ", Department = " + this.getDepartment()
                ;
    }

    public static class StudentBuilder {
        private String studentId, name, password;
        private String semester;
        private Department department;
        private boolean approvalStatus;

        /**
         * Method to check Approval status of the current object
         * @return
         */
        public boolean getApprovalStatus() {
            return approvalStatus;
        }

        /**
         * Method to set student id to the current object
         * @param studentId
         */
        public StudentBuilder setStudentId(String studentId) {
            this.studentId = studentId;
            return this;
        }

        /**
         * Method to set student name to the current object
         * @param name
         * @return
         */
        public StudentBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Method to set password for the current object
         * @param password
         */
        public StudentBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        /**
         * Method to set semester for the current object
         * @param semester
         */
        public StudentBuilder setSemester(String semester) {
            this.semester = semester;
            return this;
        }

        /**
         * Method to set department for the current object
         * @param department
         */
        public StudentBuilder setDepartment(Department department) {
            this.department = department;
            return this;
        }

        /**
         * Method to set approval status for the current object
         */
        public StudentBuilder setApprovalStatus(boolean aStatus) {
            this.approvalStatus = aStatus;
            return this;
        }

        /**
         *
         * @return Current object
         */
        public Student build() {
            return new Student(this);
        }
    }
}