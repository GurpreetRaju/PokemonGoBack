package model;

public class destatAbility extends ability {
	
	public destatAbility(String name, String abilityTarget){
		this.name = name;
		this.abilitytarget = abilityTarget;
	}

	public String getName() {
		return null;
	}

	public void useAbility() {
		Pokemon pktarget = (Pokemon) target.getTargetObject(abilitytarget).getTarget();
		pktarget.setState("normal");
		
	}

	public boolean equals(Object o) {
		return false;
	}

}
