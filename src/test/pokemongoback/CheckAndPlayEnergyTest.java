package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.AIplayer;
import model.Debug;
import model.Energy;
import model.EnergyNode;
import model.Player;
import model.Pokemon;
import model.Trainer;
import model.ability;
import model.basicPokemon;
import model.cardItem;
import model.damageAbility;
import model.healingAbility;
import model.swapAbility;
import controller.GameController;

public class CheckAndPlayEnergyTest {
	Boolean actual;
	Boolean expected;
	AIplayer p1=new AIplayer(null);
	
	@Test
	public void test() {
		GameController.getInstance().test=true;
		ArrayList<Energy> energyCards = new ArrayList<Energy>();
		
		Energy energy1=new Energy("Lightening");
		//Energy energy2=new Energy("Fight");
		
		energyCards.add(energy1);
		//energyCards.add(energy2);
		
		ArrayList<EnergyNode> EnergyInfo = new ArrayList<EnergyNode>();
		EnergyInfo.add(new EnergyNode(new Energy("Fighting"),1));
		
		damageAbility damage=new damageAbility(null, 0, EnergyInfo, null, null);
		ArrayList<ability> newAbilities=new ArrayList<ability>();;
		newAbilities.add(damage);
		
		Pokemon p2=new Pokemon(0, null, null, 0, newAbilities, null);
		p2.checkEnergyNeeds(damage);
		Pokemon pokemon1=new Pokemon(80, "Pikachu", new basicPokemon(), 0,newAbilities, null );
		
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
		GameController.getInstance().test=true;
		ArrayList<Energy> energyCards = new ArrayList<Energy>();
		
		Energy energy1=new Energy("Lightening");
		Energy energy2=new Energy("Fight");
		energyCards.add(energy1);
		energyCards.add(energy2);
		
		ArrayList<EnergyNode> EnergyInfo = new ArrayList<EnergyNode>();
		EnergyInfo.add(new EnergyNode(new Energy("Fighting"),1));
		damageAbility damage=new damageAbility(null, 0, EnergyInfo, null, null);
		
		ArrayList<ability> newAbilities=new ArrayList<ability>();;
		newAbilities.add(damage);
		Pokemon pokemon1=new Pokemon(80, "Pikachu", new basicPokemon(), 0,newAbilities, null );
		
		p1.setActivePokemon(pokemon1);
		pokemon1.attachCard(energyCards.get(0));
		pokemon1.attachCard(energyCards.get(1));
		
		//for else part true condition
		
		cardItem pokemon2=new Pokemon(80, "Pikachu", new basicPokemon(), 0, newAbilities, null);
		
		cardItem[] newcards = {pokemon2};
		p1.getBench().addCards(newcards);
		((Pokemon) pokemon2).attachCard(energy1);
		
		Pokemon p2=new Pokemon(0, null, null, 0, newAbilities, null);
		p2.checkEnergyNeeds(damage);
		
		expected=true;
		actual=p1.checkAndPlayEnergy(energyCards);
		assertEquals(expected,actual);
		
	}
	
	@Test
	public void test3(){
		GameController.getInstance().test=true;
		ArrayList<Energy> energyCards = new ArrayList<Energy>();

		Energy energy1=new Energy("Lightening");
		Energy energy2=new Energy("Fighting");
		
		energyCards.add(energy1);
		energyCards.add(energy2);
		
		ArrayList<EnergyNode> EnergyInfo = new ArrayList<EnergyNode>();
		EnergyInfo.add(new EnergyNode(new Energy("Fighting"),1));
		EnergyInfo.add(new EnergyNode(new Energy("Lightening"),2));
		
		damageAbility damage=new damageAbility(null, 0, EnergyInfo, null, null);
		ArrayList<ability> newAbilities=new ArrayList<ability>();;
		newAbilities.add(damage);
		Pokemon pokemon1=new Pokemon(80, "Pikachu", new basicPokemon(), 0,newAbilities, null );
		
		p1.setActivePokemon(pokemon1);
		pokemon1.attachCard(new Energy("Lightening"));
		pokemon1.attachCard(new Energy("Lightening"));
		pokemon1.attachCard(new Energy("Fighting"));
		
		cardItem pokemon2=new Pokemon(80, "Pikachu", new basicPokemon(), 0, newAbilities, null);
		cardItem trainercard=new Trainer(32,"Potion", null,null);
		cardItem[] newcards = {pokemon2};
		p1.getBench().addCard(pokemon2);
		((Pokemon) pokemon2).attachCard(new Energy("Lightening"));
		((Pokemon) pokemon2).attachCard(new Energy("Lightening"));
		((Pokemon) pokemon2).attachCard(new Energy("Fighting"));
		
		Debug.message(pokemon1.getAttachedCardsCount());
		
		expected=true;
		actual=p1.checkAndPlayEnergy(energyCards);
		assertEquals(expected,actual);
		
	}

}
