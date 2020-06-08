/*
 *Name: OJO, PETER OLUWATIMILEHIN.
 *Student No: 2974470.
 *HDC-HGP 
 */

// Imports
import java.util.Collections;
import java.util.Stack;

public class DeckOfCards {
	// Creating a stack of card.
	Stack<Card> deckOfCard = new Stack<Card>();

	// Constructor
	public DeckOfCards() {

	}

	// deck of Card method for populating the stack.
	public void deckOfCards() {
		for (int j = 1; j <= 13; j++) {
			for (int i = 0; i < 4; i++) {
				deckOfCard.add(new Card(j, i));
			}
			shuffle();
		}
	}

	// dealTopCard method
	public Card dealTopCard() {
		return deckOfCard.pop();
	}

	// Method for checking if the stack is empty.
	public boolean isEmpty() {
		if (deckOfCard.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	// Method used for shuffling the stack collection.
	public void shuffle() {
		Collections.shuffle(deckOfCard);
	}

}
