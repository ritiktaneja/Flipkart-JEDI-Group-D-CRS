package com.flipkart.utils;

import com.flipkart.bean.Admin;
import com.flipkart.bean.User;
import com.flipkart.service.UserOperations;
import io.dropwizard.auth.Auth;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import org.eclipse.jetty.server.Authentication;

import javax.ws.rs.Path;
import java.util.Optional;

public class BasicAuthenticator implements Authenticator<BasicCredentials,User > {
    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        String username = credentials.getUsername();
        String password = credentials.getPassword();
        User user =  new UserOperations().loginUser(username, password);

        if(user == null) {
            return Optional.empty();
        }
        System.out.println("User Authenticated : "+ user);
        return Optional.of(user);
    }
}
