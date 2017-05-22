package test.pokemongoback;

import static org.junit.Assert.*;
import org.junit.Test;
import model.Deck;

// checks whether both decks have equal values

public class DeckTest {

	@Test
	public void testDeck() {
		
		Deck deck= new Deck();
		Deck deck2= new Deck();
		deck.buildDeck(2);
		deck2.buildDeck(1);
		int noOfCards=deck.getGroupCards().size();
		int noOfCards2=deck2.getGroupCards().size();
		
		assertEquals(noOfCards,noOfCards2);
		
	}

}
