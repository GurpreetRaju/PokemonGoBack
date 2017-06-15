package model;

public class Shuffle extends ability{
	
	public Shuffle(String name, String target)
	{
		this.name = name;
		this.abilitytarget = target;
	}
	
	public void useAbility() {
		 Deck shuffle =  (Deck) target.getTargetObject(abilitytarget).getTarget();
		 shuffle.shufflecards();
		}

	public boolean equals(Object o) {
		
		return false;
	}

}
