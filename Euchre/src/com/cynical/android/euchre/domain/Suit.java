package com.cynical.android.euchre.domain;

public enum Suit {
	
	SPADES ("Spades"),
	CLUBS ("Clubs"),
	DIAMONDS ("Diamonds"),
	HEARTS ("Hearts");
	
	private final String name;
	
	Suit(String s) {
		this.name = s;
	}
	
	public String toString() {
		return this.name;
	}
	
	/**
	 * Checks if the suit is red
	 * @return True, if the suit is red
	 */
	public boolean isRed() {
		return this.equals(DIAMONDS) || this.equals(HEARTS);
	}
	
	/**
	 * Checks if the suit is black
	 * @return True, if the suit is black
	 */
	public boolean isBlack() {
		return this.equals(SPADES) || this.equals(CLUBS);
	}

}
