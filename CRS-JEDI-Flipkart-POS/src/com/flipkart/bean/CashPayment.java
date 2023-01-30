package com.flipkart.bean;

public class CashPayment extends PaymentOffline{
    private float amount;

    @Override
    public float getAmount() {
        return amount;
    }

    @Override
    public void setAmount(float amount) {
        this.amount = amount;
    }
}
