package com.flipkart.restcontroller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloRestAPI {

    @GET
    public String getEmployees() {
        return "my dropwizard service";
    }

}