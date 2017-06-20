package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Pokemon;
import model.UserPlayer;
import model.ability;
import model.basicPokemon;
import model.pokemonStage;

public class AddDamageTest {
	
	ArrayList<ability> newAbilities=new ArrayList<ability>();
	pokemonStage basic=new basicPokemon();
	Pokemon pk = new Pokemon(2, "Pikachu", basic, 60, newAbilities);
	UserPlayer up= new UserPlayer("john");

	@Test
	public void test() {
		
		// test if damage is added to the pokemon or not.
		int expected=10;
		//System.out.println(pk.getDamage());
		pk.addDamage(10);
		int actual=pk.getDamage();
		//System.out.println(pk.getDamage());
		assertEquals(expected, actual);
		
		// KnockedOut conditions

		
		// tests if hitpoints=60, damage =10, state should be deck.
		
		String expected2="deck";

		// tests if hitpoints=60, damage =60, result should be knocked out conditions.
		String expected1="knockedOut";

		up.setActivePokemon(pk);
		//System.out.println(pk.getDamage());
		pk.addDamage(10);
		//System.out.println(pk.getDamage());
		//System.out.println(pk.getState());
		String actual2=pk.getState();
		assertEquals(expected2, actual2);
		
		// tests if hitpoints=60, damage =60, state should be knockedOut.
		
		//String expected1="knockedOut";
		//System.out.println(pk.getDamage());
		pk.addDamage(60);
		//System.out.println(pk.getDamage());
		//System.out.println(pk.getState());
		String actual1=pk.getState();

		//assertEquals(expected1, actual1);
		
		// tests if hitpoints=60, damage =80, state should be knockedOut.
		//System.out.println(pk.getDamage());
		pk.addDamage(80);
		//System.out.println(pk.getDamage());
		//System.out.println(pk.getState());
		String actual3=pk.getState();
		assertEquals(expected1, actual3);
		

		assertEquals(expected1, actual1);	
	}

}
