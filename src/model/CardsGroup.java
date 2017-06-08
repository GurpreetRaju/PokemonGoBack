package model;

import java.util.ArrayList;

public class CardsGroup implements cardItem {
	
	private ArrayList<cardItem> groupCards = new ArrayList<cardItem>();
	
	public void addCard(cardItem newCard){
		this.getGroupCards().add(newCard);
	}
	
	public void addCards(cardItem[] newCards){
		for(cardItem card: newCards){
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
		return this.getGroupCards().remove(0);
	}
	
	public cardItem[] getCard(){
		return this.getGroupCards().toArray(new cardItem[this.getGroupCards().size()]);
	}
	
	public void removeCard(cardItem newCard){
		this.getGroupCards().remove(newCard);
	}
	
	public Pokemon getBasicPokemonCard(){
		for(cardItem card : groupCards){
			if(card instanceof Pokemon && ((Pokemon) card).getStage()=="Basic"){
				groupCards.remove(card);
				return (Pokemon) card;
			}
		}
		return null;
	}
	public ArrayList<Pokemon> getAllBasicPokemonCard(){
		return getAllPokemonCard("Basic");
	}
	
	public ArrayList<Pokemon> getStageOneCards(){
		return getAllPokemonCard("StageOne");
	}
	
	public ArrayList<Pokemon> getAllPokemonCard(String type){
		ArrayList<Pokemon> pokemonCards = new ArrayList<Pokemon>();
		for(cardItem card : groupCards){
			if(card instanceof Pokemon && ((Pokemon) card).getStage()==type){
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
	
	public ArrayList<Trainer> getAllTrainerCards(){
		ArrayList<Trainer> trainerCards = new ArrayList<Trainer>();
		for(cardItem card : groupCards){
			if(card instanceof Trainer || card instanceof stadium_Trainer || card instanceof supporter_Trainer || card instanceof Item_Trainer){
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

}
