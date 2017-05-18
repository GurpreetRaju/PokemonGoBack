package model;

public class Energy implements cardItem {

	public String energy;
	
	public Energy(String newEnergy){
		this.energy = newEnergy;
	}

	@Override
	public String getName() {
		return energy;
	}

	
}
