package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import model.CardsGroup;
import model.Pokemon;
import model.Trainer;
import model.cardItem;

public class GetCardTest {
	cardItem actual;
	cardItem expected;
	CardsGroup cg=new CardsGroup();
	
	@Test
	public void getCardTest(){
		
		cardItem pokemon1=new Pokemon(80, "Pikachu", null, 0, null);
		cg.addCard(pokemon1);
		
		actual=cg.getCard(pokemon1.getID());
		expected=pokemon1;
	
		assertEquals(expected,actual);
	}
	
	@Test
	public void getCardTest2(){
		
		cardItem pokemon1=new Pokemon(80, "Pikachu", null, 0, null);
		cg.addCard(pokemon1);
		
		int id=99;
		actual=cg.getCard(id);
		expected=null;
	
		assertEquals(expected,actual);
	}
}
