package model;

public class AIplayer implements Player {
	
	private String name;
	private int score;
	private cardItem deck;
	
	public AIplayer(String newName) {
		this.name = newName;
		deck = new Deck();
		((Deck) deck).buildDeck(1);
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getScore() {
		return this.score;
	}
	
}
