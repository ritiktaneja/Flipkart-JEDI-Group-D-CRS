package com.flipkart;

import com.flipkart.bean.User;
import com.flipkart.restController.AdminController;
import com.flipkart.restController.ProfessorController;
import com.flipkart.restController.RegistrationController;
import com.flipkart.restController.StudentController;
import com.flipkart.utils.BasicAuthenticator;
import com.flipkart.utils.BasicAuthorizer;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

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

        e.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new BasicAuthenticator())
                .setAuthorizer(new BasicAuthorizer())
                .setRealm("BASIC-AUTH-REALM")
                .buildAuthFilter()));

        e.jersey().register(RolesAllowedDynamicFeature.class);
        e.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));

    }

    public static void main(String[] args) throws Exception
    {
        new App().run(args);
    }
}
