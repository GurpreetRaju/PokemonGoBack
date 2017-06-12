package model;

import java.util.ArrayList;

import controller.GameController;
import view.DialogBoxHandler;

public class Search extends ability{
	
	private String targetSource;
	private String filterType;
	private String filterCategory;
	private int amount;
	
	public Search(String newName, String newTarget, String newTargetSource, String newFilterType, String newFilterCategory, int newAmount){
		this.name = newName;
		this.abilitytarget = newTarget;
		this.targetSource = newTargetSource;
		this.filterType = newFilterType;
		this.filterCategory = newFilterCategory;
		this.amount = newAmount;
	}
	

	public void useAbility() {
		CardsGroup source = (CardsGroup) this.getTargetLocation(targetSource, abilitytarget);
		ArrayList<cardItem> cards = new ArrayList<cardItem>();
		if(filterCategory!=null){
			switch(filterCategory){
				case "basic": case "stage-one":
					cards.addAll(source.getAllPokemonCard(filterCategory));
					break;
				case "colorless": case "water": case "lightning": case "psychic": case "fighting":
					cards.addAll(source.getAllEnergyCards(filterCategory));
					break;
				case "stadium": case "supporter": case "item":
					cards.addAll(source.getAllTrainerCards(filterCategory));
					break;
			}
		}
		else{
			if(filterType!=null){
				try {
					StringBuilder sb = new StringBuilder(filterType);//StackOverFlow https://stackoverflow.com/questions/14972032/how-to-convert-lower-case-letters-to-upper-case-letters-and-upper-case-letters
					char c = sb.charAt(0);
					sb.setCharAt(0, Character.toUpperCase(c));
					Class<?> classType = Class.forName(filterType);
					ArrayList<cardItem> resultcards = source.getCardsOfType(classType);
					cards.addAll(resultcards);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

		for(int i=0; i<this.amount; i++){
			DialogBoxHandler dbox = new DialogBoxHandler();
			dbox.setOptionList(cards);
			int id = Integer.parseInt(dbox.getDialog());
			CardsGroup hand = (CardsGroup) ((Player) target.getTargetObject(abilitytarget).getTarget()).getInhand();
			hand.addCard(source.getCard(id));
			GameController.getInstance().addCardToPanel(source.getCard(id), GameController.getInstance().getHand((Player) target.getTargetObject(abilitytarget).getTarget()));
			source.removeCard(source.getCard(id));
		}
		//Rest of the code here
		
	}
	
	public boolean equals(Object o) {
		if(o instanceof Search){
			if(((Search) o).getName().equals(this.name) && this.amount == ((Search) o).getAmount() && ((Search) o).getFilterCategory().equals(this.filterCategory)){
				return true;
			}
		}
		return false;
	}
	
	public String getFilterCategory(){
		return this.filterCategory;
	}

	public int getAmount() {
		return this.amount;
	}
}