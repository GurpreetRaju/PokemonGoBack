package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.CardsGroup;
import model.Energy;
import model.Pokemon;
import model.Trainer;
import model.basicPokemon;
import model.cardItem;

public class GetAllTrainerCardsTest {

	Trainer actual;
	Trainer expected;
	CardsGroup cg=new CardsGroup();
	
	@Test
	public void test() {
		
		cardItem pokemon1=new Pokemon(80, "Pikachu", new basicPokemon(), 0, null);
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
