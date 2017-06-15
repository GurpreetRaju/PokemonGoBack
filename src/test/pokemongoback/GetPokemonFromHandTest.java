package test.pokemongoback;

import static org.junit.Assert.*;

import org.junit.Test;

import model.CardsGroup;
import model.Player;
import model.Pokemon;
import model.Trainer;
import model.basicPokemon;
import model.cardItem;

public class GetPokemonFromHandTest {
	Player p1=new Player(null);
	CardsGroup cg=new CardsGroup();
	cardItem actual;
	cardItem expected;
	
	@Test
	public void test() {
		
		cardItem[] card=p1.dealMultipleCards(5);
		expected= card[4];
		actual=p1.getPokemonFromHand();
		assertEquals(expected,actual);
	}
	
	@Test
	public void test1() {
		
		cardItem[] card=p1.dealMultipleCards(4);
		
		expected= null;
		actual=p1.getPokemonFromHand();
		assertEquals(expected,actual);
	}
	
	

}
