package test.pokemongoback;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import model.AIplayer;
import model.CardsGroup;
import model.Energy;
import model.Player;
import model.Pokemon;
import model.Redamage;
import model.Reenergize;
import model.Shuffle;
import model.Turn;
import model.UserPlayer;
import model.ability;
import model.applystatAbility;
import model.basicPokemon;
import model.cardItem;
import model.healingAbility;
import model.pokemonStage;
import model.swapAbility;
import model.drawAbility;
import model.Deenergize;
import model.damageAbility;
import model.destatAbility;
import model.DeckAbility;


public class UseAbilityTest {
	
	
	@Test
	public void damageTestAndReDamage() {
		
		// it will check for damage 
		UserPlayer player= new UserPlayer("john");
	    player.setTurn(true);
	    AIplayer ai = new AIplayer("Me");
	    ((AIplayer) ai).setTurn(false);
	    Turn.getInstance().setPlayer(ai, player);
	    ArrayList<ability> newAbilities=new ArrayList<ability>();
		pokemonStage basic=new basicPokemon();
		Pokemon pk = new Pokemon(2, "Pikachu", basic, 80, newAbilities);			
		player.setActivePokemon(pk);
		ArrayList<Energy> newEnergyInfo=null;
		String count=null;
		
		System.out.println("before adding damage--"+pk.getDamage());
		pk.addDamage(20);
		ability ablt1 = new damageAbility("damage",10,newEnergyInfo, "youractive",count);
		ablt1.useAbility();
		System.out.println("after damage---"+pk.getDamage());
		ability ablt11 = new Redamage("Redamage","opponentactive","opponentBench","10");
		ablt11.useAbility();
		System.out.println("after Redamage---"+pk.getDamage());
		
	}

	
	  @Test
	
	public void swapTest() {
		
		// it will check for swap 
		
		   	UserPlayer player= new UserPlayer("john");
		   // player.setTurn(true);
		   // Turn.getInstance().setPlayer(new AIplayer("Me"), player);
		    ArrayList<ability> newAbilities=new ArrayList<ability>();
			pokemonStage basic=new basicPokemon();
			Pokemon pk1 = new Pokemon(2, "Pikachu", basic, 80, newAbilities);			
			player.setActivePokemon(pk1);
			//Pokemon pk2 = new Pokemon(3, "Raichu", basic, 60, newAbilities);
		//	cardItem pk2= new Pokemon(3, "Raichu", basic, 60, newAbilities);
			player.getBench().addCard(new Pokemon(3, "Raichu", basic, 60, newAbilities));
			
			System.out.println("active----"+player.getActivePokemon().getName());
			for(cardItem card: player.getBench().getCard()){
				System.out.println("bench---" + card.getName());
				
			}
			//System.out.println("444"+player.getBench().getName());
			ability ablt2 = new swapAbility("Swap","youractive" , "yourbench");
			ablt2.useAbility();
			
			System.out.println("after swap active----"+player.getActivePokemon().getName());
			System.out.println("after swap bench----"+player.getBench().getBasicPokemonCard().getName());
			
	  }

	  @Test
		
		public void healTest() {
			
			// it will check for healing ability
		    UserPlayer player= new UserPlayer("john");
		    player.setTurn(true);
		    Turn.getInstance().setPlayer(new AIplayer("Me"), player);
		    ArrayList<ability> newAbilities=new ArrayList<ability>();
			pokemonStage basic=new basicPokemon();
			Pokemon pk = new Pokemon(2, "Pikachu", basic, 80, newAbilities);			
			player.setActivePokemon(pk);
			
			pk.addDamage(20);
			ability ablt3 = new healingAbility("Heal", 10, "youractive");
			ablt3.useAbility();
			assertEquals(10, pk.getDamage());
			
		}

		
		
	  @Test
		
