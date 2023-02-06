package com.flipkart.constants;

public enum Department {
    CSE(1),
    EEE(2),
    IT(3),
    CIVIL(4),
    MECH(5);

    private int value;

    /**
     *
     * @param value
     */
    private Department(int value) {
        this.value = value;
    }

    /**
     *
     * @return department
     */
    public int getValue() {
        return value;
    }
}
