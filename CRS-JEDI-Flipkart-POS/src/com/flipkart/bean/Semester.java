package com.flipkart.bean;

public class Semester {
    private int registrationOpeningStatus = 0;
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
