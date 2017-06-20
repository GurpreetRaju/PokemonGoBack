package test.pokemongoback;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Player;
import model.Trainer;
import model.cardItem;



public class DealCardsTest {
	String expected;
	String actual;
	
	@Test
	public void test() {
		Player p1=new Player(null);
		
		cardItem pokemon1=new Trainer(69 , "Shauna", "supporter", null);
		expected=pokemon1.getName();
		actual=p1.dealCard().getName();
		
		assertEquals(expected,actual);
	}

}
