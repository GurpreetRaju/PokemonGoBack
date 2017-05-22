package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.Pokemon;
import model.ability;
import model.damageAbility;

public class Controller
{
    @FXML
	private ScrollPane userBenchScroll;
    private HBox userBench;
    private HBox userHand;
    private ScrollPane AIBenchScroll;
    private HBox AIBench;
    private HBox AIHand;
    private Pane aiActivePokemon;
    private Pane userActivePokemon;
    
    public Controller(){
    	userBenchScroll.setContent(userHand);
    	AIBenchScroll.setContent(AIHand);
    }
    
    public void addCardsToPanel(Pokemon pokemon, HBox panel){
    	FlowPane pokemonCard = createCard(pokemon);
    	panel.getChildren().add(pokemonCard);
    }
    
    public void removeCard(String id, HBox panel){
    	for(Node node: panel.getChildren()){
    		if(node.getStyleClass().contains(id)){
    			panel.getChildren().remove(node);
    		}
    	}
    }
    
    public void ShowCardDetails(Pokemon pokemon,HBox panel){
    	Pane pokemonCard = new Pane();
    	Label cardID = new Label(Integer.toString(pokemon.getID()));
    	Label PokemonStage = new Label(pokemon.getStage());
    	Label PokemonHp = new Label(Integer.toString(pokemon.getHP()));
    	Label PokemonName = new Label(pokemon.getName());
    	Label[] DamageAbility = createMultipleLabels(pokemon.getAbilities(),true);
    	Label[] attackDamage = createMultipleLabels(pokemon.getAbilities(),false);
    	Pane attackName = new Pane();
    	Pane attackValues = new Pane();
    	
    	attackName.getChildren().addAll(DamageAbility);
    	attackValues.getChildren().addAll(attackDamage);
    	pokemonCard.getChildren().add(cardID);
    	pokemonCard.getChildren().add(PokemonStage);
    	pokemonCard.getChildren().add(PokemonHp);
    	pokemonCard.getChildren().add(PokemonName);
    	pokemonCard.getChildren().add(attackName);
    	pokemonCard.getChildren().add(attackValues);
    	    	
    	panel.getChildren().add(pokemonCard);
    }
    
    private FlowPane createCard(Pokemon pokemon){
    	FlowPane pokemonCard = new FlowPane();
    	Label cardID = new Label(Integer.toString(pokemon.getID()));
    	Label PokemonStage = new Label(pokemon.getStage());
    	Label PokemonHp = new Label(Integer.toString(pokemon.getHP()));
    	Label PokemonName = new Label(pokemon.getName());
    	
    	pokemonCard.getChildren().add(cardID);
    	pokemonCard.getChildren().add(PokemonStage);
    	pokemonCard.getChildren().add(PokemonHp);
    	pokemonCard.getChildren().add(PokemonName);
    	return pokemonCard;
    }
    
    private Label[] createMultipleLabels(ability[] abilities,boolean value){
    	Label[] labels = new Label[abilities.length];
    	for(int i=0; i< abilities.length; i++){
    		if(value){
    			labels[i] = new Label(abilities[i].getName());
    		}
    		else{
    			labels[i] = new Label(Integer.toString(((damageAbility) abilities[i]).getDamage()));
    		}
    	}
    	return labels;
    }
    
    public void addActivePokemonUser(Pokemon pokemon){
    	this.userActivePokemon.getChildren().clear();
    	this.userActivePokemon.getChildren().add(createCard(pokemon));
    }
    
    public void addActivePokemonAI(Pokemon pokemon){
    	this.aiActivePokemon.getChildren().clear();
    	this.aiActivePokemon.getChildren().add(createCard(pokemon));
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
    

}
