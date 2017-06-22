package test.pokemongoback;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Debug;
import model.Player;
import model.Trainer;
import model.cardItem;

public class DealMultipleCardsTest {
	cardItem expected;
	cardItem actual;

	@Test
	public void test() {
		Player p1=new Player(null);
		
		cardItem pokemon1=new Trainer(69 , "Shauna", "supporter", null);
		
		cardItem[] actualc = p1.dealMultipleCards(1);
		Debug.message(actualc[0].getName());
		
		assertEquals(pokemon1.getName(),actualc[0].getName());
	}
	
	@Test
	public void test1() {
		cardItem[] actual;
		cardItem[] expected;
		Player p1=new Player(null);
		
		cardItem pokemon1=new Trainer(69 , "Shauna", "supporter", null);
		cardItem pokemon2=new Trainer(70 , "Pokémon Fan Club", "supporter", null);
		cardItem[] cards={pokemon1,pokemon2};
		//Pokémon Fan Club:trainer:cat:supporter:70
		cardItem[] actualc = p1.dealMultipleCards(2);
		Debug.message(actualc[0].getName());
		for(int i=0;i<2;i++){
		assertEquals(cards[i].getName(),actualc[i].getName());
	}}

}
