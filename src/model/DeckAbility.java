package model;

import controller.GameController;

public class DeckAbility extends ability {

	private String targetDestination;
	private int amount;
	private boolean choice;
	public DeckAbility(String newName, String newTarget, String newTargetDestination, boolean newChoice, int newAmount){
		this.name = newName;
		this.abilitytarget = newTarget;
		this.targetDestination = newTargetDestination;
		this.amount = newAmount;
		this.choice = newChoice;
	}
	
	public void useAbility() {
		if(choice){
			for(int i=0;i<amount;i++){
				GameController.getInstance().getPanelPokemonDialog((Player) target.getTargetObject(this.abilitytarget).getTarget(), "hand");
			}
		}
	}
	
	public boolean equals(Object o) {
		if(o instanceof DeckAbility){
			if(((DeckAbility) o).getName().equals(this.name)){
				return true;
			}
		}
		return false;
	}
}
