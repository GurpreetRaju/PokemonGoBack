package model;

public class healingAbility extends ability {
	
	private int healingValue;
	
	public healingAbility(String name, int value,String newtarget){
		this.name = name;
		this.healingValue = value;
		this.abilitytarget = newtarget;
	}
	
	@Override
	public void useAbility() {
		//Debug.message(Turn.getInstance().getCurrentPlayer().name);
		Pokemon pokemon = (Pokemon) this.getTargetObject().getTarget();
		if(pokemon!=null){
			pokemon.removeDamage(healingValue);
		}
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof healingAbility){
			if(this.healingValue == ((healingAbility) o).healingValue && this.name == ((healingAbility) o).name){
				return true;
			}
		}		
		return false;	
	}

}
