package model;

import java.util.ArrayList;

public class CompositeAbility extends ability{

	private ArrayList<ability> abilities;
	
	public CompositeAbility(ArrayList<ability> newAbility){
		this.abilities = newAbility;
	}
	
	public CompositeAbility() {
		this.abilities = new ArrayList<ability>();
	}

	public void add(ability newAbility){
		this.abilities.add(newAbility);
	}
	
	public void remove(ability newAbility){
		this.abilities.remove(newAbility);
	}
	
	public  ArrayList<ability> get(){
		return this.abilities;
	}
	
	public void useAbility() {
		for(ability a : this.abilities){
			a.useAbility();
		}
	}
	
	public ArrayList<Energy> getEnergyInfo(){
		ArrayList<Energy> energyRequired = new ArrayList<Energy>();
		for(ability a: this.abilities){
			if(a instanceof damageAbility){
				energyRequired.addAll(((damageAbility) a).getEnergyInfo());
			}
		}
		return energyRequired;
	}
	
	public int size(){
		return this.abilities.size();
	}
	
	public boolean equals(Object o) {
		if(o instanceof CompositeAbility && this.name.equals(((CompositeAbility) o).name)){
			return true;
		}
		return false;
	}

}
