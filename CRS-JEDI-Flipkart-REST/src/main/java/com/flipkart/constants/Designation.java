package com.flipkart.constants;

public enum Designation {
    ASSISTANT_PROFESSOR(1),
    ASSOCIATE_PROFESSOR(2),
    HOD(3),
    PROFESSOR(4);
    private int value;

    /**
     * Method to set designation to the current object
     * @param value
     */
    private Designation(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
