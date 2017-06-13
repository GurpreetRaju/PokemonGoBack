package model;

public abstract class ability {
	
	protected String name;
	protected String abilitytarget;
	
	public String getName(){
		return this.name;
	}
	
	public target getTargetObject(){
		return target.getTargetObject(this.abilitytarget);
	}
	
	public Object getTargetLocation(String source, String newtarget){
		Player player = (Player) target.getTargetObject(newtarget).getTarget();
		switch(source){
			case "deck":
				return player.deck;
			case "discard":
				return player.userDiscardPile;
			default:
				return null;
		}
	}
	
	public abstract boolean equals(Object o);
	public abstract void useAbility();

}
