package model;

import controller.GameController;

public class drawAbility extends ability {
	private int drawnumber;
	
	public drawAbility(String newname,int newDrawnumber, String newAbilitytarget){
		this.drawnumber = newDrawnumber;
		this.name = newname;
		this.abilitytarget = newAbilitytarget;
	}

	public void useAbility() {
		if(this.abilitytarget == null){
			this.abilitytarget="you";
		}
		Player player = (Player) target.getTargetObject(this.abilitytarget).getTarget();
		Debug.message(player.getName());
		GameController.getInstance().addCardsToPanel(player.dealMultipleCards(this.drawnumber), GameController.getInstance().getHand(player));
	}
	
	public boolean equals(Object o) {
		return false;
	}

}
