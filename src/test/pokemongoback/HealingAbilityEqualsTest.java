package test.pokemongoback;

import static org.junit.Assert.*;

import org.junit.Test;

import test.stubs.CardsGroup;
//import model.Deenergize;
import model.healingAbility;

public class HealingAbilityEqualsTest {

	Boolean actual;
	Boolean expected;
	
	@Test
	public void test() {
		healingAbility h1=new healingAbility("heal", 99, "yourActive");
		CardsGroup cg=new CardsGroup();
		expected=false;
		actual=h1.equals(cg);
		assertEquals(expected,actual);
	}
	
	@Test
	public void test1() {
		healingAbility h2=new healingAbility("heal", 99, "yourActive");
		actual=h2.equals(h2);
		expected=true;
		assertEquals(expected,actual);
		}

}
