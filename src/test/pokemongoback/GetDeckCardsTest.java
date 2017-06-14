package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Pokemon;
import model.Trainer;
import model.cardItem;
import model.CardsGroup;
import model.Debug;
import model.Player;

public class GetDeckCardsTest {
	cardItem expected;
	cardItem actual;

	@Test
	public void test() {
		Player p1=new Player(null);
		
		cardItem pokemon1=new Trainer(69 , "Shauna", "supporter", null);
		
		cardItem[] actualc = p1.getDeckCards(1);
		Debug.message(actualc[0].getName());
		
		assertEquals(pokemon1.getName(),actualc[0].getName());
	
	}

}
