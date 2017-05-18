package model;

public class lightingEnergy extends Energy{
	
	private String energy="Lighting";
	
	public lightingEnergy(String newEnergy) {
		super(newEnergy);
	}

	public String getEnergy() {
		return energy;
	}

	public void setEnergy(String newEnergy) {
		this.energy = newEnergy;
	}
	
}
