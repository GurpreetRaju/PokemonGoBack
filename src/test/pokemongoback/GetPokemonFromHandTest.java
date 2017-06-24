package test.pokemongoback;

import static org.junit.Assert.*;

import org.junit.Test;

import model.CardsGroup;
import model.Player;
import model.Pokemon;
import model.Trainer;
import model.basicPokemon;
import model.cardItem;
import controller.GameController;

public class GetPokemonFromHandTest {
	Player p1=new Player(null);
	CardsGroup cg=new CardsGroup();
	cardItem actual;
	cardItem expected;
	
	@Test
	public void test() {
		GameController.getInstance().test=true;
		cardItem[] card=p1.dealMultipleCards(5);
		expected= card[4];
		actual=p1.getPokemonFromHand();
		assertEquals(expected,actual);
	}
	
	@Test
	public void test1() {
		GameController.getInstance().test=true;
		cardItem[] card=p1.dealMultipleCards(4);
		//because the first four cards in the deck are not of pokemon type
		//hence the function returns null
		
		expected= null;
		actual=p1.getPokemonFromHand();
		assertEquals(expected,actual);
	}
	
	

}