		public void deenergizeAndReenergizeTest() {
			
			// it will check for deenergize and reenergize 
		    UserPlayer player= new UserPlayer("john");
		    player.setTurn(true);
		    Turn.getInstance().setPlayer(new AIplayer("Me"), player);
		    ArrayList<ability> newAbilities=new ArrayList<ability>();
			pokemonStage basic=new basicPokemon();
			Pokemon pk = new Pokemon(2, "Pikachu", basic, 80, newAbilities);			
			player.setActivePokemon(pk);
			String newEnergy = null;
			cardItem card= new Energy(newEnergy);
		    
			pk.attachCard(card);
			System.out.println("energy cards before deenerggize---"+pk.getAttachedCardsCount());
		    
			ability ablt4 = new Deenergize("Deenergize", "youractive","1");
			ablt4.useAbility();
			System.out.println("energy cards after deenerggize---"+pk.getAttachedCardsCount());
			
			ability ablt5= new Reenergize("Reenergize", "yourhand", "youractive", 2);
			ablt5.useAbility();
			System.out.println("energy cards after reenerggize---"+pk.getAttachedCardsCount());
			
			
			
		}
	  
	  @Test
		
		public void drawTest() {
		  
		  // it will test for draw ability
		  	UserPlayer player= new UserPlayer("john");
		    player.setTurn(true);
		    Turn.getInstance().setPlayer(new AIplayer("Me"), player);
		    ArrayList<ability> newAbilities=new ArrayList<ability>();
			pokemonStage basic=new basicPokemon();
			Pokemon pk = new Pokemon(2, "Pikachu", basic, 80, newAbilities);			
			player.setActivePokemon(pk);
			Pokemon pk1 = new Pokemon(2, "Raichu", basic, 60, newAbilities);
			((CardsGroup) player.getInhand()).addCard(pk1);
		
			System.out.println("inhand cards before draw--"+player.getInhandCards().length);
			ability ablt6= new model.drawAbility("draw", 3, "youractive");
			ablt6.useAbility();
			System.out.println("inhand cards after draw--"+player.getInhandCards().length);
		  
	  }
	  
	  @Test
		
		public void deckTest() {
		  
		  	// it will test for deck ability
		  
		  	UserPlayer player= new UserPlayer("john");
		    //player.setTurn(true);
		    //Turn.getInstance().setPlayer(new AIplayer("Me"), player);
		    ArrayList<ability> newAbilities=new ArrayList<ability>();
			pokemonStage basic=new basicPokemon();
			Pokemon pk = new Pokemon(2, "Pikachu", basic, 80, newAbilities);			
			//player.setActivePokemon(pk);
			Pokemon pk1 = new Pokemon(2, "Raichu", basic, 60, newAbilities);
			((CardsGroup) player.getInhand()).addCard(pk);
			((CardsGroup) player.getInhand()).addCard(pk1);
			System.out.println("inhand cards before deck ability--"+player.getInhandCards().length);
			ability ablt7= new DeckAbility("Deck", "yourhand","deck", 1, "2");
			ablt7.useAbility();
			System.out.println("inhand cards after deck ability--"+player.getInhandCards().length);
	  
	  }
	  
	  @Test
		
		public void shuffleTest() {
		  // it will test shuffle ability
		  UserPlayer player= new UserPlayer("johny");
		  
		  String deck=player.getDeck().getName();
		  System.out.println("deck before shuffling---"+player.getDeck().getCard(0).getName());
		  ability ablt8= new Shuffle("Shuffle",deck );
		  ablt8.useAbility();
		  System.out.println("deck after shuffling---"+player.getDeckCards(0));
	  }
	  
	  @Test
		
		public void applystatAndDestat() {
		  
		  // it will test applystat and destat ability
		  String status = "poisened";
		  UserPlayer player= new UserPlayer("john");
		  ArrayList<ability> newAbilities=new ArrayList<ability>();
		  pokemonStage basic=new basicPokemon();
		  Pokemon pk = new Pokemon(2, "Pikachu", basic, 80, newAbilities);			
		  player.setActivePokemon(pk);
		  
		  System.out.println(player.getActivePokemon().getStatus());
		  ability ablt9= new applystatAbility("Applystat", "youractive", status);
		  ablt9.useAbility();
		  System.out.println(player.getActivePokemon().getStatus());	  
		  
		  ability ablt10= new destatAbility("Destat", "youractive");
		  ablt10.useAbility();
		  System.out.println(player.getActivePokemon().getStatus());
		  
	  }
	  
	  
}
