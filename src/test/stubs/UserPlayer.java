package test.stubs;

import test.stubs.*;

public class UserPlayer extends Player {
		
	public UserPlayer(String newName){
		super(newName);
		this.name = newName;
		deck = (cardItem) new Deck(2);
//		((Deck) deck).buildDeck();
		((Deck) deck).buildDeckTest();
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
