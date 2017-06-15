package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.AIplayer;
import model.Energy;
import model.Player;
import model.Pokemon;
import model.Trainer;
import model.ability;
import model.basicPokemon;
import model.cardItem;
import model.damageAbility;
import model.healingAbility;
import model.swapAbility;

public class CheckAndPlayEnergyTest {
	Boolean actual;
	Boolean expected;
	AIplayer p1=new AIplayer(null);
	
	@Test
	public void test() {
		
		ArrayList<Energy> energyCards = new ArrayList<Energy>();
		
		Energy energy1=new Energy("Lightening");
		Energy energy2=new Energy("Fight");
		
		energyCards.add(energy1);
		energyCards.add(energy2);
		
		damageAbility damage=new damageAbility(null, 0, energyCards, null, null);
		ArrayList<ability> newAbilities=new ArrayList<ability>();;
		newAbilities.add(damage);
		
		Pokemon pokemon1=new Pokemon(80, "Pikachu", new basicPokemon(), 0,newAbilities );
		
		p1.setActivePokemon(pokemon1);
		pokemon1.attachCard(energyCards.get(0));
		
	//System.out.println(pokemon1.getAttachedCards().length);
	//System.out.println(pokemon1.totalEnergyRequired());
	
		expected=true;
		actual=p1.checkAndPlayEnergy(energyCards);
		assertEquals(expected,actual);
		
	}
	
	@Test
	public void test1(){
ArrayList<Energy> energyCards = new ArrayList<Energy>();
		
		Energy energy1=new Energy("Lightening");
		Energy energy2=new Energy("Fight");
		
		energyCards.add(energy1);
		energyCards.add(energy2);
		
		damageAbility damage=new damageAbility(null, 0, energyCards, null, null);
		ArrayList<ability> newAbilities=new ArrayList<ability>();;
		newAbilities.add(damage);
		Pokemon pokemon1=new Pokemon(80, "Pikachu", new basicPokemon(), 0,newAbilities );
		
		p1.setActivePokemon(pokemon1);
		pokemon1.attachCard(energyCards.get(0));
		pokemon1.attachCard(energyCards.get(1));
		
		//for else part true condition
		
		cardItem pokemon2=new Pokemon(80, "Pikachu", new basicPokemon(), 0, newAbilities);
		//cardItem trainercard=new Trainer(32,"Potion", null,null);
		cardItem[] newcards = {pokemon2};
		p1.getBench().addCards(newcards);
		((Pokemon) pokemon2).attachCard(energy1);
		
		
		expected=true;
		actual=p1.checkAndPlayEnergy(energyCards);
		assertEquals(expected,actual);
		
	}
	
	@Test
	public void test3(){
ArrayList<Energy> energyCards = new ArrayList<Energy>();
		
		Energy energy1=new Energy("Lightening");
		Energy energy2=new Energy("Fight");
		
		energyCards.add(energy1);
		energyCards.add(energy2);
		
		damageAbility damage=new damageAbility(null, 0, energyCards, null, null);
		ArrayList<ability> newAbilities=new ArrayList<ability>();;
		newAbilities.add(damage);
		Pokemon pokemon1=new Pokemon(80, "Pikachu", new basicPokemon(), 0,newAbilities );
		
		p1.setActivePokemon(pokemon1);
		pokemon1.attachCard(energyCards.get(0));
		pokemon1.attachCard(energyCards.get(1));
		
		//for else part true condition
		
		cardItem pokemon2=new Pokemon(80, "Pikachu", new basicPokemon(), 0, newAbilities);
		//cardItem trainercard=new Trainer(32,"Potion", null,null);
		cardItem[] newcards = {pokemon2};
		p1.getBench().addCards(newcards);
		((Pokemon) pokemon2).attachCard(energy1);
		((Pokemon) pokemon2).attachCard(energy2);
		
		
		expected=false;
		actual=p1.checkAndPlayEnergy(energyCards);
		assertEquals(expected,actual);
		
	}

}
