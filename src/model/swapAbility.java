package model;

import controller.GameController;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class swapAbility extends ability{
	private String name;
	private String activepokemon;
	private String benchpokemon;
	private AIplayer ai;
	private UserPlayer user;
	@FXML HBox userActivePokemon;
	@FXML HBox aiActivePokemon;
	
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
		GameController.getInstance().addCardToPanel(active, GameController.getInstance().getBench(player));
		GameController.getInstance().removeCard(String.valueOf(bench.getID()), GameController.getInstance().getBench(player));
		if (player.equals(user)){
			
			GameController.getInstance().addCardToPanel(bench, userActivePokemon);
		}else{
			GameController.getInstance().addCardToPanel(bench, aiActivePokemon);
		}
			
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
	

