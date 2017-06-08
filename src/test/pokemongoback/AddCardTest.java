package test.pokemongoback;

import static org.junit.Assert.*;

import org.junit.Test;
import model.CardsGroup;
import model.cardItem;

public class AddCardTest {
	CardsGroup cg=new CardsGroup();
	cardItem expected;
	cardItem actual;
	
	@Test
	public void addCardTest(){
		
		cardItem pokemoncard = null;
		cg.addCard(pokemoncard);
		
		expected=pokemoncard;
		actual=cg.getGroupCards().get(0);
		
		assertEquals(expected,actual);
	}
}
