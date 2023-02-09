package com.flipkart.restController;


import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.client.CRSApplication;
import com.flipkart.service.SelfRegistrationOperations;
import com.flipkart.service.SelfRegistrationService;
import com.flipkart.service.StudentOperations;
import com.flipkart.service.StudentServices;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/student/{studentId}/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentController {

    private StudentServices studentServices = new StudentOperations();
    private SelfRegistrationService selfRegistrationService = new SelfRegistrationOperations();
    private String studentId;

    public StudentController(@PathParam("studentId") String studentId) {
        this.studentId = studentId;
    }

    @POST
    @Path("/selfRegistration")
    public Response selfRegistration(@QueryParam("studentName") String name, @QueryParam("password") String password, @QueryParam("department") String department) {
        try {
            Student student = selfRegistrationService.selfRegister(name, password, CRSApplication.currentSemester.getCurrentSemester(), department);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(student)
                    .build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/addCourse")
    public Response addCourse(@QueryParam("courseCode") String courseCode) {
        try {
            studentServices.addCourse(studentId, courseCode);
            return Response.ok().build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/dropCourse")
    public Response dropCourse(@QueryParam("courseCode") String courseCode) {
        try {
            studentServices.dropCourse(studentId, courseCode);
            return Response.ok().build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/viewAvailableCourses")
    public Response viewAvailableCourses() {
        try {
            List<Course> courses = studentServices.viewAvailableCourses(studentId);
            return Response.ok(courses).build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/viewRegisteredCourses")
    public Response viewRegisteredCourses() {
        try {
            List<RegisteredCourse> courses = studentServices.viewRegisteredCourses(studentId);
            return Response.ok(courses).build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/viewGradesCard")
    public Response viewGradesCard() {
        try {
            List<RegisteredCourse> registeredCourses = studentServices.viewRegisteredCourses(studentId);
            return Response.ok(registeredCourses).build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/payFees")
    public Response payFees() {
        return Response.ok().build();
    }
}
