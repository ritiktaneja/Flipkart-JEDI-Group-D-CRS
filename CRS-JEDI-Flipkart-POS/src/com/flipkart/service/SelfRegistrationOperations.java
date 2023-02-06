package com.flipkart.service;

import com.flipkart.bean.Student;
import com.flipkart.constants.Department;
import com.flipkart.dao.StudentDao;
import com.flipkart.data.MockDB;

import java.util.Map;
import java.util.UUID;

public class SelfRegistrationOperations implements SelfRegistrationService {


    public Student selfRegister(String name, String password, String semester, String department) {
        Student.StudentBuilder studentBuilder = new Student.StudentBuilder();
        studentBuilder.setName(name);
        studentBuilder.setPassword(password);
        studentBuilder.setSemester(semester);
        studentBuilder.setDepartment(Department.valueOf(department));
        String studentId = name + (int  ) ((Math.random() * 10000) % 10000);
        studentBuilder.setStudentId(studentId);
        studentBuilder.setApprovalStatus(false); // Waiting for admin approval

        Student student = studentBuilder.build();
        StudentDao dao = StudentDao.getInstance();
        dao.insert(student);
        return student;
    }

}
