package model;

public class Energy implements cardItem {

	public String energy;
	
	public Energy(String newEnergy){
		this.energy = newEnergy;
	}

	public String getName() {
		return energy;
	}

	
}
