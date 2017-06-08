package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Pokemon;
import model.cardItem;

public class GetBasicPokemonCardTest {

	@Test
	public void testBasicPokemon() {
		String actual = null;
		ArrayList<cardItem> groupCards = new ArrayList<cardItem>();
		for(cardItem card : groupCards){
			if(card instanceof Pokemon && ((Pokemon) card).getStage()=="Basic"){
				groupCards.remove(card);
			 actual=((Pokemon) card).getStage();
			}
			
		
			String expected="Basic";
			assertEquals(expected,actual);
	
		
	}
	}

}
