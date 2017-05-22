package controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.AIplayer;
import model.Debug;
import model.Player;
import model.Pokemon;
import model.Turn;
import model.UserPlayer;
import model.ability;
import model.cardItem;
import model.damageAbility;

public class GameController {
	
	private Player user;
	private Player ai;
	
	public GameController(){

	}
	
	public void toss(){
		boolean userTurn = true;
        boolean aiTurn = false;
        String dec;
        ButtonType toss = new ButtonType("Toss", ButtonBar.ButtonData.OK_DONE);
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"It's Time for Toss ",toss);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setHeaderText(null);
        alert.setX(475);
        alert.setY(270);
        alert.setHeight(50);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == toss)
        {
            // ... user chose OK
            //head or tail
            Circle circle = new Circle();
            circle.setCenterX(200.0f);
            circle.setCenterY(135.0f);
            //Setting the radius of the circle
            circle.setRadius(25.0f);
            //Setting the color of the circle
            circle.setFill(javafx.scene.paint.Color.BROWN);
            //Setting the stroke width of the circle
            circle.setStrokeWidth(20);
            //Instantiating the path class
            javafx.scene.shape.Path path = new javafx.scene.shape.Path();
            //Creating the MoveTo path element
            MoveTo moveTo = new MoveTo(200, 250);
            //Creating the Cubic curve path element
            CubicCurveTo cubicCurveTo1 = new CubicCurveTo(200, 250, 200, 250, 200, 250);
            CubicCurveTo cubicCurveTo = new CubicCurveTo(200, 40, 200, 40,200, 40);
            //Adding the path elements to Observable list of the Path class
            path.getElements().add(moveTo);
            path.getElements().add(cubicCurveTo);
            path.getElements().add(cubicCurveTo1);
            //Creating a path transition
            PathTransition pathTransition = new PathTransition();
            //Setting the duration of the path transition
            pathTransition.setDuration(Duration.millis(4000));
            //Setting the node for the transition
            pathTransition.setNode(circle);
            //Setting the path
            pathTransition.setPath(path);
            //Setting the cycle count for the transition
            pathTransition.setCycleCount(1);
            //Setting auto reverse value to false
            //pathTransition.setAutoReverse(true);
            //Playing the animation
            pathTransition.play();
            //Creating a Group object
          // Thread.sleep(1000);
            Random random = new Random();
            int number = random.nextInt(2)+1;
            //public String dec;
            ButtonType Head = new ButtonType("Head", ButtonBar.ButtonData.YES);
            ButtonType Tail = new ButtonType("Tail", ButtonBar.ButtonData.NO);
            Alert ts = new Alert(Alert.AlertType.INFORMATION,"Select Your Coin_S",Head,Tail);
            ts.initStyle(StageStyle.UNDECORATED);
            ts.setHeaderText(null);
            ts.setX(475);
            ts.setY(270);
            System.out.print(number+"\n");
            Optional<ButtonType> result1 = ts.showAndWait();
            if ((result1.get().getText().toString() == "Head") && number==1)
            {
                //num2=1;
                dec="Congratz you won the toss";
                userTurn = true;
                aiTurn = false;
                System.out.print(dec);
            }
            else if ((result1.get().getText().toString()== "Tail") && number==2)
            {
                // num2=2;
                dec="Congratz you won the toss";
                userTurn = true;
                aiTurn = false;
                System.out.print(dec);
            }
            else
            {
                dec="Ooops!! You can't make it";
                userTurn = false;
                aiTurn = true;
                System.out.print(dec);
            }

            Group root1 = new Group(circle);
            //Creating a scene object
            Scene scene = new Scene(root1, 400, 350);
            //Setting title to the Stage
            Stage pm1 = new Stage();
            pm1.setScene(scene);
            pm1.initStyle(StageStyle.UNDECORATED);
            pm1.setResizable(false);
            pm1.show();
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(3),new EventHandler<ActionEvent>()
            {
                        @Override
                        public void handle(ActionEvent event)
                        {
                          pm1.hide();
                            ButtonType toss1 = new ButtonType("Let's Play Now", ButtonBar.ButtonData.OK_DONE);
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION,""+dec, toss1);
                            alert1.initStyle(StageStyle.UNDECORATED);
                            alert1.setHeaderText(null);
                            alert1.setX(475);
                            alert1.setY(270);
                            alert1.show();
                        }
              }));
              timeline.play();
              init(userTurn,aiTurn);
        }
        else
        {
            // ... user chose CANCEL or closed the dialog
        }
	}
	
	public void init(boolean userTurn,boolean aiTurn){
		user = new UserPlayer("Flash");
		ai = new AIplayer("Future Flash");
		init();
		Turn turn = new Turn(ai,user);
		ai.setTurn(userTurn);
		user.setTurn(aiTurn);
	}
	
	@FXML
	private ScrollPane userHandScroll;
	@FXML
	private HBox userBench;
	@FXML
	private HBox userHand;
	@FXML
	private ScrollPane AIHandScroll;
	@FXML
	private HBox AIBench;
	@FXML
	private HBox AIHand;
	@FXML
	private Pane aiActivePokemon;
	@FXML
	private Pane userActivePokemon;
    
	@FXML
    public void init(){
    	addCardsToPanel(user.dealMultipleCards(7),userHand);
    	addCardsToPanel(ai.dealMultipleCards(7), AIHand);
//    	userHandScroll = new ScrollPane();
//		AIHandScroll = new ScrollPane();
//    	userHandScroll.setContent(userHand);
//    	AIHandScroll.setContent(AIHand);
    }
    
    public void addCardsToPanel(cardItem[] cards, HBox panel){
    	FlowPane newCard = null;
    	for(cardItem card : cards){
    		if(card instanceof Pokemon){
    			newCard = createPokemonCard((Pokemon) card);
    		}
    		else {
    			newCard = createCard(card);
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
    
    public void ShowCardDetails(Pokemon pokemon,HBox panel){
    	Pane pokemonCard = new Pane();
    	pokemonCard.getStyleClass().add("pokemonCard");
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
    @FXML
    private FlowPane createPokemonCard(Pokemon pokemon){
    	FlowPane pokemonCard = new FlowPane();
    	//pokemonCard.setStyle("-fx-background-color: #fff;"+"-fx-border-color: #000;"+"-fx-border-width: 1px;"+
    	//		"-fx-pref-width: 52px;"+ "-fx-pref-height: 70px");
    	pokemonCard.getStyleClass().add("pokemonCard");
    	Label cardID = new Label(Integer.toString(pokemon.getID()));
    	cardID.getStyleClass().add("cardID");
    	Label PokemonStage = new Label(pokemon.getStage());
    	Label PokemonHp = new Label(Integer.toString(pokemon.getHP()));
    	Label PokemonName = new Label(pokemon.getName());
    	
    	CheckBox cb = new CheckBox();
    	
    	cb.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				String [] arrayData = {"Make active", "Put on bench", "View card abilities"};
				List<String> dialogData = Arrays.asList(arrayData);

				Dialog dialog = new ChoiceDialog(dialogData.get(0), dialogData);
				dialog.setTitle("Available options");
				dialog.setHeaderText("Select your choice");

				Optional<String> result = dialog.showAndWait();
				String selected = "cancelled.";
						
				if (result.isPresent()) {
				    selected = result.get();
				    if(selected=="Make active"){
				    	String id = ((Label) cb.getParent().lookup(".cardID")).getText();
				    	//(CardsGroup (Turn.getCurrentPlayer().getInhand()));
				    }
				    else if(selected=="Put on bench"){
				    	
				    }
				    else if(selected=="View card abilities"){
				    	
				    }
				}
			}
    		
		});
    	pokemonCard.getChildren().add(cb);
    	pokemonCard.getChildren().add(cardID);
    	pokemonCard.getChildren().add(PokemonStage);
    	pokemonCard.getChildren().add(PokemonHp);
    	pokemonCard.getChildren().add(PokemonName);
    	
    	return pokemonCard;
    }
    
    
    @FXML
    private FlowPane createCard(cardItem card){
    	FlowPane newCard = new FlowPane();
    	
    	newCard.getStyleClass().add("card");
    	Label cardID = new Label(Integer.toString(card.getID()));
    	Label cardName = new Label(card.getName());
    	
    	newCard.getChildren().add(cardID);
    	newCard.getChildren().add(cardName);
    	
    	return newCard;
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
    @FXML
    public void addActivePokemonUser(Pokemon pokemon){
    	this.userActivePokemon.getChildren().clear();
    	this.userActivePokemon.getChildren().add(createCard(pokemon));
    }
    @FXML
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
