package model;

public class Redamage extends ability{

	private String amount;
	private String targetSource;
	private int count;
	
	public Redamage(String name, String newTargetSource,String newTargetDestination, String newAmount){
		this.name = name;
		this.targetSource = newTargetSource;
		this.abilitytarget = newTargetDestination;
		this.amount = newAmount;
	}
	
	
	public void useAbility() {
		
		Pokemon pSource = (Pokemon) target.getTargetObject(targetSource).getTarget();
		Pokemon pDestination = (Pokemon) target.getTargetObject(abilitytarget).getTarget();
		//distribute count to different pokemons
		pDestination.addDamage(pSource.getDamage()*count);
		
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
