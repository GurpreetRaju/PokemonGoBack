package model;

import java.util.ArrayList;

public class UserPlayer implements Player {
	
	private String name;
	private int score;
	private cardItem deck;
	private cardItem inhand;
	private Pokemon activePokemon;
	private ArrayList<ability> activeAbilities;
	
	public UserPlayer(String newName){
		this.name = newName;
		deck = new Deck();
		((Deck) deck).buildDeck(2);
		activeAbilities = new ArrayList<ability>();
	}
	
	public cardItem getDeckCard(){
		return ((Deck) this.deck).removeFirstCard();
	}
	
	public cardItem[] getDeckCards(int i){
		ArrayList<cardItem> cards = new ArrayList<cardItem>();
		for(int x=0; x<i; x++){
			cards.add(getDeckCard());
		}
		return cards.toArray(new cardItem[cards.size()]);
	}
	
	public cardItem dealCard(){
		cardItem newcard = getDeckCard();
		((CardsGroup) inhand).addCard(newcard);
		return newcard;
	}
	
	public cardItem[] dealMultipleCards(int i){
		cardItem[] dealt = getDeckCards(i);
		for(int x=0; x<i; x++){
			((CardsGroup) inhand).addCard(dealt[x]);
		}
		return dealt;
	}
	
	public void setActivePokemon(Pokemon newPokemon){
		this.activePokemon = newPokemon;
	}
	
	public Pokemon getActivePokemon(){
		return this.activePokemon;
	}
	
	public ability[] getActiveAbilities(){
		return this.activeAbilities.toArray(new ability[this.activeAbilities.size()]);
	}
	
	public String getAbilityIndex(ability newAbility){
		for(int i=0;i<this.activeAbilities.size();i++){
			if(this.activeAbilities.get(i) == newAbility){
				return Integer.toString(i);
			}
		}
		return null;
	}
	
	public void removeAbility(ability newAbility){
		this.activeAbilities.remove(newAbility);
	}
	
	public void addActiveAbility(ability newAbility){
		this.activeAbilities.add(newAbility);
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getScore() {
		return this.score;
	}
	
}
