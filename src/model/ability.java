package model;

import java.util.ArrayList;

public abstract class ability {
	
	protected String name;
	protected String abilitytarget;
	protected String triggerCondition;
	protected ArrayList<Energy> energyRequired;
	
	public String getName(){
		return this.name;
	}
	
	public String getTriggerCondition(){
		return this.triggerCondition;
	}
	
	public target getTargetObject(){
		return target.getTargetObject(this.abilitytarget);
	}
	
	public Object getTargetLocation(String source, String newtarget){
		Player player = (Player) target.getTargetObject(newtarget).getTarget();
		switch(source){
			case "deck":
				return player.getDeck();
			case "discard":
				return player.getDiscardPile();
			case "hand":
				return player.getInhand();
			default:
				return null;
		}
	}
	public ArrayList<Energy> getEnergyInfo(){
		return this.energyRequired;
	}
	
	public abstract boolean equals(Object o);
	public abstract void useAbility();

}
