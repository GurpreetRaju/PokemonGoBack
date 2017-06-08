package model;

import java.util.ArrayList;

import controller.GameController;

public class AIplayer extends Player {
	
	private String name;
	private int score;
	private cardItem deck;
	private GameController controller;
	
	public AIplayer(String newName,GameController newController){
		super(newName);
		this.name = newName;
		deck = new Deck();
		((Deck) deck).buildDeck(1);
		this.controller = newController;
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
		boolean energyCardUsed = false;
		ArrayList<Pokemon> cards = ((CardsGroup) this.inhand).getAllBasicPokemonCard();
		if(this.activePokemon==null){
			boolean noPokemon = true;
			if(cards.size()!=0){
				this.activePokemon = cards.remove(0);
				((CardsGroup) this.inhand).removeCard(this.activePokemon);
				noPokemon = false;
				Debug.message("Active pokemon set: "+this.activePokemon.getName());
				updateGUI();
			}
			if(noPokemon){
				//declare Mulligan
			}
		}
		if(cards.size()!=0){
			if(bench.size()<5){
				Pokemon card2 = cards.remove(0);
				bench.add(card2);
				Debug.message("Card added to bench: "+ card2.getName());
				((CardsGroup) this.inhand).removeCard(card2);
				updateGUI();
			}
		}
		

		ArrayList<Energy> energyCards = ((CardsGroup) this.inhand).getAllEnergyCards();
		if(!energyCards.isEmpty() && !energyCardUsed && this.activePokemon!=null){
			energyCardUsed = checkAndPlayEnergy(energyCards);
			updateGUI();
		}
		
		ArrayList<Trainer> trainerCard = ((CardsGroup) this.inhand).getAllTrainerCards();
		if(!trainerCard.isEmpty() && this.activePokemon!=null){
			if(trainerCard.get(0).getAbility().getClass().getSimpleName()=="healingAbility" && this.activePokemon.getDamage()>20){
				((CardsGroup) this.inhand).removeCard(trainerCard.get(0));
				trainerCard.get(0).getAbility().useAbility();
				Debug.message("Trainer card used "+ trainerCard.get(0).getName());
				updateGUI();
			}
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
				if(((damageAbility) abilits[i-1]).getEnergyInfo().length <= this.activePokemon.getAttachedCardsCount()){
					attack=abilits[i-1];
					attack.useAbility();
					Debug.message("attacking");
					updateGUI();
				}
				i--;
			}
		}
		if(Turn.getInstance().getCurrentPlayer()==this)
		{
			Turn.getInstance().changeTurn();
		}
	}
	
	private boolean checkAndPlayEnergy(ArrayList<Energy> energyCards){
		Debug.message(this.activePokemon.getAttachedCards().length);
		if(this.activePokemon.getAttachedCards().length<this.activePokemon.totalEnergyRequired()){
			this.activePokemon.attachCard(energyCards.get(0));
			((CardsGroup) this.inhand).removeCard(energyCards.get(0));
			Debug.message("Energy card added to Active pokemon");
			return true;
		}
		else{
			for(Pokemon pokemon : bench){
				if(pokemon.getAttachedCards().length<pokemon.totalEnergyRequired()){
					pokemon.attachCard(energyCards.get(0));
					((CardsGroup) this.inhand).removeCard(energyCards.get(0));
					Debug.message("Energy card added to "+pokemon.getName());
					return true;
				}
			}
		}
		return false;
	}
	
	public void updateGUI(){
		controller.refreshCards(this);
	}
	
}
