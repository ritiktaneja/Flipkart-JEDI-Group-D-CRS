package com.flipkart.service;

import com.flipkart.dao.PaymentDao;

public class PaymentOperations implements PaymentServices {


    @Override
    public int initPayment(String studentId, String referenceId, String modeOfPayment, double amount, String semester, String paymentStatus, String paymentDescription) {
        PaymentDao paymentDao = PaymentDao.getInstance();
        return paymentDao.insert(studentId, referenceId, modeOfPayment, amount, semester, paymentStatus, paymentDescription);
    }
}
