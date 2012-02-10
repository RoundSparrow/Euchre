package com.cynical.android.euchre.player;

import com.cynical.android.euchre.domain.Card;
import com.cynical.android.euchre.domain.Suit;

public interface Player {
	
	///////////////////////////////////////////
	//	Operations while laying cards
	///////////////////////////////////////////
	
	/**
	 * Play a card from the player's hand
	 * @param c The card to play.
	 */
	public void playCard(Card c);	
	
	//////////////////////////////////////////
	//	Operations when deciding trump
	//////////////////////////////////////////
	
	/**
	 * If player is the dealer, and on the first round when deciding trump, the player will 
	 * pick up the top (face up) card if trump is decided.
	 * @return The top (face up) card. This card is trump.
	 */
	public Card pickUp();
	
	/**
	 * The dealer must discard a card if trump is decided on the first pass.
	 * @param c The card to discard.
	 */
	public void discard(Card c);
	
	/**
	 * Order up trump to the dealer. Only available on first pass.
	 */
	public void orderUp();
	
	/**
	 * Pass the opportunity to order up or call trump to the next player. 
	 */
	public void pass();
	
	/**
	 * If on the second pass, player can has the opportunity to call trump. They can not 
	 * choose the suit that was turned down on the first pass.
	 * @param trump The suit to make trump.
	 */
	public void callTrump(Suit trump);

}
