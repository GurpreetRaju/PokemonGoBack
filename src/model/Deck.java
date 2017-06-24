package model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck extends CardsGroup{
	
	private String name;
	private int deckNumber;

	private DeckFileReader db;
	
	public Deck(){}
	
	public Deck(int n){
		this.deckNumber = n;
	}
	
	public void buildDeck(){
		db = new DeckFileReader(this.deckNumber);
		this.buildDeck(db.getDeck(), db);
	}
	
	public void readFile(){
		db = new DeckFileReader(this.deckNumber);
	}
	
	public void buildDeck(ArrayList<String[]> cardsList, DeckFileReader db){
	
		int x = 1;
		for(String[] card : cardsList){
				CardParser cparser = new CardParser(this);
				//Debug.message("Card no. "+ x + " Name: "+card[0]);
				switch(card[1]){
					
					case "pokemon":
						this.getGroupCards().add(cparser.createPokemon(x, card));
						break;
					case "trainer":
						this.getGroupCards().add(cparser.createTrainer(x, card));
						break;
					case "energy":
						this.getGroupCards().add(new Energy(card[3],x));
						//this.getGroupCards().
						break;
				}
				x++;
		}
	}
	
	/* Method for testing purpose only */
	public void buildDeckTest(){
		
		//cond:ability:deck:target:your:destination:discard:choice:you:1:(search:target:your:source:deck:filter:top:8:1,shuffle:target:your)
		int j=0;
		for(;j<18;j++){
			ArrayList<ability> newAbility = new ArrayList<ability>();
			ArrayList<EnergyNode> EnergyInfo = new ArrayList<EnergyNode>();
			EnergyInfo.add(new EnergyNode(new Energy("Fighting Energy"),0));
			String a = "Switch:swap:source:your-active:destination:choice:your-bench";
			AbilityParser ap = new AbilityParser();
			ability abilt = ap.parseAbilities(a, EnergyInfo);
			newAbility.add(abilt);
			Retreat retreat = new Retreat("Fighting Energy",1);
			//Debug.message(abilt.getName());
				this.getGroupCards().add(new Pokemon(j, "Pikachu", new basicPokemon(), 20, newAbility,retreat));

//				this.getGroupCards().add(new Trainer(j+18, "Heal Trainer", "item", new healingAbility("Heal pokemon",30,"youractive")));
//				this.getGroupCards().add(new Trainer(j+18, "Deck Ability", "item", new DeckAbility("Deck Ability","opponent", "deck", 0, "opponenthand")));
//				this.getGroupCards().add(new Trainer(j+18, "Wally", "item", new Search("Deck Ability","choiceyour", "deck", null, "evolvesfrom",1)));
				this.getGroupCards().add(new Trainer(j+18,"Deenergize" ,"item", abilt));

				this.getGroupCards().add(new Energy("Fighting Energy",j+36));
		}
		j=55;
		for(;j<60;j++){
			ArrayList<ability> newAbility = new ArrayList<ability>();
			ArrayList<EnergyNode> EnergyInfo = new ArrayList<EnergyNode>();
			EnergyInfo.add(new EnergyNode(new Energy("Fighting Energy"),1));
			this.getGroupCards().add(new Pokemon(j,"Raichu", new stageOnePokemon("Pikachu"), 30, newAbility,null));
		}
		//this.shufflecards();
	}

	public void display(){
		Debug.showCard(this.getGroupCards().toArray(new cardItem[this.getGroupCards().size()]));
	}
	
	public void shufflecards(){
		Collections.shuffle(this.getGroupCards());
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	public String getAbilityString(int line){
		return db.abilityR[line];
	}
	
}
