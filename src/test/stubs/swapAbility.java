package test.stubs;

import model.Player;
import model.cardItem;
import model.target;



import controller.GameController;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

import test.stubs.*;



public class swapAbility extends model.ability{



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
		

	//	Player player = (Player) getPlayer(benchpokemon);
		//CardsGroup benchcards = player.getBench();
		cardItem active = (model.Pokemon) target.getTargetObject(activepokemon).getTarget();
		model.Pokemon bench = (model.Pokemon) target.getTargetObject(benchpokemon).getTarget(); 
	//	model.CardsGroup benchcards = player.getBench();
		//PokemonCard activeCard = new PokemonCard(active, userActivePokemon);
//		for(cardItem card: benchcards.getCard()){
//			//Do code here
//			//if(){
//				player.setActivePokemon((Pokemon)card);
//			//}
//		}

		/*Player player = (Player) getPlayer(benchpokemon);
		cardItem active = (Pokemon) target.getTargetObject(activepokemon).getTarget();
		Pokemon bench = (Pokemon) target.getTargetObject(benchpokemon).getTarget(); 
		CardsGroup benchcards = player.getBench();
		

		benchcards.addCard(active);
		benchcards.removeCard(bench);
		player.setActivePokemon(bench);
		GameController.getInstance().addCardToPanel(active, GameController.getInstance().getBench(player));
		GameController.getInstance().removeCard(String.valueOf(bench.getID()), GameController.getInstance().getBench(player));
		GameController.getInstance().addCardToPanel(bench,GameController.getInstance().getactivepokemon(player));

	}
		
		
	public Player getPlayer(String playertarget){

	*/}
		
		
	/*public Player getPlayer(String playertarget){

		if(playertarget.equals("yourbench")){
			return Turn.getInstance().getCurrentPlayer();
		}
		else{
			return Turn.getInstance().getOpponent();
		}

	}

	}*/

	}
	

