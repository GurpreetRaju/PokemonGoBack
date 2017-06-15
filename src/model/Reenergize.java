package model;

public class Reenergize extends ability{

	private int amount;
	private String targetSource;
	
	public Reenergize(String newName, String newSource, String newTarget, int newAmount){
		this.name = newName;
		this.targetSource = newSource;
		this.abilitytarget = newTarget;
		this.amount = newAmount;
	}

	public void useAbility() {
		Pokemon pkSource = (Pokemon) target.getTargetObject(this.targetSource).getTarget();
		Pokemon pkDestination = (Pokemon) target.getTargetObject(abilitytarget).getTarget();
		
		cardItem[] energy = pkSource.dettachCardType(Energy.class, amount);
		pkDestination.attachCard(energy);
	}
	
	public boolean equals(Object o) {
		return false;
	}
}
