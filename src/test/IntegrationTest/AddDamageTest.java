package test.IntegrationTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Pokemon;
import model.UserPlayer;
import model.ability;
import model.basicPokemon;
import model.healingAbility;
import model.pokemonStage;
import model.swapAbility;
import controller.GameController;

public class AddDamageTest {
	
	@Test
	public void test1() {
		// tests if damage is added to the pokemon or not
		ArrayList<ability> newAbilities=new ArrayList<ability>();
		
		healingAbility h1=new healingAbility("healing", 0, null);
		swapAbility s1=new swapAbility("swap", "Pikachu", "Raichu");
		newAbilities.add(s1);
		newAbilities.add(h1);
		
		pokemonStage basic=new basicPokemon();
		Pokemon pk = new Pokemon(2, "Pikachu", basic, 60, newAbilities, null);
	
		int expected=10;
		
		pk.addDamage(10);
		int actual=pk.getDamage();
		
		assertEquals(expected, actual);
	}
		// KnockedOut conditions
		@Test
		public void test2(){
			// tests if hitpoints=60, damage =10, state should be deck.
			ArrayList<ability> newAbilities=new ArrayList<ability>();
			
			healingAbility h1=new healingAbility("healing", 0, null);
			swapAbility s1=new swapAbility("swap", "Pikachu", "Raichu");
			newAbilities.add(s1);
			newAbilities.add(h1);
			
			pokemonStage basic=new basicPokemon();
			Pokemon pk = new Pokemon(2, "Pikachu", basic, 60, newAbilities, null);
			
			String expected2="deck";
			UserPlayer up= new UserPlayer("john");
		
			up.setActivePokemon(pk);
			
			pk.addDamage(10);
			
			String actual2=pk.getState();
			assertEquals(expected2, actual2);
		}
		
		@Test
		public void test3(){
			// tests if hitpoints=60, damage =60, result should be knocked out conditions.
			GameController.getInstance().test=true;
			String expected1="knockedOut";
			
			ArrayList<ability> newAbilities=new ArrayList<ability>();
			healingAbility h1=new healingAbility("healing", 0, null);
			swapAbility s1=new swapAbility("swap", "Pikachu", "Raichu");
			newAbilities.add(s1);
			newAbilities.add(h1);
			
			pokemonStage basic=new basicPokemon();
			Pokemon pk = new Pokemon(2, "Pikachu", basic, 60, newAbilities, null);
		
			pk.addDamage(60);
		
			String actual1=pk.getState();

			assertEquals(expected1, actual1);	
	}
		@Test
		public void test4(){
			// tests if hitpoints=60, damage =80, state should be knockedOut.
			
			GameController.getInstance().test=true;
			String expected1="knockedOut";
			
			ArrayList<ability> newAbilities=new ArrayList<ability>();
			healingAbility h1=new healingAbility("healing", 0, null);
			swapAbility s1=new swapAbility("swap", "Pikachu", "Raichu");
			newAbilities.add(s1);
			newAbilities.add(h1);
			
			pokemonStage basic=new basicPokemon();
			Pokemon pk = new Pokemon(2, "Pikachu", basic, 60, newAbilities, null);
		
			pk.addDamage(80);
		
			String actual1=pk.getState();

			assertEquals(expected1, actual1);	
	}

}
