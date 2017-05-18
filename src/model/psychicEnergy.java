package model;

public class psychicEnergy extends Energy{

	private String energy="Psychic";
	
	public psychicEnergy(String newEnergy) {
		super(newEnergy);
	}

	public String getEnergy() {
		return energy;
	}

	public void setEnergy(String newEnergy) {
		this.energy = newEnergy;
	}

}
