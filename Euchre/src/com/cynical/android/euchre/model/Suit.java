package com.cynical.android.euchre.model;

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
	
	public boolean isRed() {
		return this.equals(DIAMONDS) || this.equals(HEARTS);
	}
	
	public boolean isBlack() {
		return this.equals(SPADES) || this.equals(CLUBS);
	}

}
