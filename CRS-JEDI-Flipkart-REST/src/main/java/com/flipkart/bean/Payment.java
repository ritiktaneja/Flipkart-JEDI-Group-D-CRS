package com.flipkart.bean;

import com.flipkart.constants.ModeOfPayment;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Payment class
 */
public class Payment {

    @NotBlank
    private Student student;

    @NotBlank
    private String referenceId;

    @NotBlank
    private float amount;

    @NotBlank
    private boolean status;

    @NotBlank
    private ModeOfPayment modeOfPayment;
    //mode of payment -> online and offline
    //Type of payment using either option

    /**
     * Method to get mode of payment
     * @return Mode of Payment
     */
    public ModeOfPayment getModeOfPayment() {
		return modeOfPayment;
	}

    /**
     * Method to set Mode of Payment
     * @param modeOfPayment
     */
	public void setModeOfPayment(ModeOfPayment modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

    /**
     * Get student object
     * @return student
     */
	public Student getStudent() {
        return student;
    }

    /**
     * Method to set Student
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Get reference id
     * @return Reference Id
     */
    public String getReferenceId() {
        return referenceId;
    }

    /**
     * Method to set Reference Id
     * @param referenceId
     */
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    /**
     * Method to total amount
     * @return amount
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Method to set amount
     * @param amount
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * Payment status
     * @return status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Method to set Payment Status
     * @param status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
}
