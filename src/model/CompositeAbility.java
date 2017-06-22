package model;

import java.util.ArrayList;

public class CompositeAbility extends ability{

	private ArrayList<ability> abilities;
	
	public CompositeAbility(ArrayList<ability> newAbility, ArrayList<EnergyNode> nEnergyInfo){
		this.abilities = newAbility;
		this.energyInfo = nEnergyInfo;
	}
	
	public CompositeAbility(String abilityName, ArrayList<EnergyNode> nEnergyInfo) {
		this.name = abilityName;
		this.abilities = new ArrayList<ability>();
		this.energyInfo = nEnergyInfo;
	}

	public void add(ability newAbility){
		this.abilities.add(newAbility);
	}
	
	public void remove(ability newAbility){
		this.abilities.remove(newAbility);
	}
	
	public ability[] getAbilities(){
		return this.abilities.toArray(new ability[this.abilities.size()]);
	}
	
	public  ArrayList<ability> get(){
		return this.abilities;
	}
	
	public void useAbility() {
		for(ability a : this.abilities){
			a.useAbility();
		}
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
