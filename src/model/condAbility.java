package model;

import java.util.ArrayList;
import java.util.Random;

import controller.GameController;

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
	

	public condAbility(String name, String newCondition, ability newConditionAbility, ability newAbility1, ability newAbility2, ArrayList<EnergyNode> energyInfo)
	{
		this.name = name;
		this.condition = newCondition;
		this.ability1 = newAbility1;
		this.ability2 = newAbility2;
		this.conditionAbility = newConditionAbility;
		this.energyInfo = energyInfo;
	}
	
	@SuppressWarnings("unused")
	public void useAbility() {
		
		if(condition.contains("count")){
			String[] countA = condition.split(" ");
			String energyType = countA[3];
			int digit = Integer.parseInt(countA[5]);
			int cardsInt = ((Pokemon) target.getTargetObject(countA[1]).getTarget()).getAttachedCardsCount(Energy.class);
			//Debug.message(cardsInt + " comparing with " + digit);
			if(cardsInt>digit){
				//Debug.message("Ability name: "+ this.name+" Ability type: "+ this.ability1.getClass().getSimpleName());
				this.ability1.useAbility();
			}
			else if(this.ability2!=null){
				this.ability2.useAbility();
			}			
		}
		else if(condition.contains("healed")){
			String temptarget = condition.substring(condition.indexOf(" ")+1);
			Pokemon p = ((Pokemon) target.getTargetObject(temptarget).getTarget());
			if(p!=null){		
				if(p.isHealed()){
					this.ability1.useAbility();
					//Debug.message(this.ability1.getClass().getName());
				}
				else{
					if(this.ability2!=null){
						//Debug.message(this.ability2.getClass().getName());
						this.ability2.useAbility();
					}
					//Debug.message("checkpoint");
					}
			}
			//Debug.message("Ability name: "+ this.name+" Ability condition: "+this.condition);
		}
		else{
			switch(condition){
			case "flip":
				Random random = new Random();
	            int number = random.nextInt(2);
	            if(number == 0){
	            	this.ability1.useAbility();
	            }
	            else{
	            	if(this.ability2!=null){
	            		//Debug.message("else "+this.ability2.getClass().getSimpleName());
	            		this.ability2.useAbility();
	            	}
	            }
	            break;
			case "choice":
				if(GameController.getInstance().getAbilityChoice()){
	            	this.ability1.useAbility();
	            }
	            else{
	            	if(this.ability2!=null){
	            		this.ability2.useAbility();
	            	}
	            }
				break;
			case "ability":
				if(GameController.getInstance().getAbilityChoice()){
					Debug.message("Ability condition "+this.conditionAbility.getName());
					this.conditionAbility.useAbility();
	            	this.ability1.useAbility();
	            	//Debug.message(this.ability1.getClass().getName());
	            }
	            else{
	            	if(this.ability2!=null){
	            		//Debug.message(this.ability2.getClass().getName());
	            		this.ability2.useAbility();
	            	}
	            }
				//Debug.message("Ability name: "+ this.name+" Ability condition: "+this.condition);
				break;
			}
		}
		GameController.getInstance().ulabelUpdate();
	}
	
	public boolean equals(Object o) {
		return false;
	}

}
