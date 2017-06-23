package test.pokemongoback;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Player;
import test.stubs.Trainer;
import test.stubs.cardItem;
import controller.GameController;

public class DealCardsTest {
	
	String expected;
	String actual;
	
	@Test
	public void test() {
		GameController.getInstance().test=true;
		
		Player p1=new Player(null);
		cardItem pokemon1=new Trainer(69 , "Shauna", "supporter", null);
		expected=pokemon1.getName();
		actual=p1.dealCard().getName();
		
		assertEquals(expected,actual);
	}

}
