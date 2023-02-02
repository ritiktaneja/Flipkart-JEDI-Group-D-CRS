package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.constants.Grade;
import com.flipkart.data.MockDB;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class StudentOperations extends UserOperations implements StudentServices{


   private boolean addCourse(Student student, Course course) throws RuntimeException {

        initStudSem(student);
        if(MockDB.registeredCourses.get(student).size() >= 10) {
            throw new RuntimeException("Cant add more courses. Max Limit : 10");
        }
        RegisteredCourse.RegisteredCourseBuilder builder = new RegisteredCourse.RegisteredCourseBuilder();
        builder.setStudent(student);
        builder.setCourse(course);
        builder.setGrade(Grade.IN_PROGRESS);

        MockDB.registeredCourses.get(student).add(builder.build());
        return true;
    }
    private boolean dropCourse(RegisteredCourse registeredCourse) {


        Student student = registeredCourse.getStudent();
        initStudSem(student);
        if(MockDB.registeredCourses.get(student).remove(registeredCourse));
            return true;
    }

    private List<RegisteredCourse> viewRegisteredCourses(Student student)
    {
        initStudSem(student);
        List<RegisteredCourse> registeredCourses = new ArrayList<>();
        for(RegisteredCourse rc : MockDB.registeredCourses.get(student)) {
            registeredCourses.add(rc);
        }
        return registeredCourses;
    }

    private long calculateFee(Student student) {

        initStudSem(student);
        long fees = 0;
        long courseCnt = MockDB.registeredCourses.get(student).size();
        fees = courseCnt * 100;
        return  fees;
    }
//    public boolean getRegistrationStatus(Student student) {
//       // TODO
//       return true;
//    }

   private void initStudSem(Student student) {


       if(MockDB.registeredCourses.get(student) == null) {
           MockDB.registeredCourses.put(student, new HashSet<>());
       }
   }
   

   private Student getStudentFromId(String id) {
       for(Student student : MockDB.students) {
           if(student.getStudentId().equals(id)) return student;
       }
       return null;
   }



}
