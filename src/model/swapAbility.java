package model;

import controller.GameController;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import view.PokemonCard;

public class swapAbility extends ability{
	private String activepokemon, benchpokemon;
	private UserPlayer user;
	
	public swapAbility(String name,String activepokemon, String benchpokemon){
		this.name = name;
		this.activepokemon = activepokemon;
		this.benchpokemon = benchpokemon;
		
	}
	
	
	public boolean equals(Object o) {
		return false;
	}
	
	public void useAbility() {
		
		Player player = (Player) getPlayer(benchpokemon);
		//CardsGroup benchcards = player.getBench();
		cardItem active = (Pokemon) target.getTargetObject(activepokemon).getTarget();
		Pokemon bench = (Pokemon) target.getTargetObject(benchpokemon).getTarget(); 
		CardsGroup benchcards = player.getBench();
		//PokemonCard activeCard = new PokemonCard(active, userActivePokemon);
//		for(cardItem card: benchcards.getCard()){
//			//Do code here
//			//if(){
//				player.setActivePokemon((Pokemon)card);
//			//}
//		}
		benchcards.addCard(active);
		benchcards.removeCard(bench);
		player.setActivePokemon(bench);
//		GameController.getInstance().addCardToPanel(active, GameController.getInstance().getBench(player));
//		GameController.getInstance().removeCard(String.valueOf(bench.getID()), GameController.getInstance().getBench(player));
//		GameController.getInstance().removeCard(String.valueOf(active.getID()), GameController.getInstance().getactivepokemon(player));
//		GameController.getInstance().addCardToPanel(bench,GameController.getInstance().getactivepokemon(player));
		PokemonCard activeCard = GameController.getInstance().getCardById(active.getID(), GameController.getInstance().getactivepokemon(player));
		activeCard.setLocation(GameController.getInstance().getBench(player));
		
		PokemonCard benchCard = GameController.getInstance().getCardById(bench.getID(), GameController.getInstance().getBench(player));
		benchCard.setLocation(GameController.getInstance().getactivepokemon(player));
		
		GameController.getInstance().ulabelUpdate();
	}
		
		
	public Player getPlayer(String playertarget){
		if(playertarget.equals("yourbench")){
			return Turn.getInstance().getCurrentPlayer();
		}
		else{
			return Turn.getInstance().getOpponent();
		}
	}
	}
	

