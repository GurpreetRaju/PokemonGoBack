package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.FlowPane;
import model.Debug;
import model.Energy;
import model.Pokemon;
import model.ability;
import model.cardItem;

public class PokemonCard extends FlowPane{
	private Label cardName;
	private Label PokemonStage;
	private Label PokemonHp;
	private Label cardID;
	private Button button, loc;
	private HBox location;
	private boolean evolved = false;
	private Pokemon baseCard;
	
	private Pokemon card;
	
	public PokemonCard(Pokemon newCard, HBox newLoc){
		this.card = newCard;
		this.location = newLoc;
		this.getStyleClass().add("pokemonCard");
		this.cardID = new Label();
    	this.cardID.getStyleClass().add("cardID");
    	this.PokemonStage = new Label();
    	this.PokemonHp = new Label();
    	this.cardName = new Label();
    	this.cardName.setPrefWidth(70);
    	this.cardName.setWrapText(true);
    	this.button = new Button();

    	this.setOnMouseEntered(new EventHandler<MouseEvent>(){

    		@Override
    		public void handle(MouseEvent event){
    			String text = new String();
    			String text1 = new String();
    			String text2 = new String();
    			String text3 = new String();
    			text2 = ("Energies attached" + "\n");
    			Tooltip tttext = new Tooltip();
    			//text.setText(IntoString());
    			ability[] abilities = card.getAbilities();
    			for(int i=0; i<abilities.length;i++){
    				text = text + abilities[i].getName() + "\n" ;
    			}
    			cardItem [] energies =  card.getAttachedCards();
    			if(energies.length >= 1){
    				for (int j =0;j<energies.length;j++){
    					Debug.message(energies[j].getName());
    					text1 = ( text1 + energies[j].getName() + "\n") ;
    				}
    			text3 = text2 + text1;
    			}
    			else{
    				text3 = "";
    			}
    			//System.out.println("User bench" + userBench.getChildren().size());
    			tttext.setText("Abilities"+ "\n" + text + text3 );
    			button.setTooltip(tttext);
    		}
    	
    	});
    	
    	this.getChildren().add(this.cardID);
    	this.getChildren().add(this.PokemonStage);
    	this.getChildren().add(this.PokemonHp);
    	this.getChildren().add(this.cardName);
    	this.getChildren().add(this.button);

		init();
	}
		
	public void init(){
    	this.cardID.setText(Integer.toString(card.getID())+"\t");
    	this.PokemonStage.setText(card.getStage()+"\t");
    	this.PokemonHp.setText(Integer.toString(card.getHP()));
    	this.cardName.setText(card.getName());   	
	}
	
	public void setLocation(HBox newLocation){
		this.location = newLocation;
		this.location.getChildren().add(this);
	}
	
	public void setLocation(Button newLocation){
		this.loc = newLocation;
		this.loc.getChildrenUnmodifiable().add(this);
	}
	
	public HBox getLocation(){
		return this.location;
	}
	
	public void addOptionsActionListener(EventHandler<ActionEvent> evt){
    		button.setOnAction(evt);
	}

	public Pokemon getCard() {
		return this.card;
	}
	
	public Pokemon getBasicCard(){
		return this.baseCard;
	}
	
	public void evolve(Pokemon stageOne){
		Pokemon tempcard = this.card;
		this.card = stageOne;
		this.baseCard = tempcard;
		this.evolved = true;
		init();
	}
}
