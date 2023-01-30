package com.flipkart.constants;

public enum Grade {
    A(10),
    B(8),
    C(6),
    D(4),
    NOT_CLEAR(0),
    IN_PROGRESS(-1),
    DROPPED(-2);

    private int value;
    private Grade(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
