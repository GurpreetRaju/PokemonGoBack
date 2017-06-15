package test.pokemongoback;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Deenergize;
import model.Player;
import model.Pokemon;
import model.Search;
import model.healingAbility;
import model.swapAbility;
	
	
public class GetAbilityIndexTest {
	String expected;
	String actual;
	
	@Test
	public void test() {
		Pokemon p1=new Pokemon(0, null, null, 0, null);
		healingAbility h1=new healingAbility("healing", 0, null);
		swapAbility s1=new swapAbility("swap", null, null);
		Search s2=new Search("search", null, null, null, null, 0);
		Deenergize d1=new Deenergize("deenergize", null, null);
		
		p1.addActiveAbility(h1);
		p1.addActiveAbility(s1);
		p1.addActiveAbility(s2);
		p1.addActiveAbility(d1);
		
		expected="1";
		actual=p1.getAbilityIndex(s1);
		assertEquals(expected,actual);
	}

}
