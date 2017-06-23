package test.IntegrationTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import model.CardsGroup;
import model.Pokemon;
import model.Search;
import model.Trainer;
import model.ability;
import model.cardItem;
import model.healingAbility;
import model.swapAbility;

public class GetCardTest {
	cardItem actual;
	cardItem expected;
	CardsGroup cg=new CardsGroup();
	
	@Test
	public void getCardTest(){
		ArrayList<ability> abilities = new ArrayList<ability>();
		
		Pokemon p1=new Pokemon(0, null, null, 0, abilities, null);
		healingAbility h1=new healingAbility("healing", 0, null);
		swapAbility s1=new swapAbility("swap", "Pikachu", "Raichu");
		Search s2=new Search("search", null, null, null, null, 0);
		
		abilities.add(h1);
		abilities.add(s1);
		abilities.add(s2);
		
		
		
		cardItem pokemon1=new Pokemon(80, "Pikachu", null, 0, abilities, null);
		cg.addCard(pokemon1);
		
		actual=cg.getCard(pokemon1.getID());
		expected=pokemon1;
	
		assertEquals(expected,actual);
	}
	
	@Test
	public void getCardTest2(){
		ArrayList<ability> abilities = new ArrayList<ability>();
		
		Pokemon p1=new Pokemon(0, null, null, 0, abilities, null);
		healingAbility h1=new healingAbility("healing", 0, null);
		swapAbility s1=new swapAbility("swap", "Pikachu", "Raichu");
		Search s2=new Search("search", null, null, null, null, 0);
		
		abilities.add(h1);
		abilities.add(s1);
		abilities.add(s2);
		
		
		cardItem pokemon1=new Pokemon(80, "Pikachu", null, 0, abilities, null);
		cg.addCard(pokemon1);
		
		int id=99;
		actual=cg.getCard(id);
		expected=null;
	
		assertEquals(expected,actual);
	}
}
