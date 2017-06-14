package test.pokemongoback;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Deenergize;
import model.Player;
import model.Search;
import model.healingAbility;
import model.swapAbility;
	
	
public class GetAbilityIndexTest {
	String expected;
	String actual;
	
	@Test
	public void test() {
		Player p1=new Player(null);
		healingAbility h1=new healingAbility("healing", 0, null);
		swapAbility s1=new swapAbility("swap", null, null);
		Search s2=new Search("search", null, null, null, null, 0);
		Deenergize d1=new Deenergize("deenergize", null, 0);
		
		p1.addActiveAbility(h1);
		p1.addActiveAbility(s1);
		p1.addActiveAbility(s2);
		p1.addActiveAbility(d1);
		
		expected="1";
		actual=p1.getAbilityIndex(s1);
		assertEquals(expected,actual);
	}

}
