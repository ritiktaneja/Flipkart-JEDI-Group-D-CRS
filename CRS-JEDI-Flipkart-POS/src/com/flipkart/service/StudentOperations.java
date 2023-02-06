package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.constants.Grade;
import com.flipkart.data.MockDB;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Student Operations Class
 */
public class StudentOperations extends UserOperations implements StudentServices{


    /**
     *
     * @param student
     * @param course
     * @throws RuntimeException
     */
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

    /**
     *
     * @param student
     * @param course
     */
    private boolean dropCourse(Student student, Course course) {

        RegisteredCourse toRemove = null;
        for(RegisteredCourse registeredCourse : MockDB.registeredCourses.get(student)) {
            if(registeredCourse.getCourse().getCourseCode().equals(course.getCourseCode())) {
                toRemove = registeredCourse; break;
            }
        }
        return toRemove.dropCourse();
    }

    /**
     *
     * @param student
     * @return List of Registered Courses
     */
    private List<RegisteredCourse> viewRegisteredCourses(Student student)
    {
        initStudSem(student);
        List<RegisteredCourse> registeredCourses = new ArrayList<>();
        for(RegisteredCourse rc : MockDB.registeredCourses.get(student)) {
        	if(rc.getGrade() != Grade.DROPPED)
        		registeredCourses.add(rc);
        }
        return registeredCourses;
    }

    /**
     *
     * @param student
     * @return Calculate Fee for that transaction
     */
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

    /**
     *
     * @param student
     */
   private void initStudSem(Student student) {


       if(MockDB.registeredCourses.get(student) == null) {
           MockDB.registeredCourses.put(student, new HashSet<>());
       }
   }


    /**
     *
     * @param studentId
     * @param courseCode
     * @return Add Course Status
     */
    public boolean addCourse(String  studentId, String courseCode) {
       Student student = MockDB.getStudentFromId(studentId);
       if(student == null) {
           throw new RuntimeException("Student Not Found");
       }
       Course course = MockDB.getCourseFromId(student.getSemester(), courseCode);
        if (course == null) {
            throw new RuntimeException("Course Not Found");
        }
        return addCourse(student, course);
    }

    /**
     *
     * @param studentId
     * @param courseCode
     * @return Course Drop Status
     */
    public boolean dropCourse(String studentId, String courseCode) {
        Student student = MockDB.getStudentFromId(studentId);
        if(student == null) {
            throw new RuntimeException("Student not Found");
        }
        Course course = MockDB.getCourseFromId(student.getSemester(), courseCode);
        if(course == null) {
            throw new RuntimeException("Registered Course Not Found");
        }
        return dropCourse(student, course);
    }

    /**
     *
     * @param studentId
     * @return List of Courses
     */
    public List<Course> viewCourses(String studentId) {
       Student student = MockDB.getStudentFromId(studentId);
       if(student == null) return null;
       return MockDB.getCatalogFromId(student.getSemester()).getCourses();
    }

    /**
     *
     * @param studentId
     * @return List of registered courses
     */
    public List<RegisteredCourse> viewRegisteredCourses(String studentId) {
       Student student = MockDB.getStudentFromId(studentId);
       if(student != null) {
           return viewRegisteredCourses(student);
       }
       return null;
    }

    /**
     * @param studentId
     * @return Fee for that transaction
     */
    public long calculateFee(String studentId) {
        Student student = MockDB.getStudentFromId(studentId);
        return calculateFee(student);
    }

    /**
     *
     * @param studentId
     * @return registration Status
     */
    public boolean getRegistrationStatus(String studentId) {
       return true; // TODO
    }



}
