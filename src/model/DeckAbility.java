package model;

import java.util.ArrayList;

import controller.GameController;

public class DeckAbility extends ability {

	private String targetDestination;
	private int amount;
	private String choice;
	
	public DeckAbility(String newName, String newTarget, String newTargetDestination, int newAmount, String newChoice, ArrayList<EnergyNode> newEnergyInfo){
		this.name = newName;
		this.abilitytarget = newTarget;
		this.targetDestination = newTargetDestination;
		this.amount = newAmount;
		this.choice = newChoice; //count = yourhand, opponenthand
		this.energyInfo = newEnergyInfo;
	}
	
	public void useAbility() {
		if(choice!=null){
			Debug.message(this.choice);
			CardsGroup cardsGroup = ((CardsGroup) target.getTargetObject(this.choice).getTarget());
			if(cardsGroup!=null){	
				this.amount = cardsGroup.getGroupCards().size();
			}
		}
		//Debug.message(this.abilitytarget+" " + this.targetDestination + " " +amount + " "+ this.choice);
		for(int i=0;i<amount;i++){
			cardItem nCard = null;
			if(this.choice!=null){
				nCard = ((CardsGroup) target.getTargetObject(this.choice).getTarget()).removeFirstCard();
			}
			else{
				String tempTarget = null;
				if(this.abilitytarget.equals("opponent")){
					tempTarget="choiceopponent";
				}
				else if(this.abilitytarget.equals("your") || this.abilitytarget.equals("you")){
					tempTarget = "choiceyour";
				}
				
				nCard = (cardItem) target.getTargetObject(tempTarget).getTarget();
			}
			((CardsGroup) this.getTargetLocation(this.targetDestination,this.abilitytarget)).addCard(nCard);
		}
		GameController.getInstance().ulabelUpdate();
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
