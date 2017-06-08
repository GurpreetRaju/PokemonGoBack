package model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck extends CardsGroup{
	
	private String name;
	
	public void buildDeck(int deckNumber){
		DeckFileReader filereader = new DeckFileReader(deckNumber);
		ArrayList<String[]> cardsList = filereader.getDeck();
		pokemonStage stage = new basicPokemon();
		ArrayList<ability> newAbility = new ArrayList<ability>();
		Energy[] EnergyInfo = {new Energy("Fighting")};
		newAbility.add(new damageAbility("Attack", 10, EnergyInfo,"Pokemon"));
		
		int x = 1;
		for(String[] cards : cardsList){
			//int n = Integer.parseInt(cards[0]);
			//Debug.message(n+" "+cards[1]);
			//for(int i=1;i<=n;i++){\
			
			if(cards[1]=="pokemon"){
				Debug.message("Hello checkpoint");
			}
			
				switch(cards[1]){
					
					case "pokemon":
						if(cards[3].equals("basic")){
							//Debug.message("checkpoint3");
							this.getGroupCards().add(new Pokemon(x, cards[0], stage, Integer.parseInt(cards[6]), newAbility));
						}
						else if(cards[3].equals("stage-one")){
							//Debug.message(cards[3] + cards[6]);
							this.getGroupCards().add(new Pokemon(x, cards[0], new stageOnePokemon(cards[4]), Integer.parseInt(cards[7]), newAbility));
						}
						else{
							Debug.message("Not Running " + cards[3]);
						}
						break;
					case "trainer":
						this.getGroupCards().add(new Trainer(cards[0],x, new healingAbility("Heal pokemon",30)));
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
		this.shufflecards();
	}
	/* Method for testing purpose only */
	public void buildDeckTest(){
		ArrayList<ability> newAbility = new ArrayList<ability>();
		Energy[] EnergyInfo = {new Energy("Fighting")};
		newAbility.add(new damageAbility("Attack", 10, EnergyInfo,"Pokemon"));
		int j=0;
		for(;j<18;j++){
				this.getGroupCards().add(new Pokemon(j, "Pikachu", new basicPokemon(), 20, newAbility));
				this.getGroupCards().add(new Trainer("Heal Trainer",j+18, new healingAbility("Heal pokemon",30)));
				this.getGroupCards().add(new Energy("Fighting Energy",j+36));
		}
		for(;j<60;j++){
			this.getGroupCards().add(new Pokemon(j,"Raichu", new stageOnePokemon("Pikachu"), 30, newAbility));
		}
		this.shufflecards();
	}
	
	public void display(){
		Debug.showCard(this.getGroupCards().toArray(new cardItem[this.getGroupCards().size()]));
	}
	
	public void shufflecards(){
		//Collections.shuffle(this.getGroupCards());
		//Debug.showCard(this.getGroupCards().toArray(new cardItem[this.getGroupCards().size()]));
	}
	
	@Override
	public String getName() {
		return this.name;
	}
}
