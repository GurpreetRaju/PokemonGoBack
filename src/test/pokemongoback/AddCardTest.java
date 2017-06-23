package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.CardsGroup;

import model.Pokemon;
import model.Search;
import model.Trainer;
import model.ability;
import model.basicPokemon;
import model.cardItem;
import model.pokemonStage;
import model.cardItem;
import model.healingAbility;
import model.swapAbility;

public class AddCardTest {
	CardsGroup cg=new CardsGroup();
	cardItem expected;
	cardItem actual;

	@Test
	public void addCardTest(){

ArrayList<ability> abilities = new ArrayList<ability>();
		
		healingAbility h1=new healingAbility("healing", 0, null);
		swapAbility s1=new swapAbility("swap", "Pikachu", "Raichu");
		Search s2=new Search("search", null, null, null, null, 0);
		
		abilities.add(h1);
		abilities.add(s1);
		abilities.add(s2);
		
		cardItem trainercard=new Trainer(32,"Potion", null, null);
		cardItem pokemon1=new Pokemon(80, "Pikachu", null, 0, abilities, null);
	
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

