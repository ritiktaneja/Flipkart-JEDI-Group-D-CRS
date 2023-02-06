package com.flipkart.service;

import com.flipkart.bean.Student;
import com.flipkart.constants.Department;
import com.flipkart.data.MockDB;

public class SelfRegistrationOperations implements SelfRegistrationService{

    private static  int SID = 5000;

    /**
     *
     * @param name
     * @param password
     * @param semester
     * @param department
     * @return Student
     */
    public Student selfRegister(String name, String password, String semester, String department) {
        Student.StudentBuilder studentBuilder = new Student.StudentBuilder();
        studentBuilder.setName(name);
        studentBuilder.setPassword(password);
        studentBuilder.setSemester(semester);
        studentBuilder.setDepartment(Department.valueOf(department));
        studentBuilder.setStudentId("ST-"+SID);
        studentBuilder.setApprovalStatus(false); // Waiting for admin approval
        Student student = studentBuilder.build();
        System.out.println(student);
        MockDB.students.add(student);
        SID++;
        return student;
    }

}
