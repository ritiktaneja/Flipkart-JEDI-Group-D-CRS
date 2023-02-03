package com.flipkart.service;

import com.flipkart.bean.Student;

public interface SelfRegistrationService {

     Student selfRegister(String name, String password, String semester, String department);

}
