package com.flipkart.restController;


import com.flipkart.bean.Course;
import com.flipkart.bean.Error;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.client.CRSApplication;
import com.flipkart.service.*;
import com.flipkart.utils.BasicAuthorizer;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

import static com.mysql.cj.MysqlType.JSON;

@Path("/student/{studentId}/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({BasicAuthorizer.STUDENT_ROLE})
public class StudentController {

    private StudentServices studentServices = new StudentOperations();
    private PaymentServices paymentServices = new PaymentOperations();
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
                    .entity(new Error(e.getMessage()))
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
                    .entity(new WebApplicationException(e.getMessage()))
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
    public Response payFess(@QueryParam("modeOfPayment") String modeOfPayment, @QueryParam("amount") double amount) {
        {
            try {
                String referenceId = String.valueOf(UUID.randomUUID());
                String status = "Initiated";
                String description = "Payment has been initiated";
                long fees = studentServices.calculateFee(studentId);
                if (fees != amount) {
                    throw new Exception();
                }
                paymentServices.initPayment(studentId, referenceId, modeOfPayment, amount, CRSApplication.currentSemester.getCurrentSemester(), status, description);
                return Response.ok().build();
            } catch (Exception e) {
                return Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity(e.getMessage())
                        .build();
            }
        }
    }

    @POST
    @Path("/showFees")
    public Response showFees() {
        {
            try {
                long fees = studentServices.calculateFee(studentId);
                return Response.ok(fees).build();
            } catch (Exception e) {
                return Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity(new String(e.getMessage()))
                        .build();
            }
        }
    }
}
