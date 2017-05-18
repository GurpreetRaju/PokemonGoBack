package model;

public class waterEnergy extends Energy {
	private String energy="Water";
	
	public waterEnergy(String newEnergy) {
		super(newEnergy);
	}

	public String getEnergy() {
		return energy;
	}

	public void setEnergy(String newEnergy) {
		this.energy = newEnergy;
	}
}
