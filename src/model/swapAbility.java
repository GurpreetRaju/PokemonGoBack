package model;

public class swapAbility extends ability{
	private String name;
	private String activepokemon;
	private String benchpokemon;
	
	public swapAbility(String name,String activepokemon, String benchpokemon){
		this.name = name;
		this.activepokemon = activepokemon;
		this.benchpokemon = benchpokemon;
		
	}
	
	
	public boolean equals(Object o) {
		return false;
	}
	
	public void useAbility() {
		
		Player player = Turn.getInstance().getCurrentPlayer();
		CardsGroup benchcards = player.getBench();
		cardItem active = player.getActivePokemon();
		//PokemonCard activeCard = new PokemonCard(active, userActivePokemon);
		for(cardItem card: benchcards.getCard()){
			//Do code here
			//if(){
				player.setActivePokemon((Pokemon)card);
			//}
		}
		benchcards.addCard(active);
		
			
		}
		
	}
	

