package test.pokemongoback;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Pokemon;
import model.cardItem;
import model.stageOnePokemon;

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
		stageOnePokemon s1=new stageOnePokemon(null);
		Pokemon pokemon1=new Pokemon(80, "Pikachu", null, 0, null);
		s1.evolve(pokemon1);
		expected=true;
		actual=s1.isEvolved();
		assertEquals(expected,actual);
	}
	
}
