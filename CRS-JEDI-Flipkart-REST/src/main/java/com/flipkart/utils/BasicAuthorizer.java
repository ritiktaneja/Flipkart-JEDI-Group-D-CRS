package com.flipkart.utils;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import io.dropwizard.auth.Authorizer;

import javax.ws.rs.PathParam;

public class BasicAuthorizer implements Authorizer<User> {

    public static final String ADMIN_ROLE = "ADMIN";
    public static final String PROFESSOR_ROLE = "PROFESSOR";
    public static final String STUDENT_ROLE = "STUDENT";

    @Override
    public boolean authorize(User user, String role) {

        if(role.equals(ADMIN_ROLE)) {
            return user instanceof Admin;
        } else if(role.equals(PROFESSOR_ROLE)) {
            return user instanceof Professor;
        } else if(role.equals(STUDENT_ROLE)) {
            return user instanceof Student;
        } else {
            return false;
        }
    }
}
