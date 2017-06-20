package model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck extends CardsGroup{
	
	private String name;
	private int deckNumber;

	private String abilityName, target, destination, drawCards, status, energyinfo, abilityparse ;
	private String damage, condition, condAbility, trigger, triggerCond, addAbility, source, filter, filterCat, count;
	private boolean choice;
	private DeckFileReader db;
	
	public Deck(){}
	
	public Deck(int n){
		this.deckNumber = n;
	}
	
	public void buildDeck(){
		db = new DeckFileReader(this.deckNumber);
		this.buildDeck(db.getDeck(), db);
	}
	
	public void buildDeck(ArrayList<String[]> cardsList, DeckFileReader db){
		
		
//		ArrayList<ability> newAbility = new ArrayList<ability>();
//		ArrayList<Energy> EnergyInfo = new ArrayList<Energy>();
//		EnergyInfo.add(new Energy("Fighting"));
//		newAbility.add(new damageAbility("Attack", 10, EnergyInfo, "opponentactive", null));
		
		int x = 1;
		for(String[] card : cardsList){
				//Debug.message("Card no. "+ x + " Name: "+card[0]);
				switch(card[1]){
					
					case "pokemon":
						this.createPokemon(x,card);
						break;
					case "trainer":
						ArrayList<ability> abilities = new ArrayList<ability>();
						AbilityParser ap = new AbilityParser();
						abilities.clear();
						abilities.add(ap.parseAbilities(db.getAbilityR(Integer.parseInt(card[4])-1)));
						if(abilities.isEmpty()){
							abilities.add(new Search("Search pokemon", "you", "deck","pokemon","basic",2));
						}
						//Debug.message(abilities.get(0).toString());
						this.getGroupCards().add(new Trainer(x, card[0], card[3], abilities.get(0)));
						break;
					case "energy":
						this.getGroupCards().add(new Energy(card[0],x));
						//this.getGroupCards().
						break;
				}
				x++;
			//}
			//Debug.message(this.getGroupCards().size());
		}
		//this.shufflecards();
	}
	private void createPokemon(int x,String[] card) {
		AbilityParser ap = new AbilityParser();
		ArrayList<ability> abilities = new ArrayList<ability>();
		String carditem = String.join(" ", card);
		//String retreat = carditem.substring(carditem.indexOf("retreat cat"), carditem.indexOf("attack"));
		String ability = carditem.substring(carditem.indexOf("attack"));
		
		
		String ability1, ability2;
		int index = ap.indexOf("\\d\\s+\\d+", ability);
		//Debug.message(index);
		ability1 = ability.substring(8, index);
		//Debug.message(ability1);
		
		String[] abilityone = ability1.split(",");
		String[] substring11 = abilityone[0].split("\\s+");
		switch(abilityone.length)
		{
		//create objects of separate abilities and pass to a new class composite ability
			case 1:
				//parseAbilities((substring11[1]+" "+substring11[2]+" "+ abilityR[Integer.parseInt(substring11[3])-1]));
				ap.getEnergy(substring11[1], substring11[2]);
				abilities.add(ap.parseAbilities(db.abilityR[Integer.parseInt(substring11[3])-1]));
				//Debug.message(db.abilityR[Integer.parseInt(substring11[3])-1]);
				break;
			case 2:
				String[] substring12 = abilityone[1].split("\\s+");
				//parseAbilities((substring11[1]+" "+substring11[2]+" "+substring12[1]+" "+substring12[2]+" "+abilityR[Integer.parseInt(substring12[3])-1]));

				ap.getEnergy(substring12[1],substring12[2]);
				ap.getEnergy(substring11[1], substring11[2]);
				abilities.add(ap.parseAbilities(db.abilityR[Integer.parseInt(substring12[3])-1]));
				
				break;
		}
		
		if(ability.length() >= index+2)
		{
			ability2 = ability.substring(index+2);
			//Debug.message(ability2);
		
			String[] abilitytwo = ability2.split(",");
			String[] substring21 = abilitytwo[0].split("\\s+");
			switch(abilitytwo.length)
			{
				case 1:
//					Debug.message(substring21[3]);
//					Debug.message(abilityR[Integer.parseInt(substring21[3])-1]);
					//parseAbilities((substring21[1]+" "+substring21[2]+" "+ abilityR[Integer.parseInt(substring21[3])-1]));
					ap.getEnergy(substring21[1], substring21[2]);
					abilities.add(ap.parseAbilities(db.abilityR[Integer.parseInt(substring21[3])-1]));
					break;
				case 2:
					String[] substring22 = abilitytwo[1].split("\\s+");
					//parseAbilities((substring21[1]+" "+substring21[2]+" "+substring22[1]+" "+substring22[2]+" "+ abilityR[Integer.parseInt(substring22[3])-1]));
					ap.getEnergy(substring21[1], substring21[2]);
					ap.getEnergy(substring22[1],substring22[2]);
					abilities.add(ap.parseAbilities(db.abilityR[Integer.parseInt(substring22[3])-1]));
					break;
			}
		}
		
		if(card[3].equals("basic")){
			//Debug.message(cards[15] + cards[0]);
			this.getGroupCards().add(new Pokemon(x, card[0], new basicPokemon(), Integer.parseInt(card[6]), abilities));
		}
		else if(card[3].equals("stage-one")){
			//Debug.message(cards[0] + " evolves from " + cards[4]);
			this.getGroupCards().add(new Pokemon(x, card[0], new stageOnePokemon(card[4]), Integer.parseInt(card[7]), abilities));
		}
		else{
			Debug.message("Not Running " + card[3]);
		}
	}

	/* Method for testing purpose only */
	public void buildDeckTest(){
		ArrayList<ability> newAbility = new ArrayList<ability>();
		ArrayList<Energy> EnergyInfo = new ArrayList<Energy>();
		EnergyInfo.add(new Energy("Fighting"));
		newAbility.add(new damageAbility("Attack", 10, EnergyInfo,"opponentactive", "opponentactive energy"));
		int j=0;
		for(;j<18;j++){
				this.getGroupCards().add(new Pokemon(j, "Pikachu", new basicPokemon(), 80, newAbility));

//				this.getGroupCards().add(new Trainer(j+18, "Heal Trainer", "item", new healingAbility("Heal pokemon",30,"youractive")));
//				this.getGroupCards().add(new Trainer(j+18, "Deck Ability", "item", new DeckAbility("Deck Ability","opponent", "deck", 0, "opponenthand")));
//				this.getGroupCards().add(new Trainer(j+18, "Wally", "item", new Search("Deck Ability","choiceyour", "deck", null, "evolvesfrom",1)));
				this.getGroupCards().add(new Trainer(j+18,"Deenergize" ,"item", new Deenergize("Deenergize", "youractive", "youractive energy")));

				this.getGroupCards().add(new Energy("Fighting Energy",j+36));
		}
		for(;j<60;j++){
			this.getGroupCards().add(new Pokemon(j,"Raichu", new stageOnePokemon("Pikachu"), 30, newAbility));
		}
		//this.shufflecards();
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
