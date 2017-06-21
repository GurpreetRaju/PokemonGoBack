package test.pokemongoback;

import static org.junit.Assert.*;

import org.junit.Test;

import model.CardsGroup;
import model.DeckAbility;
import model.ability;

public class EqualsTest {
	
	Boolean actual;
	Boolean expected;
	
	@Test
	public void test() {
		DeckAbility o1=new DeckAbility("Flash", "raichu", "opponentActive", 10, null);
		CardsGroup cg=new CardsGroup();
		expected=false;
		actual=o1.equals(cg);
		
		assertEquals(expected,actual);
	}
	
	@Test
	public void test1() {
		DeckAbility o2=new DeckAbility("Flash", "raichu", "opponentActive", 10, null);
		actual=o2.equals(o2);
		expected=true;
		assertEquals(expected,actual);
		}

}
