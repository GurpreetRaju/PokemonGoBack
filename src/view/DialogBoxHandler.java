package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import model.cardItem;

public class DialogBoxHandler {
	
	private ArrayList<String> optionsList;
	
	public DialogBoxHandler(){
		this.optionsList = new ArrayList<String>();
	}
	
	public void setOptionList(ArrayList<cardItem> cards){
		for(cardItem card: cards){
			this.optionsList.add(Integer.toString(card.getID()));
		}
	}
	
	public String getDialog(ArrayList<String> newOptionsList){
		this.optionsList = newOptionsList;
		return this.getDialog();
	}
	
	@SuppressWarnings("unchecked")
	public String getDialog(){
		
		List<String> dialogData = Arrays.asList(this.optionsList.toArray(new String[optionsList.size()]));
		@SuppressWarnings("rawtypes")
		Dialog dialog = new ChoiceDialog(dialogData.get(0), dialogData);
		dialog.setTitle("Available options");
		dialog.setHeaderText("Select your choice");

		Optional<String> result = dialog.showAndWait();
		String selected = null;
		
		if(result.isPresent()){
			selected = result.get();
		}
		return selected;
	}
}
