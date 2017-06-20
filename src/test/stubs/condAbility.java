package test.stubs;

import java.util.ArrayList;

import test.stubs.AbilityParser;
import test.stubs.UserPlayer;
import test.stubs.ability;

public class condAbility extends ability{

	private UserPlayer user;
	private String condition, ability;
	private ArrayList<Energy> EnergyInfo;
	
	public condAbility(String name, String condition, String ability, ArrayList<Energy> EnergyInfo)
	{
		this.name = name;
		this.condition = condition;
		this.ability = ability;
		this.EnergyInfo = EnergyInfo;
	}
	
	public void useAbility() {
		AbilityParser ap = new AbilityParser();
		if(condition.equals("flip"))
		{
			ap.getAbility(name, ability.split(" "), EnergyInfo);
		}
		else if(condition.equals("choice"))
		{
			
		}
		else if(condition.equals("ability"))
		{
			
		}
		else if(condition.equals("count"))
		{
			
		}
		else if(condition.equals("healed"))
		{
			
		}
	}
	
	public boolean equals(Object o) {
		return false;
	}

}
