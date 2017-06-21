package model;

public abstract class ability {
	
	protected String name;
	protected String abilitytarget;
	protected String triggerCondition;
	
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
	
	public abstract boolean equals(Object o);
	public abstract void useAbility();

}
