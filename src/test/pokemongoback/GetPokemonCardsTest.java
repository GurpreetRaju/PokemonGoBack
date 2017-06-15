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

public class GetPokemonCardsTest {

	Pokemon actual;
	Pokemon expected;
	CardsGroup cg=new CardsGroup();
	
	@Test
	public void test() {
		
		
		cardItem pokemon1=new Pokemon(80, "Pikachu", new basicPokemon(), 0, null);
		cg.addCard(pokemon1);
		
		cardItem trainercard=new Trainer("Potion", 32, null);
		cg.addCard(trainercard);
		
		cardItem trainercard1=new Trainer("Wally", 74, null);
		cg.addCard(trainercard1);
		cardItem energycard=new Energy("Fight");
		cg.addCard(energycard);
		
		expected= (Pokemon) pokemon1;
		actual=(cg.getAllPokemonCard("Basic")).get(0);
		
		assertEquals(expected,actual);
	}

}
