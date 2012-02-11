package com.cynical.android.euchre.domain;

public enum FaceValue {
	
	NINE (9, "Nine"),
	TEN (10, "Ten"),
	JACK (11, "Jack"),
	QUEEN (12, "Queen"),
	KING (13, "King"),
	ACE (14, "Ace");

	private int value;
	private String name;
	
	FaceValue(int value, String name) {
		this.value = value;
		this.name = name;
	}

	/**
	 * Gets the value of the card
	 * @return The value of the card
	 */
	public int getValue() {
		return value;
	}

	public String toString() {
		return name;
	}
	
}
