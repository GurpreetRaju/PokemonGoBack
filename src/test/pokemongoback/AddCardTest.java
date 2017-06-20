package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.CardsGroup;

import test.stubs.*;
import model.Pokemon;
import model.Trainer;
import model.cardItem;

public class AddCardTest {
	CardsGroup cg=new CardsGroup();
	cardItem expected;
	cardItem actual;

	@Test
	public void addCardTest(){
		
		cardItem trainercard=new Trainer(32,"Potion", null, null);
		cardItem pokemon1=new Pokemon(80, "Pikachu", null, 0, null);
	
		ArrayList<cardItem> pokemoncard = new ArrayList<cardItem>();
		pokemoncard.add(null);
		pokemoncard.add(trainercard);
		pokemoncard.add(pokemon1);
		
		cg.addCards(pokemoncard.toArray(new cardItem[pokemoncard.size()]));
		
		for(int i=0; i<3; i++){
			assertEquals(pokemoncard.get(i),cg.getGroupCards().get(i));
		}	
			
 	}
		}

