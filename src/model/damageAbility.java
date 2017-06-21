package model;

import java.util.ArrayList;

public class damageAbility extends ability {
	private int damageValue;
	private String count;
	private ArrayList<Energy> energyRequired;
	
	public damageAbility(String newName, int newDamage, ArrayList<Energy> newEnergyInfo, String newtarget, String newCount){
		this.name = newName;
		this.damageValue = newDamage;
		this.energyRequired = newEnergyInfo;
		this.abilitytarget = newtarget;
		this.count = newCount; //damageValue = count*damageValue {count = opp active energy}
		//dam:target:opponent-active:count(target:opponent-active:energy)*10 
	}
	
	public void setDamage(int newDamage){
		this.damageValue = newDamage;
	}
	
	public void useAbility(){
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
		Turn.getInstance().changeTurn();
	
	}
	
	public int getDamage(){
		return this.damageValue;
	}
	
	public ArrayList<Energy> getEnergyInfo(){
		return this.energyRequired;
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