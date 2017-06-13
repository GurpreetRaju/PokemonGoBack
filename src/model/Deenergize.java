package model;

public class Deenergize extends ability{

	private int amount;
	
	public Deenergize(String newName, String newTarget, int newAmount){
		this.name = newName;
		this.abilitytarget = newTarget;
		this.amount = newAmount;
	}
	
	public void useAbility() {
		Pokemon pk = (Pokemon) target.getTargetObject(abilitytarget).getTarget();
		if(pk!=null){
			pk.dettachCardType(Energy.class, this.amount);
		}
	}
	
	public int getAmount(){
		return this.amount;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Deenergize){
			if(((Deenergize) o).getName().equals(name) && ((Deenergize) o).getAmount() == this.amount)
				return true;
		}
		return false;
	}

}
