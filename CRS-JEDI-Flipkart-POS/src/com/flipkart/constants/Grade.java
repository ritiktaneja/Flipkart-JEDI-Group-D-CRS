package com.flipkart.constants;

public enum Grade {
	A(10), B(8), C(6), D(4), NOT_CLEAR(0), IN_PROGRESS(-1), DROPPED(-2);

	private int value;

	private Grade(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		int v = getValue();
		if (v == 10) {
			return "A";
		} else if (v == 6) {
			return "B";
		} else if (v == 4) {
			return "C";
		} else if (v == 0) {
			return "D";
		} else if (v == -1) {
			return "Not Clear";

		} else if (v == -2) {
			return "Dropped";
		}
		return "No Grade";

	}
}
