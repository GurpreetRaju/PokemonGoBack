package model;

import controller.GameController;

public class drawAbility extends ability {
	private int drawnumber;
	
	public drawAbility(int drawnumber){
		this.drawnumber = drawnumber;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void useAbility() {
		// TODO Auto-generated method stub
		Player player = (Player) target.getTargetObject("you").getTarget();
		//CardsGroup hand = (CardsGroup) player.getInhand();
		CardsGroup deck = (CardsGroup) player.getDeckCard();
		//player.dealMultipleCards(drawnumber);
		//deck.removeCard(newCard);
		GameController.getInstance().addCardsToPanel(player.dealMultipleCards(drawnumber), GameController.getInstance().getHand(player));
	}

}
