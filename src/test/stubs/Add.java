package test.stubs;

import model.Pokemon;
import model.ability;
import model.target;

public class Add extends ability{

	//add:target:your:trigger:opponent:turn-end:(heal:target:self:20)
	
	private String trigger, triggerCondition;
	ability addAbility;
	
	public Add(String name, String target, String trigger, String triggerCondition, String addAbility)
	{
		this.name = name;
		this.abilitytarget = target;
		this.trigger = trigger;
		this.triggerCondition = triggerCondition;
		//this.addAbility = addAbility;
	}
	
	public String getTriggerCondition()
	{
		return this.triggerCondition;
	}

	public void useAbility() {
		Pokemon pokemon = (Pokemon) target.getTargetObject(abilitytarget).getTarget();
		pokemon.addActiveAbility(addAbility);
	}

	
	public boolean equals(Object o) {
		return false;
	}
	
}
