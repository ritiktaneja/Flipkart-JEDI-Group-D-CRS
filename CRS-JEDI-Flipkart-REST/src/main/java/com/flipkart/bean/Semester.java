package com.flipkart.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Semester Class
 */
public class Semester {

    @NotBlank
    private int registrationOpeningStatus = 0;

    @NotBlank
    private String currentSemester;

    public int getRegistrationOpeningStatus() {
        return registrationOpeningStatus;
    }

    /**
     * Set registration status as open
     * @param registrationOpeningStatus
     */
    public void setRegistrationOpeningStatus(int registrationOpeningStatus) {
        this.registrationOpeningStatus = registrationOpeningStatus;
    }

    /**
     * Method to get current semester
     * @return Current Semester
     */
    public String getCurrentSemester() {
        return currentSemester;
    }

    /**
     * Method to set Current semester
     * @param currentSemester
     */
    public void setCurrentSemester(String currentSemester) {
        this.currentSemester = currentSemester;
    }
}
