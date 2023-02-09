package com.flipkart.service;

import com.flipkart.dao.PaymentDao;

/**
 * PaymentOperations class
 */
public class PaymentOperations implements PaymentServices {

    /**
     * Method to init Payment
     * @param studentId
     * @param referenceId
     * @param modeOfPayment
     * @param amount
     * @param semester
     * @param paymentStatus
     * @param paymentDescription
     * @return status
     */
    @Override
    public int initPayment(String studentId, String referenceId, String modeOfPayment, double amount, String semester, String paymentStatus, String paymentDescription) {
        PaymentDao paymentDao = PaymentDao.getInstance();
        return paymentDao.insert(studentId, referenceId, modeOfPayment, amount, semester, paymentStatus, paymentDescription);
    }
}
