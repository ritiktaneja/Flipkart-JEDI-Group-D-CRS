package com.flipkart.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Payment Notification Class
 */
public class PaymentNotification {

    @NotBlank
    private Payment payment;

    @NotBlank
    private String notificationId;

    @NotBlank
    private String notificationMessage;

    public Payment getPayment() {
        return payment;
    }

    /**
     * Method to set Payment
     * @param payment
     */
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    /**
     * Method to get notification id
     * @return Notification Id
     */
    public String getNotificationId() {
        return notificationId;
    }

    /**
     * Method to set Notification Id
     * @param notificationId
     */
    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    /**
     * Get Notification Message
     * @return notification message
     */
    public String getNotificationMessage() {
        return notificationMessage;
    }

    /**
     * Method to set Notification Message
     * @param notificationMessage
     */
    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }
}
