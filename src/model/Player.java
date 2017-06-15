package model;

import java.util.ArrayList;

public class Player {
	
	protected String name;
	protected int score;
	protected cardItem deck;
	protected cardItem inhand;
	protected Pokemon activePokemon;
	protected ArrayList<ability> activeAbilities;
	protected boolean turn;
	protected CardsGroup bench;
	protected CardsGroup userDiscardPile;
	protected CardsGroup rewardCards;
	
//	public abstract String getName();
//	public abstract int getScore();
	
	public Player(String name){
		this.name = name;
		this.deck = new Deck(1);
		((Deck) this.deck).buildDeck();
		this.activeAbilities = new ArrayList<ability>();
		this.inhand = new CardsGroup();
		this.bench = new CardsGroup();
		this.userDiscardPile = new CardsGroup();
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
	
	public void addRewardCards(int i){
		rewardCards.addCards(dealMultipleCards(i));
	}
	
	public cardItem dealCard(){
		cardItem newcard = getDeckCard();
		((CardsGroup) inhand).addCard(newcard);
		return newcard;
	}
	
	public cardItem[] dealMultipleCards(int i){
		cardItem[] dealt = getDeckCards(i);
		for(int x=0; x<i; x++){
			((CardsGroup) this.inhand).addCard(dealt[x]);
		}
		return dealt;
	}
	
	public cardItem[] getInhandCards(){
		return ((CardsGroup) this.inhand).getCard();
	}
	
	public cardItem getInhand(){
		return this.inhand;
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
	
	public Deck getDeck(){
		return (Deck) this.deck;
	}
	
	public void removeAbility(ability newAbility){
		this.activeAbilities.remove(newAbility);
	}
	
	public void addActiveAbility(ability newAbility){
		this.activeAbilities.add(newAbility);
	}
		
	public boolean getTurn(){
		return this.turn;
	}
	
	public cardItem getPokemonFromHand(){
		for(cardItem card: ((CardsGroup) this.inhand).getCard()){
			if(card instanceof Pokemon){
				return card;
			}
		}
		return null;
	}
	
	public void evolve(Pokemon stageOnePokemon, Pokemon basicPokemon){
		stageOnePokemon.evolve(basicPokemon);
		((CardsGroup) this.getInhand()).removeCard(stageOnePokemon);
		if(this.getActivePokemon() == basicPokemon){
			this.setActivePokemon(stageOnePokemon);
		}
		else{
			this.getBench().addCard(stageOnePokemon);
		}
	}
	
	public static void main(String arg[]){
		Player newPlayer = new Player("Flash");
		Debug.showCard(newPlayer.dealCard());
	}

	public CardsGroup getBench(){
		return this.bench;		
	}	
	public CardsGroup getDiscardPile(){
		return this.userDiscardPile;
	}
}
