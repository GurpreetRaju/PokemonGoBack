package test.stubs;

import test.stubs.*;

public class destatAbility extends ability {
	
	public destatAbility(String abilityTarget){
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
