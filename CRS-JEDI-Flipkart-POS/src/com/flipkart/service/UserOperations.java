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
    /**
     * using this method user can update the password
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public boolean updatePassword(String userName, String userPassword) {
        //TODO DAO Call
        if (UserDao.getInstance().get(userName) == null) {
            System.out.println("UserId not found.");
            return false;
        }
        UserDao.getInstance().updatePassword(userName, userPassword);
        return true;
    }

    /**
     *using this method user can login
     * @param userId
     * @param password
     * @return Status of user login
     */

    public User loginUser(String userId, String password) {
        UserDao userDao = UserDao.getInstance();
        return userDao.login(userId, password);
    }
}
