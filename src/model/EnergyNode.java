package model;

public class EnergyNode {
	
	private Energy energy;
	private int count;
	
	public EnergyNode(Energy newEnergy, int newCount){
		this.energy = newEnergy;
		this.count = newCount;
	}
	
	public EnergyNode(){
		this.energy = new Energy("colorless");
		this.count = 0;
	}
	
	public void setEnergyCount(int newCount){
		this.count = newCount;
	}
	
	public void setEnergyType(Energy newEnergy){
		this.energy = newEnergy;
	}
	
	public void setEnergyType(String newType){
		this.energy = new Energy("newType");
	}
	
	public String getEnergyType(){
		return this.energy.getName();
	}
	
	public int getEnergyCount(){
		return this.count;
	}
	
}
