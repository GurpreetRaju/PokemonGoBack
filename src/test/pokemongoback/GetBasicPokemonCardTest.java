package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Pokemon;
import model.cardItem;

public class GetBasicPokemonCardTest {

	@Test
	public void test() {
		String actual = null;
		String expected="Basic";
		ArrayList<cardItem> groupCards = new ArrayList<cardItem>();
		
		for(cardItem card : groupCards){
			if(card instanceof Pokemon && ((Pokemon) card).getStage()=="Basic"){
				groupCards.remove(card);
			 actual=((Pokemon) card).getStage();
			}
			assertEquals(expected,actual);
	
		
	}
	}

}
