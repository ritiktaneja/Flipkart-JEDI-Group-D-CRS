package com.flipkart.restController;


import com.flipkart.App;
import com.flipkart.bean.*;
import com.flipkart.client.CRSApplication;
import com.flipkart.constants.Department;
import com.flipkart.service.AdminOperations;
import com.flipkart.service.AdminServices;
import com.flipkart.service.ProfessorServices;
import org.xml.sax.SAXException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.util.List;

@Path("/admin/{adminId}/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminController {

    private AdminServices adminOperations = new AdminOperations();
    private String adminId;

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
            //return Response.status(Response.Status.BAD_REQUEST).entity().build();
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
            return Response.status(Response.Status.CREATED).build();
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
                    .entity(e.getMessage())
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
