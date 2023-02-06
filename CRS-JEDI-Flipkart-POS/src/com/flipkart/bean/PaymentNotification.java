package com.flipkart.bean;

public class PaymentNotification {
    private Payment payment;
    private String notificationId;
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
     *
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
     * @return
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
