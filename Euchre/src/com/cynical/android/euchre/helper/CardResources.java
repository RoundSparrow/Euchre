package com.cynical.android.euchre.helper;

import android.content.res.Resources;

import com.cynical.android.euchre.R;
import com.cynical.euchre.domain.Card;

public class CardResources {
	
	Resources res;
	
	public CardResources(Resources res) {
		this.res = res;
	}
	
	public int getCardDrawable(Card c) {
		int resId = R.drawable.cards_joker_r;
		switch(c.getSuit()) {
		case CLUBS:
			switch(c.getRank()) {
			case ACE:
				resId = R.drawable.cards_clubs_a;
				break;
			case KING:
				resId = R.drawable.cards_clubs_k;
				break;
			case QUEEN:
				resId = R.drawable.cards_clubs_q;
				break;
			case JACK:
				resId = R.drawable.cards_clubs_j;
				break;
			case TEN:
				resId = R.drawable.cards_clubs_10;
				break;
			case NINE:
				resId = R.drawable.cards_clubs_9;
				break;
			}
			break;
		case SPADES:
			switch(c.getRank()) {
			case ACE:
				resId = R.drawable.cards_spades_a;
				break;
			case KING:
				resId = R.drawable.cards_spades_k;
				break;
			case QUEEN:
				resId = R.drawable.cards_spades_q;
				break;
			case JACK:
				resId = R.drawable.cards_spades_j;
				break;
			case TEN:
				resId = R.drawable.cards_spades_10;
				break;
			case NINE:
				resId = R.drawable.cards_spades_9;
				break;
			}
			break;
		case DIAMONDS:
			switch(c.getRank()) {
			case ACE:
				resId = R.drawable.cards_diamonds_a;
				break;
			case KING:
				resId = R.drawable.cards_diamonds_k;
				break;
			case QUEEN:
				resId = R.drawable.cards_diamonds_q;
				break;
			case JACK:
				resId = R.drawable.cards_diamonds_j;
				break;
			case TEN:
				resId = R.drawable.cards_diamonds_10;
				break;
			case NINE:
				resId = R.drawable.cards_diamonds_9;
				break;
			}
			break;
		case HEARTS:
			switch(c.getRank()) {
			case ACE:
				resId = R.drawable.cards_hearts_a;
				break;
			case KING:
				resId = R.drawable.cards_hearts_k;
				break;
			case QUEEN:
				resId = R.drawable.cards_hearts_q;
				break;
			case JACK:
				resId = R.drawable.cards_hearts_j;
				break;
			case TEN:
				resId = R.drawable.cards_hearts_10;
				break;
			case NINE:
				resId = R.drawable.cards_hearts_9;
				break;
			}
			break;
		}
		
		return resId;
	}

}
