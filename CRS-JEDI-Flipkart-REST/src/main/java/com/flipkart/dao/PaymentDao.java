package com.flipkart.dao;

import com.flipkart.utils.DBConnection;
import com.flipkart.constants.sqlconstants.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * PaymentDao Class
 */
public class PaymentDao {


    private static PaymentDao instance = null;

    /**
     * Payment Dao default constructor
     */
    private PaymentDao() {

    }

    /**
     * Get instance
     * @return instance
     */
    public static PaymentDao getInstance() {
        if (instance == null) {
            instance = new PaymentDao();
        }
        return instance;
    }

    /**
     * Insert payment details in the given database
     * @param studentId
     * @param referenceId
     * @param modeOfPayment
     * @param amount
     * @param semester
     * @param paymentStatus
     * @param paymentDescription
     * @return
     */
    public int insert(String studentId, String referenceId, String modeOfPayment, double amount, String semester, String paymentStatus, String paymentDescription) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(PaymentDaoConstants.PAYMENT_INIT);
            statement.setString(1, studentId);
            statement.setString(2, referenceId);
            statement.setString(3, modeOfPayment);
            statement.setDouble(4, amount);
            statement.setString(5, semester);
            statement.setString(6, paymentStatus);
            statement.setString(7, paymentDescription);
            return statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeStatement(statement);
            DBConnection.closeConnection(connection);
        }
        return 0;
    }

}

