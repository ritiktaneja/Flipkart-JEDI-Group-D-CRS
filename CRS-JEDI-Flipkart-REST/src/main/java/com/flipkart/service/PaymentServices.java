package com.flipkart.service;

/**
 * PaymentServices interface
 */
public interface PaymentServices {
    int initPayment(String studentId, String referenceId, String modeOfPayment, double amount, String semester, String paymentStatus, String paymentDescription);
}
