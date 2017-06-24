package model;

import controller.GameController;

public class Add extends ability{

	//add:target:your:trigger:opponent:turn-end:(heal:target:self:20)
	
	private String trigger;
	ability addAbility;
	
	public Add(String name, String target, String trigger, String triggerCondition, ability addAbility)
	{
		this.name = name;
		this.abilitytarget = target;
		this.trigger = trigger;
		this.triggerCondition = triggerCondition;
		this.addAbility = addAbility;
	}
	
	public String getTriggerCondition()
	{
		return this.triggerCondition;
	}

	public void useAbility() {
		if(this.abilitytarget.equalsIgnoreCase("opponent"))
		{
			this.abilitytarget = "choiceyour";
		}
		Pokemon pokemon = (Pokemon) target.getTargetObject(abilitytarget).getTarget();
		pokemon.addActiveAbility(addAbility);
		//GameController.getInstance().ulabelUpdate();
	}

	
	public boolean equals(Object o) {
		return false;
	}
	
}
