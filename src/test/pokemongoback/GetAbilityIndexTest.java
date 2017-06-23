package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Deenergize;
import model.Energy;
import model.Pokemon;
import model.Search;
import model.ability;
import model.healingAbility;
import model.swapAbility;

	
public class GetAbilityIndexTest {
	String expected;
	String actual;
	
	@Test
	public void test() {
		ArrayList<ability> abilities = new ArrayList<ability>();
		
		Pokemon p1=new Pokemon(0, null, null, 0, abilities, null);
		healingAbility h1=new healingAbility("healing", 0, null);
		swapAbility s1=new swapAbility("swap", "Pikachu", "Raichu");
		Search s2=new Search("search", null, null, null, null, 0);
		
		abilities.add(h1);
		abilities.add(s1);
		abilities.add(s2);

		p1.addActiveAbility(h1);
		p1.addActiveAbility(s1);
		p1.addActiveAbility(s2);
		
		expected="1";
		actual=p1.getAbilityIndex(s1);
		assertEquals(expected,actual);
	}

}
