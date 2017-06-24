package model;

import java.util.ArrayList;
import java.util.Random;

import controller.GameController;

public class Player {
	
	protected String name;
	protected int score;
	protected cardItem deck;
	protected cardItem inhand,rwrd;
	protected Pokemon activePokemon;
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
		this.inhand = new CardsGroup();
		this.rwrd = new CardsGroup();
		this.bench = new CardsGroup();
		rewardCards = new CardsGroup();
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
		rewardCards.addCards(dealMultipleCards1(i));
		GameController.getInstance().ulabelUpdate();
	}
	
	public CardsGroup getRewardCards()
	{
		return rewardCards;
	}
	
	public cardItem dealCard(){
		cardItem newcard = getDeckCard();
		((CardsGroup) inhand).addCard(newcard);
		GameController.getInstance().ulabelUpdate();
		return newcard;
	}
	
	public cardItem[] dealMultipleCards(int i){
		cardItem[] dealt = getDeckCards(i);
		for(int x=0; x<i; x++){
			((CardsGroup) this.inhand).addCard(dealt[x]);
		}
		GameController.getInstance().ulabelUpdate();
		return dealt;
	}
	
	public cardItem[] dealMultipleCards1(int i){
		cardItem[] dealt = getDeckCards(i);
		Debug.message("Adding card to rewards "+dealt.length);
	/*	for(int x=0; x<i; x++){
			((CardsGroup) this.rwrd).addCard(dealt[x]);
		}*/
		GameController.getInstance().ulabelUpdate();
		return dealt;
	}
	
	public void dealrewardCards()
	{
		int size = getRewardCards().getGroupCards().size();
		 Random random = new Random();
         int number = random.nextInt(size);
         GameController.getInstance().addCardToPanel(getRewardCards().getGroupCards().get(number),GameController.getInstance().getHand(this));
         getRewardCards().removeCard(rewardCards.getGroupCards().get(number));
	}
	
	public cardItem[] getInhandCards(){
		//GameController.getInstance().ulabelUpdate();
		return ((CardsGroup) this.inhand).getCard();
		
	}
	
	public cardItem getInhand(){
		GameController.getInstance().ulabelUpdate();
		return this.inhand;
	}
	
	public void setActivePokemon(Pokemon newPokemon){
		this.activePokemon = newPokemon;
	}
	
	public Pokemon getActivePokemon(){
		return this.activePokemon;
	}
	
	public Deck getDeck(){
		return (Deck) this.deck;
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
		//GameController.getInstance().ulabelUpdate();
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
		GameController.getInstance().ulabelUpdate();
	}
	
//	public static void main(String arg[]){
//		Player newPlayer = new Player("Flash");
//		for(Pokemon pCard : newPlayer.getDeck().getAllPokemonCard("basic")){
//			Debug.message("Card name : " + pCard.getName());
//			for(ability a : pCard.getAbilities()){
//				Debug.message("Ability Name:"+a.getName()+" energy required: "+a.getEnergyInfo().size());
//			}
//		}
//	}

	public CardsGroup getBench(){
		return this.bench;		
	}	
	public CardsGroup getDiscardPile(){
		return this.userDiscardPile;
	}

	public String getName() {
		return this.name;
	}
	
	public ArrayList<Pokemon> getPokemonFromBenchAndActive(){
		ArrayList<Pokemon> allPokemons = new ArrayList<Pokemon>();
		allPokemons.addAll(this.bench.getAllPokemonCard());
		allPokemons.add(this.activePokemon);
		return allPokemons;
	}
}
