package test.pokemongoback;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Pokemon;
import model.ability;
import model.basicPokemon;
import model.pokemonStage;

public class KnockoutTest {

	@Test
	public void testKnockout() {
	
	
		int damage;
		int hitpoints=60;
		
		String expected= "knockedOut";
		

		
		ArrayList<ability> newAbilities=new ArrayList<ability>();
		pokemonStage basic=new basicPokemon();
		Pokemon pk = new Pokemon(2, "Pikachu", basic, 80, newAbilities);
		System.out.println(pk.getDamage());
		System.out.println(pk.getState());
		pk.addDamage(90);
		System.out.println(pk.getDamage());
		System.out.println(pk.getState());
		String actual=pk.getState();
		assertEquals(expected, actual);
}
}
