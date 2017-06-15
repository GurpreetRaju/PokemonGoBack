package model;

public class applystatAbility extends ability{

	private String status;
	
	public applystatAbility(String name, String target, String status)
	{
		this.name = name;
		this.abilitytarget = target;
		this.status = status;
	}
	
	public void useAbility() {
		//paralyzed, asleep, stuck, poisoned
		
		Pokemon pktarget = (Pokemon) target.getTargetObject(abilitytarget).getTarget();
		pktarget.setState(status);
		
		//add functionality for each status
	}
	
	public boolean equals(Object o) {
			
		return false;
	}

}
