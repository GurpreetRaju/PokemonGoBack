package test.stubs;

import java.util.ArrayList;

import test.stubs.*;
import test.stubs.PokemonCard;

public class Pokemon implements cardItem{
	private int id;
	private String cardName;
	private int hitpoints;
	private pokemonStage pStage;
	private int damage = 0;
	private String state = "deck";
	private ArrayList<ability> abilities;
	private String status = "normal";
	private ArrayList<cardItem> attachedCards;
	private ArrayList<ability> activeAbilities;
	private static ArrayList<ability> addAbilities; //trigger abilities
	private PokemonCard uiCard;
	
	public Pokemon(int newId, String name, pokemonStage newPokemonStage, int newHp, ArrayList<ability> newAbilities){
		this.id = newId;
		this.cardName = name;
		this.pStage = newPokemonStage;
		this.hitpoints = newHp;
		this.attachedCards = new ArrayList<cardItem>();
		this.activeAbilities = new ArrayList<ability>();
		this.abilities = newAbilities;
	}
	
	public void addDamage(int newDamage){
		this.damage += newDamage;
		if(this.damage==this.hitpoints || this.damage>=this.hitpoints){
			this.state = "knockedOut";
			GameController.getInstance().knockout();
		}
	}
	
	public void removeDamage(int newHealing){
		if(this.damage<newHealing){
			this.damage = 0;
		}
		else{
			this.damage -= newHealing;
		}
	}
	
	public void setState(String newState){
		this.state = newState;
	}
	
	public String getName(){
		return this.cardName;
	}
	
	public int getID(){
		return this.id;
	}
	
	public String getStage(){
		return this.pStage.getStage();
	}
	
	public String getState(){
		return this.state;
	}
	
	public int getDamage(){
		return this.damage;
	}
	
	public ability[] getAbilities(){
		return this.abilities.toArray(new ability[this.abilities.size()]);
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public void attachCard(test.stubs.cardItem cardItem){
		this.attachedCards.add((test.stubs.cardItem) cardItem);
	}
	
	public void dettachCard(cardItem newCard){
		this.attachedCards.remove(newCard);
	}
	
	public cardItem[] dettachCardType(Class<? extends cardItem> newtype, int i){
		ArrayList<cardItem> cards = new ArrayList<cardItem>();
		for(cardItem tempcard : this.attachedCards){
			if(i==0){
				break;
			}
			if(tempcard.getClass()==newtype){
				cards.add(tempcard);
				this.attachedCards.remove(tempcard);
				i--;
			}
		}
		return cards.toArray(new cardItem[cards.size()]);
	}
	
	public void useAbility(ability uAbility){
		uAbility.useAbility();
		Turn.getInstance().changeTurn();
	}
	
	public int getAttachedCardsCount(){
		return this.attachedCards.size();
	}
	
	public cardItem[] getAttachedCards(){
		return this.attachedCards.toArray(new cardItem[this.attachedCards.size()]);
	}
	public int totalEnergyRequired(){
		int totalEnergy = 0;
		for(ability ablt : this.getAbilities()){
			int temp = ((damageAbility) ablt).getEnergyInfo().size();
			if(temp>totalEnergy){
				totalEnergy = temp;
			}
		}
		return totalEnergy;
	}
	
	public void evolve(Pokemon basicCard){
		//this.pStage.evolve(basicCard);
		for(ability a:basicCard.getActiveAbilities())
			this.addActiveAbility(a);
	}
	
	public boolean equals(Object o){
		if(o instanceof Pokemon){
			Pokemon tempP = (Pokemon) o;
			if(this.id == tempP.id){
				return true;
			}
		}
		return false;
	}

	public int getHP() {
		return this.hitpoints;
	}

	public String getBasePokemonName() {
		return ((stageOnePokemon) this.pStage).getBasicPokemonName();
	}

	public void attachCard(cardItem[] cards) {
		for(cardItem card: cards){
			this.attachCard(card);
		}		
	}
	
	public void addObserver(PokemonCard newCard){
		this.uiCard = newCard;
	}
	
	public void removeAbility(ability newAbility){
		this.activeAbilities.remove(newAbility);
	}
	
	public void addActiveAbility(ability newAbility){
		this.activeAbilities.add(newAbility);
	}
	
	public ability[] getActiveAbilities(){
		return this.activeAbilities.toArray(new ability[this.activeAbilities.size()]);
	}
	
	public String getAbilityIndex(ability newAbility){
		for(int i=0;i<this.activeAbilities.size();i++){
			if(this.activeAbilities.get(i) == newAbility){
				return Integer.toString(i);
			}
		}
		return null;
	}
	
//	public static void getTurnEndAbilities(Player player)
//	{
//		for( ability a: player.getActivePokemon().getActiveAbilities())
//		{
//			addAbilities.add(a);
//			if()
//		}
//	}
}
