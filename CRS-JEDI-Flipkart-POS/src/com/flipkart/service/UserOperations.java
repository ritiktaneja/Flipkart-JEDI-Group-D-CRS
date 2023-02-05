package com.flipkart.service;

import com.flipkart.bean.User;
import com.flipkart.dao.UserDao;

public class UserOperations implements UserServices {
    public boolean updatePassword(String oldPassword, String newPassword) {
        //TODO DAO Call
        return true;
    }

    public User loginUser(String userId, String password) {
        UserDao userDao = new UserDao();
        return userDao.login(userId, password);

    }
}
