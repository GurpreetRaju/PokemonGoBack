package pokemongoback;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import model.Deck;
import model.cardItem;


public class CardsGroupTest {

	@Test
	public void test() {
		
		Deck group= new Deck();
		group.buildDeck(1);
		ArrayList<cardItem> actual=group.getGroupCards();
		int expected=60;
		
		assertEquals(expected,actual.size());
	}

}
