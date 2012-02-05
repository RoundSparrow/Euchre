package com.cynical.android.euchre.model;

public class Card {	
	
	private Suit suit;
	private FaceValue value;
	
	public Card(FaceValue value, Suit suit) {
		this.suit = suit;
		this.value = value;
	}
	
	public boolean isRightBower(Suit trump) {
		if(suit == trump) {
			return value.equals(FaceValue.JACK);
		}
		return false;
	}
	
	public boolean isLeftBower(Suit trump) {
		if(value.equals(FaceValue.JACK)) {
			if(trump.isBlack()) {
				return trump.equals(Suit.CLUBS) ? suit.equals(Suit.SPADES) : suit.equals(Suit.CLUBS);
			}
			else {
				return trump.equals(Suit.DIAMONDS) ? suit.equals(Suit.HEARTS) : suit.equals(Suit.DIAMONDS);
			}
		}
		return false;
	}
	
	public boolean isTrump(Suit trump) {
		return suit.equals(trump) || isLeftBower(trump);
	}
	
	public boolean isRed() {
		return suit.isRed();
	}
	
	public boolean isBlack() {
		return suit.isBlack();
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public FaceValue getValue() {
		return value;
	}

	public void setValue(FaceValue value) {
		this.value = value;
	}
	
	
	
}
