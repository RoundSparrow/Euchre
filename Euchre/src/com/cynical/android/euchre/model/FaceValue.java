package com.cynical.android.euchre.model;

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

	public int getValue() {
		return value;
	}

}
