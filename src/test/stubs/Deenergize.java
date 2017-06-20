package test.stubs;

import test.stubs.*;

public class Deenergize extends ability{

	private String amount;
	
	public Deenergize(String newName, String newTarget, String newAmount){
		this.name = newName;
		this.abilitytarget = newTarget;
		this.amount = newAmount;
	}
	
	public void useAbility() {
		Pokemon pk = (Pokemon) target.getTargetObject(abilitytarget).getTarget();
		if(pk!=null){
			pk.dettachCardType(Energy.class, Integer.valueOf(this.amount));
		}
	}
	
	public int getAmount(){
		return Integer.valueOf(this.amount);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Deenergize){
			if(((Deenergize) o).getName().equals(name) && ((Deenergize) o).getAmount() == Integer.valueOf(this.amount))
				return true;
		}
		return false;
	}

}
