package com.flipkart.bean;

import com.flipkart.constants.Department;

import java.util.List;

/**
 * Student Class
 */
public class Student extends User {

    private Department department;

    private boolean approvalStatus;
    private String semester;

    /**
     * Method to get student id
     *
     * @return student Id
     */
    public String getStudentId() {
        return super.getUserId();
    }

    /**
     * Method to get student department
     *
     * @return student department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * method to set student department
     *
     * @param department
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Method to get semester details of the current student
     *
     * @return
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Method to set semester for the current student
     *
     * @param semester
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * Method to check weather student is approved or not
     *
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
        return "Student Name : " + this.getName() + ", Id : " + this.getStudentId() + ", Registered for Semester : " + this.getSemester() + ", Department : " + this.getDepartment()
                ;
    }

    public static class StudentBuilder {
        private String studentId, name, password;
        private String semester;
        private Department department;
        private boolean approvalStatus;

        /**
         * Method to check Approval status of the current object
         *
         * @return status of approval
         */
        public boolean getApprovalStatus() {
            return approvalStatus;
        }

        /**
         * Method to set student id to the current object
         *
         * @param studentId
         */
        public StudentBuilder setStudentId(String studentId) {
            this.studentId = studentId;
            return this;
        }

        /**
         * Method to set student name to the current object
         *
         * @param name
         * @return object of student
         */
        public StudentBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Method to set password for the current object
         *
         * @param password
         * @return object of student
         */
        public StudentBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        /**
         * Method to set semester for the current object
         *
         * @param semester
         * @return object of student
         */
        public StudentBuilder setSemester(String semester) {
            this.semester = semester;
            return this;
        }

        /**
         * Method to set department for the current object
         *
         * @param department
         * @return object of student
         */
        public StudentBuilder setDepartment(Department department) {
            this.department = department;
            return this;
        }

        /**
         * Method to set approval status for the current object
         *
         * @param aStatus
         * @return object of student
         */
        public StudentBuilder setApprovalStatus(boolean aStatus) {
            this.approvalStatus = aStatus;
            return this;
        }

        /**
         * @return Current object
         */
        public Student build() {
            return new Student(this);
        }

    }

    /**
     * student list
     *
     * @param header
     * @param studentList
     */
    public static void printStudentList(String header, List<Student> studentList) {
        String stars = "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
        System.out.println(stars);
        String namePlate = String.format("%45s", header);
        System.out.println(namePlate + "\n");
        String s = String.format("%-25s" + "%-25s" + "%-20s", "Name", "ID", "Department");
        System.out.println(s + "\n");

        for (Student stud : studentList) {
            String stmt = String.format("%-25s" + "%-25s" + "%-20s", stud.getName(), "" + stud.getStudentId() + "", stud.getDepartment());
            System.out.println(stmt);
        }

        System.out.println(stars + "\n");

    }
}