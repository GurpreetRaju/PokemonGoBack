package model;

public class Redamage extends ability{

	private int amount;
	private String targetSource;
	
	public Redamage(String name, String newTargetSource,String newTargetDestination, int newAmount){
		this.name = name;
		this.targetSource = newTargetSource;
		this.abilitytarget = newTargetDestination;
		this.amount = newAmount;
	}
	
	
	public void useAbility() {
		
		Pokemon pSource = (Pokemon) target.getTargetObject(targetSource).getTarget();
		Pokemon pDestination = (Pokemon) target.getTargetObject(abilitytarget).getTarget();
		
		pDestination.addDamage(pSource.getDamage()*amount);
		
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
