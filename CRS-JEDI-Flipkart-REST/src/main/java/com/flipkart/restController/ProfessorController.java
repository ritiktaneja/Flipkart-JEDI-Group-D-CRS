package com.flipkart.restController;

import com.flipkart.App;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.client.CRSApplication;
import com.flipkart.service.ProfessorOperations;
import com.flipkart.service.ProfessorServices;
import com.flipkart.utils.BasicAuthorizer;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.validation.Validator;
import javax.ws.rs.core.Response.Status;

import java.util.List;

@Path("/professor/{professorId}/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({BasicAuthorizer.PROFESSOR_ROLE})
public class ProfessorController {
    private ProfessorServices professorServices;

    private String professorId;

    public ProfessorController(@PathParam("professorId") @NotNull String professorId) {

        this.professorId = professorId;
        this.professorServices = new ProfessorOperations();
    }


    @GET
    @Path("/getEnrolledStudents")
    public Response getEnrolledStudents(@QueryParam("courseCode") String courseCode) {

        try {

            List<Student> studentList = professorServices.viewEnrolledStudents(CRSApplication.currentSemester.getCurrentSemester(), courseCode);
            return Response.ok(studentList).build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/viewAssignedCourses")
    public Response getAssignedCourses() {
        try {
            List<Course> coursesTaken = null;
            coursesTaken = professorServices.viewCoursesTaken(professorId);
            return Response.ok(coursesTaken).build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/registerCourse/")
    public Response registerForCourse(@QueryParam("courseCode") String courseCode) {
        try {
            professorServices.registerForCourse(professorId, courseCode, CRSApplication.currentSemester.getCurrentSemester());
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Status.NOT_FOUND).build();
            //return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/addGrade")
    public Response addGrade(@QueryParam("courseCode") String courseCode,
                             @QueryParam("studentId") String studentID,
                             @QueryParam("grade") String grade) {
        try {
            professorServices.addGrade(studentID, grade, courseCode);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }


}
