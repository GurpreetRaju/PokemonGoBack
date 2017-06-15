package model;

import java.util.ArrayList;

public class UserPlayer extends Player {
		
	public UserPlayer(String newName){
		super(newName);
		this.name = newName;
		deck = new Deck(2);
		((Deck) deck).buildDeck();
//		((Deck) deck).buildDeckTest();
		activeAbilities = new ArrayList<ability>();
	}
		
	public String getName() {
		return this.name;
	}

	public int getScore() {
		return this.score;
	}
	
	public void setTurn(boolean newTurn){
		this.turn = newTurn;
	}
}
