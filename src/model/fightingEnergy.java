package model;

public class fightingEnergy extends Energy{
	private String energy="Fighting";
	
	public fightingEnergy(String newEnergy) {
		super(newEnergy);
	}

	public String getEnergy() {
		return energy;
	}

	public void setEnergy(String newEnergy) {
		this.energy = newEnergy;
	}
}
