package model;

import java.util.ArrayList;

public class CardsGroup implements cardItem {
	
	private ArrayList<cardItem> groupCards = new ArrayList<cardItem>();
	
	public void addCard(cardItem newCard){
		this.getGroupCards().add(newCard);
	}
	
	public cardItem removeFirstCard(){
		return this.getGroupCards().remove(0);
	}
	
	public cardItem[] getCard(){
		return this.getGroupCards().toArray(new cardItem[this.getGroupCards().size()]);
	}
	
	public void removeCard(cardItem newCard){
		this.getGroupCards().remove(newCard);
	}

	@Override
	public String getName() {
		return null;
	}
	
	public int getID(){
		return 99;
	}

	public ArrayList<cardItem> getGroupCards() {
		return this.groupCards;
	}

	public void setGroupCards(ArrayList<cardItem> groupCards) {
		this.groupCards = groupCards;
	}

}
