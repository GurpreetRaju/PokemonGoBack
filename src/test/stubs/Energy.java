package test.stubs;

import test.stubs.cardItem;

public class Energy implements cardItem {

	public String energy;
	private int id;
	
	public Energy(String newEnergy, int newID){
		this.energy = newEnergy;
		this.id = newID;
	}
	
	public Energy(String newEnergy){
		this.energy = newEnergy;
	}

	@Override
	public String getName() {
		return energy;
	}
	
	public int getID(){
		return this.id;
	}

	
}
