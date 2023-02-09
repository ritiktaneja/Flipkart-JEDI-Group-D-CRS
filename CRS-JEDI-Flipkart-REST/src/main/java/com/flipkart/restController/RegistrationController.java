package com.flipkart.restController;

import com.flipkart.bean.Student;
import com.flipkart.client.CRSApplication;
import com.flipkart.service.SelfRegistrationOperations;

import javax.annotation.Nonnull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/register")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegistrationController {

    @POST
    public Response studentRegistration(@QueryParam("name") @Nonnull String name,
                                        @QueryParam("password") @Nonnull String password,
                                        @QueryParam("department") @Nonnull String department) {
        try {
            Student student = new SelfRegistrationOperations().selfRegister(name, password, CRSApplication.currentSemester.getCurrentSemester(), department);
            return Response.created(new URI("/register")).entity(student).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
