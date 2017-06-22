package model;

import java.util.ArrayList;

public class Retreat{
	
	private String energy;
	private int count;
	
	public Retreat(String newEnergy, int newCount){
		this.energy = newEnergy;
		this.count = newCount;
	}
	
	public boolean useAbility(){
		Pokemon pokemon = Turn.getInstance().getCurrentPlayer().getActivePokemon();
		return pokemon.dettachCardType(this.energy, this.count);
	}
}
