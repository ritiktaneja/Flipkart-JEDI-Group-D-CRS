package com.flipkart;

import com.flipkart.bean.Semester;
import com.flipkart.restController.AdminController;
import com.flipkart.restController.ProfessorController;
import com.flipkart.restController.RegistrationController;
import com.flipkart.restController.StudentController;
import com.flipkart.service.AdminOperations;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Hello world!
 *
 */
public class App extends Application<Configuration>
{

    @Override
    public void initialize(Bootstrap<Configuration> b) {

    }

    @Override
    public void run(Configuration c, Environment e) {
        System.out.println("RUNNING REST CLIENT");
        e.jersey().register(AdminController.class);
        e.jersey().register(ProfessorController.class);
        e.jersey().register(StudentController.class);
        e.jersey().register(RegistrationController.class);
    }

    public static void main(String[] args) throws Exception
    {
        new App().run(args);
    }
}
