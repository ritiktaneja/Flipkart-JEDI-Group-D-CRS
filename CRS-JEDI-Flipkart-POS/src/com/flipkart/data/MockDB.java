package com.flipkart.data;

import com.flipkart.bean.*;

import java.util.*;

import com.flipkart.constants.Department;
import com.flipkart.constants.Designation;

public class MockDB {
    public static Map<Student, Set<RegisteredCourse>> registeredCourses = new HashMap<>();
    public static List<Student> students = new ArrayList<>();
    public static List<CourseCatalog> catalogs = new ArrayList<>();

    public static void populateLists() {

        Student.StudentBuilder studentBuilder = new Student.StudentBuilder();
        ArrayList<String> names = new ArrayList<>(Arrays.asList("Geeks", "Jean", "John", "Francis", "Daniel", "Dave", "Seth", "Wade", "Miles", "Brain", "Ben", "parker"));
        ArrayList<Department> departments = new ArrayList<>(Arrays.asList(Department.IT, Department.CIVIL, Department.CSE, Department.CIVIL, Department.EEE, Department.MECH));

        int id = 20231010;
        for (int i = 0; i < names.size(); i++) {
            studentBuilder.setName(names.get(i));
            studentBuilder.setSemester(2023);
            studentBuilder.setDepartment(departments.get(i % departments.size()));
            studentBuilder.setStudentId("ST-" + (id + i));
            students.add(studentBuilder.build());
        }

        for (Student s : students) {
            System.out.println(s);
        }
        ArrayList<String> cousesList = new ArrayList<>(Arrays.asList("English", "math", "art", "science", "history", "music", "geography", "chemistry", "physics"));
        int couseId = 101;
        Professor.ProfessorBuilder professorBuilder = new Professor.ProfessorBuilder();

        ArrayList<String> profNames = new ArrayList<>(Arrays.asList("Rick", "Victor", "Jessie", "Neil", "Ted", "Nick", "Wiley", "Morris", "Clark", "Stuart", "Orlando"));
        ArrayList<Designation> desiginations = new ArrayList<>(Arrays.asList(Designation.HOD, Designation.ASSISTANT_PROFESSOR, Designation.ASSISTANT_PROFESSOR, Designation.PROFESSOR));
        int facutltyId = 20021001;
        int catalogID = 1001;
        for (int j = 0; j < 3; j++) {
            CourseCatalog catalog = new CourseCatalog();
            catalog.setCatalogId("CT-" + (catalogID + j));
            for (int i = 0; i < cousesList.size(); i++) {
                Course course = new Course();
                course.setCourseCode("CS-" + (couseId + i));
                course.setName(cousesList.get(i));
                professorBuilder.setDepartment(departments.get(i % departments.size()));
                professorBuilder.setDesignation(desiginations.get(i % desiginations.size()));
                professorBuilder.setName(profNames.get(i));
                professorBuilder.setFacultyId("PR-" + (facutltyId + i));
                course.setProfessor(professorBuilder.build());
                catalog.addCourse(course);
            }
            catalogs.add(catalog);
        }
        for (int i = 0; i < catalogs.size(); i++) {
            System.out.println("Catalog no. = " + i);
            for (int j = 0; j < catalogs.get(i).getCourses().size(); j++) {
                System.out.println(catalogs.get(i).getCourses().get(j));
            }
        }
    }

    public static void main(String[] args) {
        populateLists();
    }


}
