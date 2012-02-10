package com.cynical.android.euchre.domain;

import com.cynical.android.euchre.player.Player;

public class Card {	
	
	private Suit suit;
	private FaceValue value;
	
	private Player owner;
	
	public Card(FaceValue value, Suit suit) {
		this.suit = suit;
		this.value = value;
	}
	
	/**
	 * Checks if the card is the right bower
	 * @param trump The suit that is currently trump
	 * @return True, if the card is the right bower
	 */
	public boolean isRightBower(Suit trump) {
		if(suit == trump) {
			return value.equals(FaceValue.JACK);
		}
		return false;
	}
	
	/**
	 * Checks if the card is the left bower
	 * @param trump The suit that is currently trump
	 * @return True, if the card is the left bower
	 */
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
	
	/**
	 * Checks if the card is trump
	 * @param trump The suit that is currently trump
	 * @return True, if the card's suit is considered trump
	 */
	public boolean isTrump(Suit trump) {
		return suit.equals(trump) || isLeftBower(trump);
	}
	
	/**
	 * Checks if the card's suit is red
	 * @return True, if the card's suit is red
	 */
	public boolean isRed() {
		return suit.isRed();
	}
	
	/**
	 * Checks if the card's suit is black
	 * @return True, if the card's suit is black
	 */
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

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}	
	
}
