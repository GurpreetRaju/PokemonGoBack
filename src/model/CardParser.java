package model;

import java.util.ArrayList;

public class CardParser {
	
	private Deck deck;
	
	public CardParser(Deck newdeck){
		this.deck = newdeck;
	}
	
	public cardItem createPokemon(int newId, String[] newCard){
			
		AbilityParser ap = new AbilityParser();
		ArrayList<ability> abilities = new ArrayList<ability>();
		String carditem = String.join(" ", newCard);
		//String retreat = carditem.substring(carditem.indexOf("retreat cat"), carditem.indexOf("attack"));
		String ability = carditem.substring(carditem.indexOf("attack")+8);
		
		
		String ability1, ability2;
		int index = ap.indexOf("\\d\\s+\\d+", ability);
		//Debug.message(index);
		ability1 = ability.substring(0, index);
		//Debug.message(ability1);
		
		int abilityLineInfo = Integer.parseInt(ability1.substring(ability1.lastIndexOf(" ")+1));
		//Debug.message(abilityLineInfo);
		
		String[] energyCategoryInfo = ability1.split(",");
		String[] substring11 = energyCategoryInfo[0].split("\\s+");
		
		switch(energyCategoryInfo.length)
		{
		//create objects of separate abilities and pass to a new class composite ability
			case 1:
				//parseAbilities((substring11[1]+" "+substring11[2]+" "+ abilityR[Integer.parseInt(substring11[3])-1]));
				String[] energytype = {substring11[1]};
				int[] energynumber = {Integer.parseInt(substring11[2])};
				abilities.add(ap.parseAbilities(deck.getAbilityString(abilityLineInfo-1), this.getEnergy(energytype, energynumber)));
				//Debug.message(db.abilityR[Integer.parseInt(substring11[3])-1]);
				break;
			case 2:
				String[] substring12 = energyCategoryInfo[1].split("\\s+");
				//parseAbilities((substring11[1]+" "+substring11[2]+" "+substring12[1]+" "+substring12[2]+" "+abilityR[Integer.parseInt(substring12[3])-1]));

				String[] energytype2 = {substring11[1], substring12[1]};
				int[] energynumber2 = { Integer.parseInt(substring11[2]), Integer.parseInt(substring12[2])};
				abilities.add(ap.parseAbilities(deck.getAbilityString(abilityLineInfo-1), this.getEnergy(energytype2, energynumber2)));
				break;
		}
		
		if(ability.length() >= index+2)
		{
			ability2 = ability.substring(index+2);
			//Debug.message(ability2);
			abilityLineInfo = Integer.parseInt(ability2.substring(ability2.lastIndexOf(" ")+1));
			String[] abilitytwo = ability2.split(",");
			String[] substring21 = abilitytwo[0].split("\\s+");
			switch(abilitytwo.length)
			{
				case 1:
//					Debug.message(substring21[3]);
//					Debug.message(abilityR[Integer.parseInt(substring21[3])-1]);
					//parseAbilities((substring21[1]+" "+substring21[2]+" "+ abilityR[Integer.parseInt(substring21[3])-1]));
					String[] energytype = {substring21[1]};
					int[] energynumber = {Integer.parseInt(substring21[2])};
					abilities.add(ap.parseAbilities(deck.getAbilityString(abilityLineInfo-1), this.getEnergy(energytype, energynumber)));
					break;
				case 2:
					String[] substring22 = abilitytwo[1].split("\\s+");
					//parseAbilities((substring21[1]+" "+substring21[2]+" "+substring22[1]+" "+substring22[2]+" "+ abilityR[Integer.parseInt(substring22[3])-1]));
					String[] energytype2 = {substring21[1], substring22[1]};
					int[] energynumber2 = { Integer.parseInt(substring21[2]), Integer.parseInt(substring22[2])};
					abilities.add(ap.parseAbilities(deck.getAbilityString(abilityLineInfo-1), this.getEnergy(energytype2, energynumber2)));
						
					break;
			}
		}
		
		if(newCard[3].equals("basic")){
			//Debug.message(cards[15] + cards[0]);
			return new Pokemon(newId, newCard[0], new basicPokemon(), Integer.parseInt(newCard[6]), abilities);
		}
		else if(newCard[3].equals("stage-one")){
			//Debug.message(cards[0] + " evolves from " + cards[4]);
			return new Pokemon(newId, newCard[0], new stageOnePokemon(newCard[4]), Integer.parseInt(newCard[7]), abilities);
		}
		else{
			Debug.message("Not Running " + newCard[3]);
		}
		
		return null;
	}
	
	public cardItem createTrainer(int newId, String[] newCard){
		
		AbilityParser ap = new AbilityParser();
		ability a = ap.parseAbilities(deck.getAbilityString(Integer.parseInt(newCard[4])-1), null);
		if(a==null){
			a = new Search("Search pokemon", "you", "deck","pokemon","basic",2);
		}
		//Debug.message(abilities.get(0).toString());
		return new Trainer(newId, newCard[0], newCard[3], a);
		
	}
	
	public ArrayList<Energy> getEnergy(String[] energytype, int[] energynumber){
		ArrayList<Energy> EnergyInfo = new ArrayList<Energy>();
		for(int i=0; i<energytype.length; i++){
			for(int e =0; e < energynumber[i];e++){
				//Debug.message(energytype);
				EnergyInfo.add(new Energy(energytype[i]));
			}
		}
		return EnergyInfo;
	}

	public static void main(String[] aarg){
		Deck deck = new Deck(1);
		deck.readFile();
		String[] card = ("Pikachu Libre:pokemon:cat:basic:cat:lightning:80:retreat:cat:colorless:1:attacks:cat:colorless:2:3,cat:colorless:2,cat:lightning:1:4").split(":");

		CardParser cpars = new CardParser(deck);
		Pokemon p = (Pokemon) cpars.createPokemon(1, card);
		
		for(ability a: p.getAbilities()){
			Debug.message("Type "+a.getClass().getSimpleName()+ " Name " + a.getName()+" Energy required "+ a.getEnergyInfo().size());
		}
	}
}
