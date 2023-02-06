package com.flipkart.service;

import com.flipkart.bean.User;
import com.flipkart.dao.UserDao;

/**
 * User operations class
 */
public class UserOperations implements UserServices {
    /**
     *
     * @param oldPassword
     * @param newPassword
     * @return Status of update password
     */
    public boolean updatePassword(String oldPassword, String newPassword) {
        //TODO DAO Call
        return true;
    }

    /**
     *
     * @param userId
     * @param password
     * @return Status of user login
     */
    public User loginUser(String userId, String password) {
        UserDao userDao = new UserDao();
        return userDao.login(userId, password);

    }
}
