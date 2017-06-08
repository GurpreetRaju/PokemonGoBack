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
	//	String expected="knockedOut";
	
		int damage=0;
		int hitpoints=60;
		String actual = null;
		
		pokemonStage newPokemon2Stage = new basicPokemon();
		ArrayList<ability> newAbilities = new ArrayList<ability>();
		Pokemon pikachu = new Pokemon(1,"Pikachu",newPokemon2Stage, 60, newAbilities); 
		
		System.out.println(pikachu.getDamage());
		pikachu.addDamage(10);
		pikachu.addDamage(10);
		pikachu.addDamage(10);
		pikachu.addDamage(10);
		pikachu.addDamage(10);
		pikachu.addDamage(10);
		pikachu.addDamage(10);
		pikachu.addDamage(10);
		
		System.out.println(pikachu.getDamage());
		
	if(damage==hitpoints ||damage>=hitpoints)
		 actual = pikachu.getState();
		
		System.out.println(actual);
		//System.out.println(expected);
		assertEquals("knockedOut", actual);

}
}
