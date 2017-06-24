package model;

import java.util.ArrayList;
import java.util.Random;

import controller.GameController;

public class AIplayer extends Player {
	
	private String name;
	private int score;
	private cardItem deck;
	
	public AIplayer(String newName){
		super(newName);
		this.name = newName;
		deck = new Deck(1);
		((Deck) deck).buildDeck();
	}
	
	public String getName() {
		return this.name;
	}

	public int getScore() {
		return this.score;
	}

	public void setTurn(boolean newTurn){
		this.turn = newTurn;
		if(this.turn==true){
			runAI();
		}
	}
	
	private void runAI(){
		GameController.getInstance().ulabelUpdate();
		boolean energyCardUsed = false;
		
		activePokemonMove();
		
		ArrayList<Pokemon> cards = ((CardsGroup) this.inhand).getAllBasicPokemonCard();
		if(cards.size()!=0){
			if(bench.getGroupCards().size()<5){
				Pokemon card2 = cards.remove(0);
				bench.addCard(card2);
				Debug.message("Card added to bench: "+ card2.getName());
				((CardsGroup) this.inhand).removeCard(card2);
				updateGUI();
			}
		}
		

		ArrayList<Energy> energyCards = ((CardsGroup) this.inhand).getAllEnergyCards();
		if(!energyCards.isEmpty() && !energyCardUsed && this.activePokemon!=null){
			Debug.message("AI Energy move");
			energyCardUsed = checkAndPlayEnergy(energyCards);
			GameController.getInstance().ulabelUpdate();
			updateGUI();
		}
		
		ArrayList<Trainer> trainerCard = ((CardsGroup) this.inhand).getAllTrainerCards();
		if(!trainerCard.isEmpty() && this.activePokemon!=null){
			if(trainerCard.get(0).getAbility().getClass().getSimpleName()=="healingAbility" && this.activePokemon.getDamage()>20){
				((CardsGroup) this.inhand).removeCard(trainerCard.get(0));
				Trainer tcard = trainerCard.remove(0);
				tcard.getAbility().useAbility();
				Debug.message("Trainer card used "+ tcard.getName() + " RunAI");
				userDiscardPile.addCard(tcard);
			}
			else{
				((CardsGroup) this.inhand).removeCard(trainerCard.get(0));
				Trainer tcard = trainerCard.remove(0);
				(tcard).getAbility().useAbility();
				Debug.message("Trainer card used "+ tcard.getName() + " RunAI");
				userDiscardPile.addCard(tcard);
			}
			updateGUI();
		}
		
		energyCards = ((CardsGroup) this.inhand).getAllEnergyCards();
		if(!energyCards.isEmpty() && !energyCardUsed){
			energyCardUsed = checkAndPlayEnergy(energyCards);
			updateGUI();
		}
		
		if(Turn.getInstance().getOpponent().getActivePokemon()!=null){
			ability attack = null;
			ability[] abilits = this.activePokemon.getAbilities();
			int i = abilits.length;
			while(attack==null && i>0){
				if(this.activePokemon.checkEnergyNeeds(abilits[i-1])){
					attack=abilits[i-1];
					attack.useAbility();
					Debug.message("AI attacking : RunAI");
					updateGUI();
				}
				i--;
			}
		}
		if(Turn.getInstance().getCurrentPlayer()==this)
		{
			Turn.getInstance().changeTurn();
		}
		GameController.getInstance().ulabelUpdate();
	}
	
	public boolean checkAndPlayEnergy(ArrayList<Energy> energyCards){
		Debug.message("No. of attached cards at active pokemon "+this.activePokemon.getAttachedCards().length);
		for(ability a : this.activePokemon.getAbilities()){
			if(!this.activePokemon.checkEnergyNeeds(a)){
				Debug.message("Checkpoint 1");
				this.activePokemon.attachCard(energyCards.get(0));
				((CardsGroup) this.inhand).removeCard(energyCards.get(0));
				Debug.message("Energy card added to Active pokemon");
				return true;
			}
		}
		for(cardItem card : bench.getGroupCards()){
			Pokemon pokemon = (Pokemon) card;
			for(ability a: pokemon.getAbilities()){
				if(!pokemon.checkEnergyNeeds(a)){
					Debug.message("Checkpoint 2");
					pokemon.attachCard(energyCards.get(0));
					((CardsGroup) this.inhand).removeCard(energyCards.get(0));
					Debug.message("Energy card added to "+pokemon.getName());
					return true;
				}
			}
		}
		//GameController.getInstance().ulabelUpdate();
		return false;
	}
	
	public void activePokemonMove(){
		if(this.activePokemon==null){
			if(!this.bench.getGroupCards().isEmpty()){
				this.activePokemon = (Pokemon) this.bench.getGroupCards().remove(0);
			}
			else{
				ArrayList<Pokemon> cards = ((CardsGroup) this.inhand).getAllBasicPokemonCard();
				if(cards.size()!=0){
				this.activePokemon = cards.remove(0);
				((CardsGroup) this.inhand).removeCard(this.activePokemon);
				Debug.message("Active pokemon set: "+this.activePokemon.getName());
				updateGUI();
				}
				else{
					//declare Mulligan
				}
			}
		}
		GameController.getInstance().ulabelUpdate();
	}
	
	public void updateGUI(){
		GameController.getInstance().refreshCards(this);
	}

	public Pokemon getActiveBenchChoice() {
		ArrayList<Pokemon> pokmons = new ArrayList<Pokemon>();
		if(this.activePokemon!=null){
			pokmons.add(this.activePokemon);
		}
		if(this.getBench().getAllPokemonCard()!=null && !this.getBench().getAllPokemonCard().isEmpty()){
			pokmons.addAll(this.getBench().getAllPokemonCard());
		}
		Random random = new Random();
		int number = random.nextInt(pokmons.size());
		
		return pokmons.get(number);
	}
	
}
