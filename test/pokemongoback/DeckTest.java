package pokemongoback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Deck;
import model.cardItem;

public class DeckTest {

	@Test
	public void test() {
		
		Deck deck= new Deck();
		deck.buildDeck(1);
		ArrayList<cardItem> actual=deck.getGroupCards();
		int expected=60;
		
		assertEquals(expected,actual.size());
		
	}

}
