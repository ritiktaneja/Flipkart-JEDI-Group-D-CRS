package com.flipkart.bean;

import com.flipkart.constants.Department;

public class Student extends User {

    private Department department;

    private boolean approvalStatus;
    private String semester;

    /**
     * Get Student Id
     * @return Student Id
     */
    public String getStudentId() {
        return super.getUserId();
    }

    /**
     *
     * @return department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     *
     * @param department
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     *
     * @return semester
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Set semester
     * @param semester
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * Get Course Approval status
     */
    public boolean isApproved() {return this.approvalStatus; }
    public void approve() {this.approvalStatus = true; }

    /**
     *
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

    /**
     * Student Builder Class
     */
    public static class StudentBuilder {
        private String studentId, name, password;
        private String semester;
        private Department department;
        private boolean approvalStatus;

        /**
         * Set Student Id
         * @param studentId
         */
        public StudentBuilder setStudentId(String studentId) {
            this.studentId = studentId;
            return this;
        }

        /**
         * Set Student Id
         * @param name
         */
        public StudentBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Set Password
         * @param password
         */
        public StudentBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        /**
         * Set Semester
         * @param semester
         */
        public StudentBuilder setSemester(String semester) {
            this.semester = semester;
            return this;
        }

        /**
         * Set Department
         * @param department
         */
        public StudentBuilder setDepartment(Department department) {
            this.department = department;
            return this;
        }

        /**
         * Set Approval Status
         * @param aStatus
         */
        public StudentBuilder setApprovalStatus(boolean aStatus) {
        	this.approvalStatus = aStatus;
        	return this;
        }
        public Student build() {
            return new Student(this);
        }
    }
}