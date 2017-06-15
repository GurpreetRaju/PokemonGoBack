package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Pokemon;
import model.UserPlayer;
import model.ability;
import model.basicPokemon;
import model.pokemonStage;
import model.stageOnePokemon;

public class EvolveTest {

	// pending
	@Test
	public void test() {
		String expected= "StageOne";
		ArrayList<ability> newAbilities=new ArrayList<ability>();
		pokemonStage newPokemonStage=new basicPokemon();
		
		Pokemon p= new Pokemon(2, "Pikachu", newPokemonStage, 80, newAbilities);
		pokemonStage stageone = new stageOnePokemon("Pikachu");
		Pokemon e= new Pokemon(3, "Raichu", stageone, 80, newAbilities);
		UserPlayer up= new UserPlayer("alex");
		
		up.setActivePokemon(p);
		System.out.println(up.getActivePokemon().getName()+"   "+up.getActivePokemon().getStage());
	
		
	    up.evolve(e, p);
		System.out.println(up.getActivePokemon().getName()+"   "+up.getActivePokemon().getStage());
		
		
		String actual=up.getActivePokemon().getStage();

		assertEquals(expected, actual);
		
	}

}
