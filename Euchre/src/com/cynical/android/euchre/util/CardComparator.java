package com.cynical.android.euchre.util;

import java.util.Comparator;

import com.cynical.android.euchre.domain.Card;
import com.cynical.android.euchre.domain.Suit;

/**
 * A Card Comparator used for comparing cards in the game of euchre. Make sure to set the trump suit and 
 * the suit that was lead that hand in order for the comparator to work correctly.
 * 
 * @author Adam Benjamin
 * 
 */
public class CardComparator implements Comparator<Card> {
	
	Suit trump;
	Suit leadSuit;

	public int compare(Card c1, Card c2) {
		
		///////////////////////////////
		//	Right bowers
		///////////////////////////////
		if(c1.isRightBower(trump)) {
			return 1;
		}
		if(c2.isRightBower(trump)) {
			return -1;
		}
		
		///////////////////////////////
		//	Left bowers
		///////////////////////////////
		// (At this point we know the left bower will kill the other card)
		if(c1.isLeftBower(trump)) {
			return 1;
		}
		if(c2.isLeftBower(trump)) {
			return -1;
		}
		
		///////////////////////////////
		//	Trump
		///////////////////////////////
		//	At this point we can go by value of face rank if the card is trump
		if(c1.isTrump(trump)) {
			if(c2.isTrump(trump)) {
				return c1.getRank().getValue() > c2.getRank().getValue() ? 1 : -1;
			}
			else {
				return 1;
			}
		}
		if(c2.isTrump(trump)) {
			return -1;
		}
		
		///////////////////////////////
		//	Lead suit
		///////////////////////////////
		//	At this point we know neither card is trump
		if(c1.getSuit().equals(leadSuit)) {
			if(c2.getSuit().equals(leadSuit)) {
				return c1.getRank().getValue() > c2.getRank().getValue() ? 1 : -1;
			}
			else {
				return 1;
			}
		}
		if(c2.getSuit().equals(leadSuit)) {
			return -1;
		}
		
		///////////////////////////////
		//	Doesn't matter
		///////////////////////////////
		//	At this point we know that neither card is important in any way
		return 0;
	}
	
	public void setTrump(Suit trump) {
		this.trump = trump;
	}
	
	public void setLeadSuit(Suit lead) {
		this.leadSuit = lead;
	}

}
