package com.flipkart.bean;

public class NetBanking extends PaymentOnline {

    private String modeOfTransfer;
    private String accountNumber;
    private String IFSCCode;

    public String getModeOfTransfer() {
        return modeOfTransfer;
    }

    public void setModeOfTransfer(String modeOfTransfer) {
        this.modeOfTransfer = modeOfTransfer;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIFSCCode() {
        return IFSCCode;
    }

    public void setIFSCCode(String IFSCCode) {
        this.IFSCCode = IFSCCode;
    }
}
