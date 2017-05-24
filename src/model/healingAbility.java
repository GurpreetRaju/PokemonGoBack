package model;

public class healingAbility implements ability {
	
	private String cardName;
	private int healingValue;
	
	public healingAbility(String name, int value){
		this.cardName = name;
		this.healingValue = value;
	}
	
	@Override
	public String getName() {
		return cardName;
	}

	@Override
	public void useAbility() {
		Debug.message(Turn.getInstance().getCurrentPlayer().name);
		Turn.getInstance().getCurrentPlayer().getActivePokemon().removeDamage(healingValue);
	}

}
