package test.stubs;

import test.stubs.GameController;

public class drawAbility extends ability {
	private int drawnumber;
	
	public drawAbility(String newname,int drawnumber, String abilitytarget){
		this.drawnumber = drawnumber;
		this.name = newname;
		this.abilitytarget = abilitytarget;
	}

	public void useAbility() {
		if(abilitytarget.equals(null)){
			abilitytarget="you";
		}
		Player player = (Player) target.getTargetObject(abilitytarget).getTarget();
		CardsGroup deck = (CardsGroup) player.getDeckCard();
		GameController.getInstance().addCardsToPanel(player.dealMultipleCards(drawnumber), GameController.getInstance().getHand(player));
	}
	
	public boolean equals(Object o) {
		return false;
	}

}