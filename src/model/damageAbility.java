package model;

import java.util.ArrayList;

import controller.GameController;



public class damageAbility extends ability {
	private int damageValue;
	private String count;
	
	public damageAbility(String newName, int newDamage, ArrayList<EnergyNode> newEnergyInfo, String newtarget, String newCount){
		this.name = newName;
		this.damageValue = newDamage;
		this.energyInfo = newEnergyInfo;
		this.abilitytarget = newtarget;
		this.count = newCount; //damageValue = count*damageValue {count = opp active energy}
		//dam:target:opponent-active:count(target:opponent-active:energy)*10 
	}
	
	public void setDamage(int newDamage){
		this.damageValue = newDamage;
	}
	
	public void useAbility(){
		if(this.abilitytarget.equalsIgnoreCase("opponent"))
		{
			this.abilitytarget = "choiceopponent";
		}
		Debug.message(this.abilitytarget);
		Pokemon pokm = (Pokemon) target.getTargetObject(this.abilitytarget).getTarget();
		int times = 1;
		if(count!=null){
			Debug.message("Pokemon name "+ pokm.getName() +"  Energy info "+pokm.getAttachedCardsCount(Energy.class));
			times = pokm.getAttachedCardsCount(Energy.class);
		}
		//Debug.message("Attacking Opponent pokemon "+Turn.getInstance().getOpponent().getActivePokemon().getName());
		if(pokm!=null){
			pokm.addDamage(this.damageValue*times);
		}
	   // GameController.getInstance().ulabelUpdate();
	}
	
	public int getDamage(){
		return this.damageValue;
	}
	
	public boolean equals(Object o){
		if(o instanceof damageAbility){
			if(this.name == ((damageAbility) o).name && this.damageValue == ((damageAbility) o).damageValue){
				return true;
			}
		}		
		return false;
	}	
}