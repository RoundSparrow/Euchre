package com.cynical.android.euchre.domain;

public enum FaceValue {
	
	NINE (9),
	TEN (10),
	JACK (11),
	QUEEN (12),
	KING (13),
	ACE (14);

	private int value;
	
	FaceValue(int value) {
		this.value = value;
	}

	/**
	 * Gets the value of the card
	 * @return The value of the card
	 */
	public int getValue() {
		return value;
	}

}
