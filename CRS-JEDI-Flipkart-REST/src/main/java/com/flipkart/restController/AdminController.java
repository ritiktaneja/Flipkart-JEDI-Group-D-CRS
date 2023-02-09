package com.flipkart.restController;



import com.flipkart.bean.*;
import com.flipkart.bean.Error;
import com.flipkart.client.CRSApplication;

import com.flipkart.service.AdminOperations;
import com.flipkart.service.AdminServices;
import com.flipkart.utils.BasicAuthorizer;
import io.dropwizard.auth.Auth;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;
import java.util.Optional;

@Path("/admin/{adminId}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({BasicAuthorizer.ADMIN_ROLE})
public class AdminController {

    private final AdminServices adminOperations = new AdminOperations();
    private final String adminId;
    public AdminController(@PathParam("adminId") String adminId) {
        this.adminId = adminId;
    }

    @POST
    @Path("/addCourseToCatalog")
    public Response addCourseToCatalog(@QueryParam("courseId") String courseId, @QueryParam("courseName") String courseName) {
        try {
            adminOperations.addCourse(CRSApplication.currentSemester.getCurrentSemester(), courseId, courseName);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/addProfessor")
    public Response addProfessor(@QueryParam("professorId") String professorId, @QueryParam("professorName") String professorName, @QueryParam("password") String password, @QueryParam("department") String department) {
        try {
            adminOperations.addProfessor(professorId, professorName, password, department);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/addAdmin")
    public Response addAdmin(@QueryParam("newAdminId") String newAdminId, @QueryParam("adminName") String adminName, @QueryParam("password") String password) {
        try {
            adminOperations.addAdmin(newAdminId, adminName, password);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
             return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/approveStudent")
    public Response approveStudent(@QueryParam("studentId") String studentId) {
        try {
            adminOperations.approveStudent(studentId);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
             return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/deleteCourseFromCatalog")
    public Response deleteCourseFromCatalog(@QueryParam("courseId") String courseId) {
        try {
            adminOperations.deleteCourse(CRSApplication.currentSemester.getCurrentSemester(), courseId);
            return Response.ok().build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }

    }

    @GET
    @Path("/showProfessor")
    public Response showProfessor() {
        try {
            List<Professor> professors = adminOperations.viewProfessors();
            System.out.println(professors.get(0));
            return Response.ok(professors).build();
        } catch (Exception e) {
            e.getMessage();
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/viewCoursesInCatalog")
    public Response viewCoursesInCatalog() {
        try {
            List<Course> courses = adminOperations.viewCourses(CRSApplication.currentSemester.getCurrentSemester());
            return Response.ok(courses).build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(new Error(e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("/showRegisteredStudents")
    public Response showRegisteredStudents() {
        try {
            List<Student> students = adminOperations.viewStudents();
            return Response.ok(students).build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/viewPendingAdmission")
    public Response viewPendingAdmission() {
        try {
            List<Student> students = adminOperations.viewPendingApprovals();
            return Response.ok(students).build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/showAddedAdmins")
    public Response showAddedAdmins() {
        try {
            List<Admin> admins = adminOperations.viewAdmins();
            return Response.ok(admins).build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/updateSemester")
    public Response updateSemester(@QueryParam("semester") String semester) {
        try {
            adminOperations.updateSemester(semester);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }


}
