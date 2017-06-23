package test.pokemongoback;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Deenergize;
import test.stubs.CardsGroup;


public class DeenergizeEqualsTest {

	Boolean actual;
	Boolean expected;
	
	@Test
	public void test() {
		Deenergize o1=new Deenergize("Flash", "opponentActive", "10");
		CardsGroup cg=new CardsGroup();
		expected=false;
		actual=o1.equals(cg);
		assertEquals(expected,actual);
	}
	
	@Test
	public void test1() {

	Deenergize o2=new Deenergize("Flash", "opponentActive", "10");

		actual=o2.equals(o2);
		expected=true;
		assertEquals(expected,actual);
		}

}
