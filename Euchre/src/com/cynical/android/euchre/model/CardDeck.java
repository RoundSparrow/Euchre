package com.cynical.android.euchre.model;

import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Stack;

/**
 * A class to mimic a euchre card deck, consisting of 24 cards. Includes a realistic shuffling mechanism.
 * @author Adam
 *
 */
public class CardDeck {
	
	private static final int NUM_OF_CARDS = 24;
	
	private Stack<Card> deck;
	
	/**
	 * Creates a new CardDeck object
	 */
	public CardDeck() {
		deck = new Stack<Card>();
		deck.ensureCapacity(NUM_OF_CARDS);
		fillDeck();
		Collections.shuffle(deck);
		realisticShuffle(3);
	}
	
	/**
	 * Realistically shuffles the deck by introducing randomness and chance into the shuffle logic, 
	 * such as cards being stuck together, or not cutting the deck perfectly in half.
	 * @param times The number of times to shuffle the deck
	 */
	public void realisticShuffle(int times) {
		Random rand = new Random();
		
		for(int t = 0; t < times; t++) {
			int plusOrMinus = rand.nextInt(5);	//	Used to add cards to one of the halves
			plusOrMinus = rand.nextInt(2) == 0 ? (0 - plusOrMinus) : plusOrMinus; 	//	If random number is 0, make plusOrMinus negative
			
			//	Divide the deck in "half"
			Stack<Card> s1 = makeStack(deck.subList(0, (deck.size() / 2) + plusOrMinus));
			Stack<Card> s2 = makeStack(deck.subList((deck.size() / 2) + plusOrMinus, deck.size()));
			
			try {
				deck.clear();
				
				//	Do alternating shuffle, with random "stuck cards"
				while(!s1.isEmpty() && !s2.isEmpty()) {
					int r = rand.nextInt(10);
					if(r < 4) {
						for(int i = 0; i < r; i++) {
							deck.push(s1.pop());
						}
					}
					else {
						deck.push(s1.pop());
					}
					
					r = rand.nextInt(10);
					if(r < 4) {
						for(int i = 0; i < r; i++) {
							deck.push(s2.pop());
						}
					}
					else {
						deck.push(s2.pop());
					}
				}
			} catch(EmptyStackException e) {
				//	Expected
			} finally {
				//	Add the rest of the cards to the top of the deck
				if(s1.isEmpty()) {
					while(!s2.isEmpty()) {
						deck.push(s2.pop());
					}
				}
				else if(s2.isEmpty()) {
					while(!s1.isEmpty()) {
						deck.push(s1.pop());
					}
				}
			}
			
			//	Over-hand shuffle
			for(int i = 0; i < 3; i++) {
				Stack<Card> top = new Stack<Card>();
				Stack<Card> movingStack = new Stack<Card>();
				int start = rand.nextInt(NUM_OF_CARDS);
				int size = rand.nextInt(5) + 1;
				
				// Take top portion off the deck
				for(int j = 0; j < start; j++) {
					top.push(deck.pop());
				}
				// Take section to move off the deck
				for(int j = 0; j < size; j++) {
					try {
						movingStack.push(deck.pop());
					} catch(EmptyStackException e) {
						break;
					}
				}
				// Put top section back on deck
				while(!top.isEmpty()) {
					deck.push(top.pop());
				}
				// Put the moved section on top of the "top section"
				while(!movingStack.isEmpty()) {
					deck.push(movingStack.pop());
				}
			}
		}
	}
	
	/**
	 * Fills the deck with the original cards in "new deck" order; all suits are together, in face value order.
	 */
	private void fillDeck() {
		deck.clear();
		for(Suit s : Suit.values()) {
			for(FaceValue fv : FaceValue.values()) {
				deck.push(new Card(fv, s));
			}
		}
	}
	
	/**
	 * Creates a Stack object full of cards based on the passed in List object
	 * @param list The List object to convert to a Stack
	 * @return The Stack that was created
	 */
	private Stack<Card> makeStack(List<Card> list) {
		Stack<Card> s = new Stack<Card>();
		ListIterator<Card> iterator = list.listIterator();
		while(iterator.hasNext()) {
			s.push(iterator.next());
		}
		return s;
	}
	
	/**
	 * Draws a card from the top of the deck
	 * @return The top card of the deck
	 */
	public Card drawCard() {
		return deck.pop();
	}

}
