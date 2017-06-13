package model;

public class destatAbility extends ability {
	private String abilitytarget;
	
	public destatAbility(String abilityTarget){
		this.abilitytarget = abilitytarget;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void useAbility() {
		// TODO Auto-generated method stub
		Pokemon pktarget = (Pokemon) target.getTargetObject(abilitytarget).getTarget();
		pktarget.setState("normal");
		
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
