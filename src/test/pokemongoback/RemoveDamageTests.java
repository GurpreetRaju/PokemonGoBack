package test.pokemongoback;
import controller.GameController;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;
import org.omg.Messaging.SyncScopeHelper;
import javafx.scene.layout.HBox;
import model.Pokemon;
import model.UserPlayer;
import model.ability;
import model.basicPokemon;
import model.cardItem;
import model.pokemonStage;

public class RemoveDamageTests {

	
	@Test
	public void test(){
		
	
		ArrayList<ability> newAbilities=new ArrayList<ability>();
		pokemonStage basic=new basicPokemon();
		Pokemon pk = new Pokemon(2, "Pikachu", basic, 80, newAbilities, null);
		// test if damage is 0, heal is 10, result should be 0.
		int expected=0,expected1=10,expected2=20;
		pk.removeDamage(10);
		int actual=pk.getDamage();
		assertEquals(expected,actual);
		
		// test if damage is 10, heal is 10, result should be 0.
		pk.addDamage(10);
		pk.removeDamage(10);
		int actual1=pk.getDamage();
		assertEquals(expected,actual1);
		
		// test if damage is 10, heal is 20, result should be 0.
		pk.addDamage(10);
		pk.removeDamage(20);
		int actual2=pk.getDamage();
		assertEquals(expected,actual2);
		
		// test if damage is 20, heal is 10, result should be 10.
		pk.addDamage(20);
		pk.removeDamage(10);
		int actual3=pk.getDamage();
		assertEquals(expected1,actual3);
		
		// test if damage is 10, heal is 0, result should be 20.
		pk.addDamage(10);
		pk.removeDamage(0);
		int actual4=pk.getDamage();
		assertEquals(expected2,actual4);
		


}
}
 