package com.flipkart.bean;

public class PaymentOnline extends  Payment{
    private String bankName;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
