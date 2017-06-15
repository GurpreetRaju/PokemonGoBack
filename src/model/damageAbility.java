package model;

import java.util.ArrayList;

public class damageAbility extends ability {
	private int damageValue;
	private String count;
	private ArrayList<Energy> energyRequired;
	
	public damageAbility(String newName, int newDamage, ArrayList<Energy> newEnergyInfo, String newtarget, String count){
		this.name = newName;
		this.damageValue = newDamage;
		this.energyRequired = newEnergyInfo;
		this.abilitytarget = newtarget;
		this.count = count; //damageValue = count*damageValue {count = opp active energy}
		//dam:target:opponent-active:count(target:opponent-active:energy)*10 
	}
	
	public void setDamage(int newDamage){
		this.damageValue = newDamage;
	}
	
	public void useAbility(){
		//Debug.message("Attacking Opponent pokemon "+Turn.getInstance().getOpponent().getActivePokemon().getName());
		Pokemon pk = (Pokemon) this.getTargetObject().getTarget();
		if(pk!=null){
			pk.addDamage(this.damageValue);
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