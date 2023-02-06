package com.flipkart.service;

import com.flipkart.bean.Student;

public interface SelfRegistrationService {

     /**
      *
      * @param name
      * @param password
      * @param semester
      * @param department
      */
     Student selfRegister(String name, String password, String semester, String department);

}
