package com.flipkart.service;

import com.flipkart.bean.Student;

/**
 * SelfRegistrationService class
 */
public interface SelfRegistrationService {

     /**
      * Self register method
      * @param name
      * @param password
      * @param semester
      * @param department
      */
     Student selfRegister(String name, String password, String semester, String department);

}
