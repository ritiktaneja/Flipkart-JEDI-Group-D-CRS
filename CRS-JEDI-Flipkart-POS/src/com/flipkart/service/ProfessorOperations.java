package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;

import java.util.List;

<<<<<<< HEAD:CRS-JEDI-Flipkart-POS/src/com/flipkart/service/ProfessorOperations.java
public class ProfessorOperations implements ProfessorServices {
=======
public class ProfessorImpl extends UserOperations implements ProfessorInterface {
>>>>>>> 9234335 (Extended sub user Service clases with user operations class):CRS-JEDI-Flipkart-POS/src/com/flipkart/service/ProfessorImpl.java

    private static volatile ProfessorOperations instance = null;

    private ProfessorOperations() {

    }

    public static ProfessorOperations getInstance() {
        if(instance == null) {
            synchronized (ProfessorOperations.class){
                instance = new ProfessorOperations();
            }
        }
        return instance;
    }


    @Override
    public boolean addGrade(RegisteredCourse course) throws Exception{
        // dao call
        return true;
    }

    public List<Student> viewEnrolledStudents(Course course) throws Exception {
        return null;
    }

    public List<Course> viewCoursesTaken() throws Exception{
        return null;
    }

}
