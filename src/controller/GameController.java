package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.AIplayer;
import model.CardsGroup;
import model.Debug;
import model.Energy;
import model.Player;
import model.Pokemon;
import model.Turn;
import model.UserPlayer;
import model.ability;
import model.cardItem;
import model.damageAbility;
import view.DialogBoxHandler;
import view.GeneralCard;
import view.PokemonCard;

public class GameController {
	
	private UserPlayer user;
	private AIplayer ai;
	

	@FXML private ScrollPane userHandScroll;
	@FXML private HBox userBench;
	@FXML private HBox userHand;
	@FXML private ScrollPane AIHandScroll;
	@FXML private HBox AIBench;
	@FXML private HBox AIHand;
	@FXML private HBox aiActivePokemon;
	@FXML private HBox userActivePokemon;
	@FXML private Button UserEndTurnBtn = new Button();
	@FXML private Label userDamage = new Label();
	@FXML private Label aiDamage = new Label();
	
	public GameController(){}
	
 	public void init(){
		
 		boolean[] turn = Turn.getInstance().toss();
 		
 		UserEndTurnBtn = new Button();
		userDamage = new Label();
		aiDamage = new Label();
		user = new UserPlayer("Flash");
		ai = new AIplayer("Future Flash",this);
		UserEndTurnBtn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        Turn.getInstance().changeTurn();
		    }
		});
    	addCardsToPanel(user.dealMultipleCards(7),userHand);
    	addCardsToPanel(ai.dealMultipleCards(7), AIHand);
		Turn.getInstance().setPlayer(ai,user,this);
		
		Debug.message("User turn : "+turn[0]);
		Debug.message("AI Turn: "+turn[1]);
		((UserPlayer) user).setTurn(turn[0]);
		((AIplayer) ai).setTurn(turn[1]);
	}    
    public void addCardsToPanel(cardItem[] cards, HBox panel){
    	FlowPane newCard = null;
    	for(cardItem card : cards){
    		if(card instanceof Pokemon){
    			newCard = createPokemonCard((Pokemon) card, panel);
    		}
    		else {
    			newCard = createCard(card, panel);
    		}
    		panel.getChildren().add(newCard);
    	}
    }
    
    public void removeCard(String id, HBox panel){
    	for(Node node: panel.getChildren()){
    		if(node.getStyleClass().contains(id)){
    			panel.getChildren().remove(node);
    		}
    	}
    }
    
    public void addCardToPanel(cardItem card, HBox panel){
    	FlowPane newCard = null;
    	if(card instanceof Pokemon){
    		newCard = createPokemonCard((Pokemon) card, panel);
    	}
    	else {
    		newCard = createCard(card, panel);
    	}
    	panel.getChildren().add(newCard);
    }
      
    private FlowPane createPokemonCard(Pokemon pokemon, HBox panel){
    	PokemonCard pokemonCard = new PokemonCard(pokemon, panel);
    	//pokemonCard.setStyle("-fx-background-color: #fff;"+"-fx-border-color: #000;"+"-fx-border-width: 1px;"+
    	//		"-fx-pref-width: 52px;"+ "-fx-pref-height: 70px");
    	pokemonCard.addOptionsActionListener(new EventHandler<ActionEvent>() {
    		@Override 
    		public void handle(ActionEvent e) {
    			pokemonOptions(pokemonCard);
    		}
    	});
    	
    	return pokemonCard;
    }
    
    private void pokemonOptions(PokemonCard pokemonCard)
    {
    	ArrayList<String> optionsList = new ArrayList<String>();
    	HBox tempLoc = pokemonCard.getLocation();
    	
    	if(tempLoc==userHand){
    		if(pokemonCard.getCard().getStage()=="Basic"){
    			if(userActivePokemon.getChildren().isEmpty()){
    				optionsList.add("Make active");
    			}
    			else if(userBench.getChildren().size() < 5){
    				optionsList.add("Put on bench");
    			}
    		}
    		else{//if pokemon card is stageone pokemon
    			optionsList.add("Evolve");
    		}
    	}
    	else if(tempLoc==userBench){
    		if(userActivePokemon.getChildren().isEmpty()){
    			optionsList.add("Make active");
    		}
    	}
    	else if(tempLoc==userActivePokemon){
    		optionsList.add("Retreat");
			optionsList.add("View card abilities");
    	}
    	if(optionsList.isEmpty()){
    		
    	}
    	else{
    		DialogBoxHandler dialog = new DialogBoxHandler();
        	Optional<String> result = dialog.getDialog(optionsList);
    		String selected = "cancelled.";
        		
        	if (result.isPresent()) {
        		selected = result.get();
        		switch(selected){
        			case "Make active":
        				pokemonCard.setLayoutX(0);
        		   		pokemonCard.setLayoutY(0);
        		   		pokemonCard.setLocation(userActivePokemon);
        		   		user.setActivePokemon(pokemonCard.getCard());
        		   		((CardsGroup) user.getInhand()).removeCard(user.getActivePokemon());
        				break;
        			case "Put on bench":
        				pokemonCard.setLayoutX(0);
        		   		pokemonCard.setLayoutY(0);
        		   		pokemonCard.setLocation(userBench);
        		   		user.addCardonBench(pokemonCard.getCard());
        		   		((CardsGroup) user.getInhand()).removeCard(pokemonCard.getCard());
        				break;
        			case "Retreat":
            			ArrayList<String> benchCards = new ArrayList<String>();
    			    	for(Node card : userBench.getChildren()){
    						PokemonCard tempCard = (PokemonCard) card;
    						int id = tempCard.getCard().getID();
    						Debug.message(id);
    						benchCards.add(String.valueOf(id));
    			    	}
    			    	List<String> benchData = Arrays.asList(benchCards.toArray(new String[benchCards.size()]));
    			    	
            			@SuppressWarnings({ "rawtypes", "unchecked" })
    					Dialog benchDialog = new ChoiceDialog(benchData.get(0), benchData);
    					benchDialog.setTitle("Select a bench pokemon to be active");
    					benchDialog.setHeaderText("Select your choice");

    					@SuppressWarnings("unchecked") 
    					Optional<String> benchOp = benchDialog.showAndWait();
    					String select = "cancelled.";
    					if(benchOp.isPresent())
    					{
    						select = benchOp.get();
    						Debug.message(select);
    						PokemonCard benchC = null;
    						for(Node tempNode: userBench.getChildren())
    						{
    							PokemonCard tempCard = (PokemonCard) tempNode;
    							if(tempCard.getCard().getID() == Integer.parseInt(select))
    							{
    								benchC = tempCard;
    								System.out.println(benchC.getCard().getName());
    							}
    						}
//    						user.setActivePokemon(null);
    						user.setActivePokemon(benchC.getCard());
    			    		user.addCardonBench(pokemonCard.getCard());
    			    		
    			    		//userActivePokemon.getChildren().add(benchC);
    			    		benchC.setLocation(userActivePokemon);
    			    		//userBench.getChildren().add(pokemonCard);
    			    		pokemonCard.setLocation(userBench);
    					}
            			break;
        			case "Evolve":
        				PokemonCard card = evolveoptions(pokemonCard);
        				if(card!=null){
        					card.evolve(pokemonCard.getCard());
        					userHand.getChildren().remove(pokemonCard);
        					((CardsGroup) user.getInhand()).removeCard(pokemonCard.getCard());
        					user.addCardonBench(pokemonCard.getCard());
        				}
        				else{
        					Debug.message("No pokemon found");
        				}
        				break;
        			case "View card abilities": 
        				Debug.message("Showing card abilities");
            			Dialog<String> abilitiesDialog = new Dialog<>();
            			abilitiesDialog.setTitle("Abilities");
            			abilitiesDialog.setHeaderText("Select any ability to use");
            			ButtonType attackButton = new ButtonType("Attack", ButtonData.OK_DONE);
            			abilitiesDialog.getDialogPane().getButtonTypes().addAll(attackButton, ButtonType.CANCEL);
            			GridPane grid = new GridPane();
            			grid.setHgap(10);
            			grid.setVgap(10);
            			grid.setPadding(new Insets(20, 150, 10, 10));
        		   			    	
            			final ToggleGroup group = new ToggleGroup();
        		   	
            			for(ability a : user.getActivePokemon().getAbilities()){
            				FlowPane temppane = new FlowPane();
            				RadioButton rb = new RadioButton(a.getName());
            				if(!(user.getActivePokemon().getAttachedCardsCount()>=((damageAbility) a).getEnergyInfo().length)){
            					rb.setDisable(true);
            				}
            				rb.setUserData(a.getName());
            				rb.setToggleGroup(group);
            				temppane.getChildren().add(rb);
            				temppane.getChildren().add(new Label(Integer.toString(((damageAbility) a).getDamage())));
            				grid.add(temppane, 0, 0);
            			}
            			abilitiesDialog.getDialogPane().setContent(grid);
            			abilitiesDialog.getResult();
            			abilitiesDialog.setResultConverter(dialogButton -> {
            				if (dialogButton == attackButton) {
            					return group.getSelectedToggle().getUserData().toString();
            				}
            				return null;
            			});
        		   	
        		    	
           				Node aButton = abilitiesDialog.getDialogPane().lookupButton(attackButton);
           				aButton.setDisable(true);
        	    	
           				group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
           					public void changed(ObservableValue<? extends Toggle> ov,
        	    	            Toggle old_toggle, Toggle new_toggle) {
        	    	                if (group.getSelectedToggle() != null) {
        	    	                	aButton.setDisable(false);
        	    	                }                
        	    	            }
        	    	    });
        	    	
            			Optional<String> result2 = abilitiesDialog.showAndWait();
            			if(result2.isPresent()){
            				for(ability b: user.getActivePokemon().getAbilities()){
            					if(b.getName()==result2.get()){
            						b.useAbility();
            						aiDamage.setText(Integer.toString(ai.getActivePokemon().getDamage()));
            					}
        		    		}
            			}
        		   		break;
        		}
        	}
    	}
	}

	private FlowPane createCard(cardItem card, HBox panel){
    	GeneralCard newCard = new GeneralCard(card);
    	
    	if(panel == userHand || panel == userBench)
    	{
    		newCard.addOptionsActionListener(new EventHandler<ActionEvent>() {
    			@Override 
    			public void handle(ActionEvent e) {
    				if(card instanceof Energy){
    					EnergyOptions(newCard);
    				}
    				//trainerOptions(button, optionsList);
    			}
    	
    		});
    	}   	
    	return newCard;
    }
    
    @SuppressWarnings("unchecked")
	private void EnergyOptions(GeneralCard newcard) {
		ArrayList<String> optionsList = new ArrayList<String>();
    	if(! userActivePokemon.getChildren().isEmpty())
	    {
    		optionsList.add("ActivePokemon");
	    }
    	if(! userBench.getChildren().isEmpty())
    	{
    		optionsList.add("BenchPokemon");
    	}
    	if(!optionsList.isEmpty()){
    		List<String> dialogData = Arrays.asList(optionsList.toArray(new String[optionsList.size()]));

    		@SuppressWarnings({ "rawtypes" })
    		Dialog dialog = new ChoiceDialog(dialogData.get(0), dialogData);
    		dialog.setTitle("Select pokemon");
    		dialog.setHeaderText("Select your choice");

    		Optional<String> result = dialog.showAndWait();
    		String selected = "cancelled.";
    				
    		if (result.isPresent()) {
    		    selected = result.get();
    		    if(selected=="ActivePokemon"){
			    	
			    	//Debug.message(((Label) button.getParent().lookup(".cardID")).getText().trim());
			    		userHand.getChildren().remove(newcard);
			    		((CardsGroup) user.getInhand()).removeCard(newcard.getCard());
			    		user.getActivePokemon().attachCard(newcard.getCard());
			    		//Debug.message(((Label) button.getParent().lookup(".cardID")).getText().trim());
			    }
			    else if(selected=="BenchPokemon")
			    {
			    	ArrayList<String> benchCards = new ArrayList<String>();
			    	for(Node card : userBench.getChildren()){
						PokemonCard tempCard = (PokemonCard) card;
						int id = tempCard.getCard().getID();
						Debug.message(id);
						benchCards.add(String.valueOf(id));
			    	}
			    	List<String> benchData = Arrays.asList(benchCards.toArray(new String[benchCards.size()]));
			    	
					@SuppressWarnings({ "rawtypes" })
					Dialog benchDialog = new ChoiceDialog(benchData.get(0), benchData);
					benchDialog.setTitle("Select pokemon");
					benchDialog.setHeaderText("Select your choice");

					Optional<String> benchOp = benchDialog.showAndWait();
					String select = "cancelled.";
					if(benchOp.isPresent())
					{
						select = benchOp.get();
						Debug.message(select);
						Pokemon benchC = null;
						for(Pokemon pokemon: user.getBenchCards())
						{
							if(pokemon.getID() == Integer.parseInt(select))
							{
								benchC = pokemon;
							}
						}
				    	
			    		userHand.getChildren().remove(newcard);
			    		((CardsGroup) user.getInhand()).removeCard(newcard.getCard());
			    		benchC.attachCard(newcard.getCard());
						
					}
			    }    
    		}
    	}
    }
        
    public HBox getUserBench(){
    	return this.userBench;
    }
    public HBox getAIBench(){
    	return this.AIBench;
    }
    public HBox getUserHand(){
    	return this.userHand;
    }
    public HBox getAIHand(){
    	return this.AIHand;
    }

	public void refreshCards(Player player) {
		HBox handpanel = null;
		HBox benchpanel = null;
		Pane activePokemon = null;
		if(player instanceof AIplayer){
			handpanel = AIHand;
			benchpanel = AIBench;
			activePokemon = aiActivePokemon;
			if(user.getActivePokemon()!=null){
				Debug.message("adding damage to label");
				userDamage.setText(Integer.toString(user.getActivePokemon().getDamage()));
			}
		}else{
			handpanel = userHand;
			benchpanel = userBench;
			activePokemon = userActivePokemon;
			if(ai.getActivePokemon()!=null){
				aiDamage.setText(Integer.toString(ai.getActivePokemon().getDamage()));
			}
		}
		addCardsToAIPanel(player.getInhandCards(), handpanel);	
		addCardsToAIPanel(player.getBenchCards(), benchpanel);
		if(player.getActivePokemon()!=null){
			activePokemon.getChildren().clear();
			activePokemon.getChildren().add(createPokemonCard(player.getActivePokemon()));
		}
	}
    
	public void addCardsToAIPanel(cardItem[] cards, HBox panel){
		panel.getChildren().clear();
		addCardsToPanel(cards, panel);
	}
	
	 private FlowPane createPokemonCard(Pokemon pokemon){
	    	FlowPane pokemonCard = new FlowPane();
	    	pokemonCard.getStyleClass().add("pokemonCard");
	    	Label cardID = new Label(Integer.toString(pokemon.getID())+"\t");
	    	cardID.getStyleClass().add("cardID");
	    	Label PokemonStage = new Label(pokemon.getStage()+"\t");
	    	Label PokemonHp = new Label(Integer.toString(pokemon.getHP()));
	    	Label PokemonName = new Label(pokemon.getName());
	    	PokemonName.setPrefWidth(70);
	    	pokemonCard.setMaxWidth(88);
	    	PokemonName.setWrapText(true);

	    	pokemonCard.getChildren().add(cardID);
	    	pokemonCard.getChildren().add(PokemonStage);
	    	pokemonCard.getChildren().add(PokemonHp);
	    	pokemonCard.getChildren().add(PokemonName);
	    	
	    	return pokemonCard;
	 }

	public void dealCard(String player) {
		cardItem newcard;
		if(player=="user"){
			newcard = user.dealCard();
			addCardToPanel(newcard, userHand);
		}
		else {
			newcard = ai.dealCard();
			ai.updateGUI();
		}
		
	}
	
	private PokemonCard evolveoptions(PokemonCard card){
		String basicPname = card.getCard().getBasePokemonName();
		ArrayList<PokemonCard> basicpokemons = new ArrayList<PokemonCard>();
		if(!userActivePokemon.getChildren().isEmpty()){
			PokemonCard tempCard = (PokemonCard) userActivePokemon.getChildren().get(0);
			if(tempCard.getCard().getStage().equals("Basic")){
				if(tempCard.getCard().getName().equals(basicPname)){
//					tempCard.evolve(card.getCard());
					basicpokemons.add(tempCard);
				}
			}
		}
		for(Node tempNode : userBench.getChildren()){
			PokemonCard tempCard = (PokemonCard) tempNode;
			if(tempCard.getCard().getStage().equals("Basic") && tempCard.getCard().getName().equals(basicPname)){
				basicpokemons.add(tempCard);
			}
		}
		ArrayList<String> optionsList = new ArrayList<String>();
		
		for(PokemonCard tempcard : basicpokemons){
			optionsList.add(Integer.toString(tempcard.getCard().getID()));
		}
		if(!optionsList.isEmpty()){
			DialogBoxHandler dialog = new DialogBoxHandler();
			Optional<String> result = dialog.getDialog(optionsList);
			String selected = "cancelled.";
		
			if (result.isPresent()) {
				selected = result.get();
				for(PokemonCard tempCard : basicpokemons){
					if(tempCard.getCard().getID()==(Integer.parseInt(selected))){
						return tempCard;
					}
				}
			}
		}
		return null;
	}
}