package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Pokemon;
import model.Search;
import model.ability;
import model.cardItem;
import model.healingAbility;
import model.stageOnePokemon;
import model.swapAbility;

public class isEvolvedTest {

	Boolean expected;
	Boolean actual;
	
	@Test
	public void test(){
		stageOnePokemon s1=new stageOnePokemon(null);
		s1.evolve(null);
		expected=false;
		actual=s1.isEvolved();
		assertEquals(expected,actual);
	}
	
	@Test
	public void test1(){
		ArrayList<ability> abilities = new ArrayList<ability>();
		
		Pokemon p1=new Pokemon(0, null, null, 0, abilities);
		healingAbility h1=new healingAbility("healing", 0, null);
		swapAbility s1=new swapAbility("swap", "Pikachu", "Raichu");
		Search s2=new Search("search", null, null, null, null, 0);
		
		abilities.add(h1);
		abilities.add(s1);
		abilities.add(s2);
		
		stageOnePokemon s=new stageOnePokemon(null);
		Pokemon pokemon1=new Pokemon(80, "Pikachu", null, 0, abilities);
		s.evolve(pokemon1);
		expected=true;
		actual=s.isEvolved();
		assertEquals(expected,actual);
	}
	
}
