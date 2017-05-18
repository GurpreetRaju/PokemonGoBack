import static org.junit.Assert.*;

import org.junit.Test;

import model.basicPokemon;

public class basicPokemonTest {

	@Test
	public void test() {
		basicPokemon basic= new basicPokemon();
		String actual=basic.getStage();
		String expected="Basic";
		
		assertEquals(expected,actual);
	}

}
