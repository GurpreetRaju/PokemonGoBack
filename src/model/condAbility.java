package model;

import java.util.ArrayList;
import java.util.Random;

public class condAbility extends ability{

	/*cond:flip:dam:target:opponent-active:30
		cond:flip:applystat:status:paralyzed:opponent-active
		cond:flip:dam:target:opponent-active:10
		cond:healed:target:your-active:dam:target:opponent-active:80
		cond:flip:deenergize:target:opponent-active:1
		cond:flip:dam:target:opponent-active:30
		cond:flip:dam:target:opponent-active:20
		cond:flip:dam:target:opponent-active:40:else:applystat:status:paralyzed:opponent-active
		cond:flip:deenergize:target:opponent-active:1
		cond:ability:deck:destination:discard:target:choice:you:1:(search:target:you:source:deck:filter:top:8:1,shuffle:target:you)
		cond:count(target:your-active:energy:psychic)>0:dam:target:opponent-active:20
		cond:flip:dam:target:opponent-active:30:else:dam:target:your-active:30
		cond:flip:dam:target:opponent-active:10
		cond:flip:(applystat:status:asleep:opponent-active,applystat:status:poisoned:opponent-active)
		cond:choice:shuffle:target:opponent
		cond:flip:heal:target:your-active:10
	 */
	private String condition;
	private ability ability1, ability2, conditionAbility;
	private ArrayList<Energy> EnergyInfo;
	
	public condAbility(String name, String condition, ability newConditionAbility, ability newAbility1, ability newAbility2)
	{
		this.name = name;
		this.condition = condition;
		this.ability1 = newAbility1;
		this.ability2 = newAbility2;
		this.conditionAbility = newConditionAbility;
		//this.EnergyInfo = EnergyInfo;
	}
	
	@SuppressWarnings("unused")
	public void useAbility() {
		Debug.message("Hello");
    	
		if(condition.contains("count")){
			if(this.ability2==null){
            	//this.ability1.useAbility();
				Debug.message(this.ability1.getClass().getName() + condition +" Test");
            }
            else{
            	if(this.ability2!=null){
            		Debug.message(this.ability2.getClass().getName());
            		//this.ability2.useAbility();
            	}
            }
			Debug.message("Ability name: "+ this.name+" Ability condition: "+this.condition);
		}
		else{
			switch(condition){
			case "flip":
				Random random = new Random();
	            int number = random.nextInt(2);
	            if(number == 0){
	            	//this.ability1.useAbility();
	            	if(this.ability1 instanceof CompositeAbility){
	            		for(ability a:((CompositeAbility) this.ability1).getAbilities()){
	            			Debug.message(a.getClass().getSimpleName());
	            		}
	            	}
	            	else{
		            	Debug.message(this.ability1.getClass().getSimpleName());	
	            	}
	            }
	            else{
	            	if(this.ability2!=null){
	            		Debug.message("else "+this.ability2.getClass().getSimpleName());
	            		//this.ability2.useAbility();
	            	}
	            }
	            break;
			case "choice":
				if(true){
	            	//this.ability1.useAbility();
	            	Debug.message(this.ability1.getClass().getSimpleName());
	            }
	            else{
	            	if(this.ability2!=null){
	            		Debug.message(this.ability2.getClass().getSimpleName());
	            		//this.ability2.useAbility();
	            	}
	            }
				Debug.message("Ability name: "+ this.name+" Ability condition: "+this.condition);
				break;
			case "ability":
				if(this.conditionAbility!=null){
	            	//this.ability1.useAbility();
	            	Debug.message(this.ability1.getClass().getName());
	            }
	            else{
	            	if(this.ability2!=null){
	            		Debug.message(this.ability2.getClass().getName());
	            		//this.ability2.useAbility();
	            	}
	            }
				Debug.message("Ability name: "+ this.name+" Ability condition: "+this.condition);
				break;
			case "healed":
				if(this.ability2!=null){
	            	//this.ability1.useAbility();
	            	Debug.message(this.ability1.getClass().getName());
	            }
	            else{
	            	if(this.ability2!=null){
	            		Debug.message(this.ability2.getClass().getName());
	            		//this.ability2.useAbility();
	            	}
	            }
				Debug.message("Ability name: "+ this.name+" Ability condition: "+this.condition);
				break;
			}
		}
	}
	
	public boolean equals(Object o) {
		return false;
	}

}
