package com.cynical.android.euchre.domain;

import java.util.HashMap;
import java.util.Map;

import com.cynical.android.euchre.player.Player;

public class Trick {
	
	Map<Player, Card> cards;
	Suit trump;
	Suit leadSuit;
	
	public Trick() {
		cards = new HashMap<Player, Card>();
	}
	
	public void addCard(Player player, Card card) {
		cards.put(player, card);
	}

	//////////////////////////////////////////
	//	Getters / Setters
	//////////////////////////////////////////
	
	/**
	 * Gets the card the specified player laid.
	 * @param player The player whose card you want to see
	 * @return The card the player laid, or <i>null</i> if no card was laid by that player.
	 */
	public Card getLaidCard(Player player) {
		return cards.get(player);
	}
	
	/**
	 * Gets the map of cards.
	 * @return The map of cards
	 */
	public Map<Player, Card> getCards() {
		return cards;
	}
	
	public void setTrump(Suit trump) {
		this.trump = trump;
	}
	
	public void setLeadSuit(Suit leadSuit) {
		this.leadSuit = leadSuit;
	}

}
