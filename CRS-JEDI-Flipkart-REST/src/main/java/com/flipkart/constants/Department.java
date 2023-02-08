package com.flipkart.constants;

public enum Department {
    CSE(1),
    EEE(2),
    IT(3),
    CIVIL(4),
    MECH(5);

    private int value;

    /**
     * Method to set department to the current object
     * @param value
     */
    private Department(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
