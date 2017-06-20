package test.stubs;

import test.stubs.Pokemon;
import test.stubs.ability;

public class Redamage extends ability{

	private int amount;
	private String targetSource;
	private String source;
	private String destination;
	
	public Redamage(String name, String newTargetSource,String newSource, String newTargetDestination,String newDestination, int newAmount){
		this.name = name;
		this.targetSource = newTargetSource;
		this.source = newSource;
		this.abilitytarget = newTargetDestination;
		this.destination = newDestination;
		this.amount = newAmount;
	}
	
	
	public void useAbility() {
		
		Pokemon pSource = (Pokemon) this.getTargetLocation(source, targetSource);
		Pokemon pDestination = (Pokemon) this.getTargetLocation(destination, this.abilitytarget);
		
		pDestination.addDamage(pSource.getDamage()*amount);
		
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
