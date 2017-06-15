package test.stubs;

import java.util.ArrayList;
import java.util.Collections;

import model.Debug;
import model.DeckFileReader;
import model.Energy;
import model.Search;
import model.ability;
import model.basicPokemon;
import model.cardItem;
import model.damageAbility;
import model.healingAbility;
import model.pokemonStage;
import model.stageOnePokemon;

public class Deck extends CardsGroup{
	
	private String name;
	private int deckNumber;
	
	public Deck(){}
	
	public Deck(int n){
		this.deckNumber = n;
	}
	
	public void buildDeck(){
		DeckFileReader filereader = new DeckFileReader(this.deckNumber);
		this.buildDeck(filereader.getDeck());
	}
	
	public void buildDeck(ArrayList<String[]> cardsList){
		pokemonStage stage = new basicPokemon();
		ArrayList<ability> newAbility = new ArrayList<ability>();
		ArrayList<Energy> EnergyInfo = new ArrayList<Energy>();
		EnergyInfo.add(new Energy("Fighting"));
		newAbility.add(new damageAbility("Attack", 10, EnergyInfo, "opponentactive", null));
		
		int x = 1;
		for(String[] cards : cardsList){
			
				switch(cards[1]){
					
					case "pokemon":
						
						if(cards[3].equals("basic")){
							//Debug.message(cards[15] + cards[0]);
			//				this.getGroupCards().add(new Pokemon(x, cards[0], stage, Integer.parseInt(cards[6]), newAbility));
						}
						else if(cards[3].equals("stage-one")){
							//Debug.message(cards[0] + " evolves from " + cards[4]);
			//				this.getGroupCards().add(new Pokemon(x, cards[0], new stageOnePokemon(cards[4]), Integer.parseInt(cards[7]), newAbility));
						}
						else{
							Debug.message("Not Running " + cards[3]);
						}
						break;
					case "trainer":
						this.getGroupCards().add(new Trainer(x, cards[0], "item", new Search("Search pokemon", "you", "deck","pokemon","basic",2)));
						break;
					case "energy":
						this.getGroupCards().add(new Energy(cards[0],x));
						//this.getGroupCards().
						break;
				}
				x++;
			//}
			//Debug.message(this.getGroupCards().size());
		}
		
	}
	/* Method for testing purpose only */
	public void buildDeckTest(){
		ArrayList<ability> newAbility = new ArrayList<ability>();
		ArrayList<Energy> EnergyInfo = new ArrayList<Energy>();
		EnergyInfo.add(new Energy("Fighting"));
		newAbility.add(new damageAbility("Attack", 10, EnergyInfo,"opponentactive", null));
		int j=0;
		for(;j<18;j++){
		//		this.getGroupCards().add(new Pokemon(j, "Pikachu", new basicPokemon(), 20, newAbility));

				this.getGroupCards().add(new Trainer(j+18, "Heal Trainer", "item", new healingAbility("Heal pokemon",30,"youractive")));

				this.getGroupCards().add(new Energy("Fighting Energy",j+36));
		}
		for(;j<60;j++){
		//	this.getGroupCards().add(new Pokemon(j,"Raichu", new stageOnePokemon("Pikachu"), 30, newAbility));
		}
		
	}
	
	public void display(){
		Debug.showCard(this.getGroupCards().toArray(new cardItem[this.getGroupCards().size()]));
	}
	
	public void shufflecards(){
		Collections.shuffle(this.getGroupCards());
		//Debug.showCard(this.getGroupCards().toArray(new cardItem[this.getGroupCards().size()]));
	}
	
	@Override
	public String getName() {
		return this.name;
	}
}
