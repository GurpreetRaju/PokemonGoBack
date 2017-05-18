package model;

public class colorlessEnergy extends Energy{
	
	public colorlessEnergy(String newEnergy) {
		super(newEnergy);
		
	}

	private String energy="Colorless";
	
	public String getEnergy() {
		return energy;
	}

	public void setEnergy(String newEnergy) {
		this.energy = newEnergy;
	}
}
