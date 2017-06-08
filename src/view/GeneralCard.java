package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import model.cardItem;

public class GeneralCard extends FlowPane{
	
	private Label cardID;
	private Label cardName;
	private Button button;
	private cardItem card;

	public GeneralCard(cardItem newcard){
		this.card = newcard;
		this.getStyleClass().add("card");
		this.cardID = new Label();
		this.cardID.getStyleClass().add("cardID");
		this.cardName = new Label();
		this.cardName.setPrefWidth(70);
		this.setMaxWidth(88);
		this.cardName.setWrapText(true);
		this.button = new Button();
		this.getChildren().add(cardID);
		this.getChildren().add(cardName);
		this.getChildren().add(button);
		init();
	}
	private void init(){
		this.cardID.setText(Integer.toString(this.card.getID())+"\t");
		this.cardName.setText(this.card.getName());
	}
	public void addOptionsActionListener(EventHandler<ActionEvent> evt){
		button.setOnAction(evt);
	}
	public cardItem getCard(){
		return this.card;
	}
}
