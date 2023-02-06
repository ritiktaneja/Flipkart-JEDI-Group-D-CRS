package com.flipkart.constants;

public enum Designation {
    ASSISTANT_PROFESSOR(1),
    ASSOCIATE_PROFESSOR(2),
    HOD(3),
    PROFESSOR(4);
    private int value;

    /**
     *
     * @param value
     */
    private Designation(int value) {
        this.value = value;
    }

    /**
     *
     * @return
     */
    public int getValue() {
        return value;
    }
}
