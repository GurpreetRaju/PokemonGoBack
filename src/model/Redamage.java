package model;

import controller.GameController;

public class Redamage extends ability{

	private String amount;
	private String targetSource;
	private int count;
	
	public Redamage(String name, String newTargetSource,String newTargetDestination, String newAmount){
		this.name = name;
		this.targetSource = newTargetSource;
		this.abilitytarget = newTargetDestination;
		this.amount = newAmount;
	}
	
	
	public void useAbility() {
		
		//Debug.message(this.name +" "+ this.targetSource +" *"+ this.abilitytarget +"* "+ this.amount);
		
		Pokemon pSource = (Pokemon) target.getTargetObject(targetSource).getTarget();
		Pokemon pDestination = null;
		if(this.amount.equals("opponentdamage")){
			pDestination = pSource;
		}
		else{
			pDestination = (Pokemon) target.getTargetObject(this.abilitytarget).getTarget();
		}
		//distribute count to different pokemons
		pDestination.addDamage(pSource.getDamage()*count);
		GameController.getInstance().ulabelUpdate();
		
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
