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
		
		// tests if hitpoints=60, damage =60, result shuold be knocked out conditions.
		
		String expected1="knockedOut";
		up.setActivePokemon(pk);
		
		//System.out.println(pk.getDamage());
		pk.addDamage(80);
		//System.out.println(pk.getDamage());
		//System.out.println(pk.getState());
		String actual1=pk.getState();
		assertEquals(expected1, actual1);
		
		
		
		
	}

}
