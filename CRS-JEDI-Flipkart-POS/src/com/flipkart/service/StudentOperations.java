package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Semester;
import com.flipkart.bean.Student;
import com.flipkart.constants.Grade;
import com.flipkart.data.MockDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class StudentOperations extends UserOperations implements StudentServices{


   public boolean addCourse(Student student, Semester semester, Course course) throws RuntimeException {

        initStudSem(student, semester);
        if(MockDB.registeredCourses.get(semester).get(student).size() >= 10) {
            throw new RuntimeException("Cant add more courses. Max Limit : 10");
        }
        RegisteredCourse.RegisteredCourseBuilder builder = new RegisteredCourse.RegisteredCourseBuilder();
        builder.setStudent(student);
        builder.setCourse(course);
        builder.setGrade(Grade.IN_PROGRESS);
        builder.setSemester(semester);
        MockDB.registeredCourses.get(semester).get(student).add(builder.build());
        return true;
    }
    public boolean dropCourse(RegisteredCourse registeredCourse) {

        Semester semester = registeredCourse.getSemester();
        Student student = registeredCourse.getStudent();
        initStudSem(student, semester);
        if(MockDB.registeredCourses.get(semester).get(student).remove(registeredCourse));
            return true;
    }

    public List<RegisteredCourse> viewRegisteredCourses(Student student, Semester semester)
    {
        initStudSem(student, semester);
        List<RegisteredCourse> registeredCourses = new ArrayList<>();
        for(RegisteredCourse rc : MockDB.registeredCourses.get(semester).get(student)) {
            registeredCourses.add(rc);
        }
        return registeredCourses;
    }

    public long calculateFee(Student student, Semester semester) {
        initStudSem(student, semester);
        long fees = 0;
        long courseCnt = MockDB.registeredCourses.get(semester).get(student).size();
        fees = courseCnt * 100;
        return  fees;
    }
    @Override
    public boolean getRegistrationStatus(Student student) {
       // TODO
       return true;
    }

   private void initStudSem(Student student, Semester semester) {

       if(MockDB.registeredCourses.get(semester) == null) {
           MockDB.registeredCourses.put(semester, new HashMap<>());
       }
       if(MockDB.registeredCourses.get(semester).get(student) == null) {
           MockDB.registeredCourses.get(semester).put(student, new HashSet<>());
       }
   }
   
	@Override
	public boolean updatePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}
}
