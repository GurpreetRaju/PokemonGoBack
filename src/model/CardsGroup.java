package model;

import java.util.ArrayList;

import controller.GameController;

public class CardsGroup implements cardItem
{
	int uDeck,aiDeck;
	private ArrayList<cardItem> groupCards = new ArrayList<cardItem>();
	
	public void addCard(cardItem newCard)
	{
		this.getGroupCards().add(newCard);
		
	}
	
	public void addCards(cardItem[] newCards){
		for(cardItem card: newCards)
		{
			this.addCard(card);
			
		}
	}
	
	public cardItem getCard(int id){
		for(cardItem card:groupCards){
			if(card.getID() == id){
				return card;
			}
		}
		return null;
	}
	
	public cardItem removeFirstCard(){
		GameController.getInstance().ulabelUpdate();
		return this.getGroupCards().remove(0);
	}
	
	public cardItem[] getCard(){
		return this.getGroupCards().toArray(new cardItem[this.getGroupCards().size()]);
	}
	
	public void removeCard(cardItem newCard){
	 GameController.getInstance().ulabelUpdate();
		this.getGroupCards().remove(newCard);
		
	}
	
	public Pokemon getBasicPokemonCard(){
		for(cardItem card : groupCards){
			if(card instanceof Pokemon && ((Pokemon) card).getStage()=="basic"){
				groupCards.remove(card);
				GameController.getInstance().ulabelUpdate();
				return (Pokemon) card;
			}
		}
		return null;
	}
	public ArrayList<Pokemon> getAllBasicPokemonCard(){
		return getAllPokemonCard("basic");
	}
	
	public ArrayList<Pokemon> getStageOneCards(){
		return getAllPokemonCard("stage-one");
	}
	
	public ArrayList<Pokemon> getAllPokemonCard(String type){
		ArrayList<Pokemon> pokemonCards = new ArrayList<Pokemon>();
		for(cardItem card : groupCards){
			if(card instanceof Pokemon && ((Pokemon) card).getStage()==type){
				pokemonCards.add((Pokemon) card);
			}
		}
		//GameController.getInstance().ulabelUpdate();
		return pokemonCards;
	}
	
	public ArrayList<Pokemon> getAllPokemonCard(){
		ArrayList<Pokemon> pokemonCards = new ArrayList<Pokemon>();
		for(cardItem card : groupCards){
			if(card instanceof Pokemon){
				pokemonCards.add((Pokemon) card);
			}
		}
		return pokemonCards;
	}
	
	public ArrayList<Energy> getAllEnergyCards(){
		ArrayList<Energy> energyCards = new ArrayList<Energy>();
		for(cardItem card : groupCards){
			if(card instanceof Energy){
				energyCards.add((Energy) card);
			}
		}
		return energyCards;
	}
	
	public ArrayList<cardItem> getAllEnergyCards(String newName){
		ArrayList<cardItem> energyCards = new ArrayList<cardItem>();
		for(cardItem card : groupCards){
			if(card instanceof Energy && card.getName().equals(newName)){
				energyCards.add(card);
			}
		}
		return energyCards;
	}
	
	public ArrayList<Trainer> getAllTrainerCards(){
		ArrayList<Trainer> trainerCards = new ArrayList<Trainer>();
		for(cardItem card : groupCards){
			if(card instanceof Trainer){
				trainerCards.add((Trainer) card);
			}
		}
		return trainerCards;
	}
	
	public ArrayList<cardItem> getAllTrainerCards(String cat){
		ArrayList<cardItem> trainerCards = new ArrayList<cardItem>();
		for(cardItem card : groupCards){
			if(card instanceof Trainer && ((Trainer) card).getCategory().equals(cat)){
				trainerCards.add((Trainer) card);
			}
		}
		return trainerCards;
	}
		

	@Override
	public String getName() {
		return null;
	}
	
	public int getID(){
		return 99;
	}

	public ArrayList<cardItem> getGroupCards() {
				return this.groupCards;
	}

	public void setGroupCards(ArrayList<cardItem> groupCards) {
		this.groupCards = groupCards;
	}

	public ArrayList<cardItem> getCardsOfType(Class<?> newClassType) 
	{
		return null;
	}

}
