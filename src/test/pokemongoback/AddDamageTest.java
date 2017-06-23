package test.pokemongoback;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import model.Pokemon;
import model.UserPlayer;
import model.ability;
import model.basicPokemon;
import model.pokemonStage;
public class AddDamageTest 
{
	ArrayList<ability> newAbilities=new ArrayList<ability>();
	pokemonStage basic=new basicPokemon();
	Pokemon pk = new Pokemon(2, "Pikachu", basic, 60, newAbilities, null);
	UserPlayer up= new UserPlayer("john");

	@Test
	public void test()
	{
		int expected=10;
		pk.addDamage(10);
		int actual=pk.getDamage();
		assertEquals(expected, actual);
		String expected2="deck";
		String expected1="knockedOut";
		up.setActivePokemon(pk);
		pk.addDamage(10);
		String actual2=pk.getState();
		assertEquals(expected2, actual2);
		pk.addDamage(60);
		String actual1=pk.getState();
		pk.addDamage(80);
		String actual3=pk.getState();
		assertEquals(expected1, actual3);
		assertEquals(expected1, actual1);	
	}

}
