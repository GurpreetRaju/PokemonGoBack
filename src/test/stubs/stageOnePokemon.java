package test.stubs;

import test.stubs.*;

public class stageOnePokemon extends pokemonStage{
	private String stage = "stage-one";
	private String basicCardName;
	private Pokemon basicPokemon;
	
	public stageOnePokemon(String newEvolvePokemon){
		this.basicCardName = newEvolvePokemon;
	}
	
	public String getStage(){
		return this.stage;
	}
	
	public String getBasicPokemonName(){
		return this.basicCardName;
	}

	@Override
	public void evolve(Pokemon basicCard) {
		this.basicPokemon = basicCard;
	}
	
	public boolean isEvolved(){
		if(this.basicPokemon!=null){
			return true;
		}
		return false;
	}
	
	public Pokemon getBasicPokemonCard(){
		return this.basicPokemon;
	}
}
