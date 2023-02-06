package com.flipkart.dao;

import com.flipkart.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PaymentDao {


    private static PaymentDao instance = null;

    private static final String PAYMENT_INIT = "INSERT INTO PAYMENT (StudentId, ReferenceId, ModeOfPayment, Amount, Semester, PaymentStatus, PaymentDescription) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private PaymentDao() {

    }

    public static PaymentDao getInstance() {
        if (instance == null) {
            instance = new PaymentDao();
        }
        return instance;
    }

    public int insert(String studentId, String referenceId, String modeOfPayment, double amount, String semester, String paymentStatus, String paymentDescription) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(PAYMENT_INIT);
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
        }
        return 0;
    }

}

