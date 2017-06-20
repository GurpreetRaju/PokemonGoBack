package model;

public class Deenergize extends ability{

	private String amount;
	
	public Deenergize(String newName, String newTarget, String newAmount){
		this.name = newName;
		this.abilitytarget = newTarget;
		this.amount = newAmount;
	}
	
	public void useAbility() {
		Pokemon pk = (Pokemon) target.getTargetObject(this.abilitytarget).getTarget();
		int value = 0; 
		if(this.amount.equals("youractive energy")){
			value = pk.getAttachedCardsCount(Energy.class);
		}
		else{
			value = Integer.valueOf(this.amount);
		}
		cardItem[] discardedEnergy = null;
		if(pk!=null){
			discardedEnergy = pk.dettachCardType(Energy.class, value);
		}
		if(discardedEnergy!=null){
			target.getTargetObject(abilitytarget).getPlayer().getDiscardPile().addCards(discardedEnergy);;
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
