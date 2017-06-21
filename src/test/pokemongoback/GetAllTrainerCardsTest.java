package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.CardsGroup;
import model.Energy;
import model.Pokemon;
import model.Search;
import model.Trainer;
import model.ability;
import model.basicPokemon;
import model.cardItem;
import model.healingAbility;
import model.swapAbility;

public class GetAllTrainerCardsTest {

	Trainer actual;
	Trainer expected;
	CardsGroup cg=new CardsGroup();
	
	@Test
	public void test() {
ArrayList<ability> abilities = new ArrayList<ability>();
		
		healingAbility h1=new healingAbility("healing", 0, null);
		swapAbility s1=new swapAbility("swap", "Pikachu", "Raichu");
		Search s2=new Search("search", null, null, null, null, 0);
		
		abilities.add(h1);
		abilities.add(s1);
		abilities.add(s2);
		
		cardItem pokemon1=new Pokemon(80, "Pikachu", new basicPokemon(), 0, abilities);
		cg.addCard(pokemon1);
		
		cardItem trainercard=new Trainer(32,"Potion", null, null);
		cg.addCard(trainercard);
		
		cardItem energycard=new Energy("Fight");
		cg.addCard(energycard);
		
		cardItem energycard2=new Energy("Lightening");
		cg.addCard(energycard);
		
		expected= (Trainer) trainercard;
		actual=cg.getAllTrainerCards().get(0);
		
		assertEquals(expected,actual);
	}

}
