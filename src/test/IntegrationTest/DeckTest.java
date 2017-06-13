package test.IntegrationTest;

import model.Deck;
import model.DeckFileReader;
import model.Pokemon;
import model.cardItem;

import org.junit.experimental.categories.Category;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class DeckTest {
	
	@Test
	public void integrationDeckTest(){
	
		Deck deck = new Deck(1);
		//ArrayList<String[]> cardsList = null;
		deck.buildDeck();
		
		ArrayList<cardItem> deckCards = deck.getGroupCards();
		
		String[] expectedDeck = {"Doduo GEN 55","1 Dodrio GEN 56","1 Meowth AOR 61","1 Meowth GEN 53","1 Persian GEN 54"
				,"2 Diglett GEN 38","1 Dugtrio GEN 39","2 Geodude GEN 43","1 Hitmonchan GEN 48","1 Hitmonlee GEN 47"
				,"3 Machop GEN 40","2 Machoke GEN 41","2 Espurr GEN 114","1 Meowstic GEN 115","2 Gastly GEN 33","1 Haunter GEN 34"
				,"2 Jirachi GEN 113","1 Jynx FFI 37","1 Slowpoke GEN 32","2 Zubat GEN 30"};
	
		for(cardItem card: deckCards){
			for(String s: expectedDeck){
				if(s==card.getName()){
					Assert.assertEquals(s, card.getName());					
				}
			}
		}
	}

}
