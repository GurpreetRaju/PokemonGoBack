package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Pokemon;
import model.UserPlayer;
import model.ability;
import model.basicPokemon;
import model.damageAbility;
import model.pokemonStage;
import model.stageOnePokemon;

public class TotalEnergyRequiredTest {

	
	ArrayList<ability> newAbilities=new ArrayList<ability>();
	pokemonStage basic=new basicPokemon();
	pokemonStage stageone= new stageOnePokemon("Pikachu");
	Pokemon pk = new Pokemon(2, "Pikachu", basic, 60, newAbilities);
	Pokemon rk = new Pokemon(2, "raikachu", stageone, 60, newAbilities);
	UserPlayer up= new UserPlayer("john");
	
	@Test
	public void test1() {
		// tests if energy required is 0
		//System.out.println(pk.totalEnergyRequired());
		assertTrue(pk.totalEnergyRequired()==0);
		
	}
	
	
		
		@Test
		public void test2() {
			// tests if energy required is more than 0
			
			
			pk.attachCard(rk);
			rk.getAbilities();
				for(ability ablt : rk.getAbilities())
				System.out.println(((damageAbility) ablt).getEnergyInfo());
				
				System.out.println(rk.totalEnergyRequired());
				//pk.totalEnergyRequired();
				
			
			
		}
		
	}


