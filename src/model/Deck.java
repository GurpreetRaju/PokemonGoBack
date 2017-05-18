package model;

import java.util.ArrayList;

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
			int n = Integer.parseInt(cards[0]);
			for(int i=1;i<=n;i++){
				switch(cards[2]){
					case "P":
						this.getGroupCards().add(new Pokemon(x, cards[1], stage, 80, newAbility));
					case "T":
						this.getGroupCards().add(new Item_Trainer(cards[1]));
					case "E":
						this.getGroupCards().add(new Energy(cards[1]));
				}
				x++;
			}
		}
	}
	
	@Override
	public String getName() {
		return this.name;
	}

}
