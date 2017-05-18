package model;

public class GameModel {
	
	private Player user;
	private Player ai;
	
	public GameModel(){
		user = new UserPlayer("Flash");
		ai = new AIplayer("Future Flash");
		gameInitialize();
	}
	
	private void gameInitialize(){
		
	}
	
}
