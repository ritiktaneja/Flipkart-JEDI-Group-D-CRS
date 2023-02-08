package com.flipkart.restController;

import com.flipkart.bean.Professor;
import com.flipkart.service.ProfessorOperations;
import com.flipkart.service.ProfessorServices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.validation.Validator;

@Path("/professor/${professorId}/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfessorController {

    private final Validator validator;
    private ProfessorServices professorServices;

    private Professor professor;
    public ProfessorController(Validator validator) {
        this.validator = validator;
        this.professorServices = new ProfessorOperations();
    }

    @GET
    @Path("/{courseCode}/")
    public Response getProfessors() {
        //this.professorServices.get
    }


}
