package model;

import java.awt.Composite;
import java.util.ArrayList;

import controller.GameController;
import view.PokemonCard;

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
	private PokemonCard uiCard;
	
	public Pokemon(int newId, String name, pokemonStage newPokemonStage, int newHp, ArrayList<ability> newAbilities){
		this.id = newId;
		this.cardName = name;
		this.pStage = newPokemonStage;
		this.hitpoints = newHp;
		this.attachedCards = new ArrayList<cardItem>();
		this.activeAbilities = new ArrayList<ability>();
		this.abilities = new ArrayList<ability>();
		this.abilities.addAll(newAbilities);
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
	
	public void attachCard(cardItem newCard){
		this.attachedCards.add(newCard);
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
			int temp = 0;
			if(ablt instanceof damageAbility){
				temp = ((damageAbility) ablt).getEnergyInfo().size();
			}
			else if(ablt instanceof CompositeAbility){
				temp = ((CompositeAbility) ablt).getEnergyInfo().size();
			}
			if(temp>totalEnergy){
				totalEnergy = temp;
			}
		}
		return totalEnergy;
	}
	
	public void evolve(Pokemon basicCard){
		this.pStage.evolve(basicCard);
		if(basicCard.getActiveAbilities()!=null && !basicCard.getActiveAbilities().isEmpty()){
			for(ability a : basicCard.getActiveAbilities()){
				this.addActiveAbility(a);
			}
		}
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
	
//	public static void main(String[] arg){
//		pokemonStage newPokemonStage = new stageOnePokemon("Pikachu");
//		pokemonStage newPokemon2Stage = new basicPokemon();
//		ArrayList<ability> newAbilities = new ArrayList<ability>();
//		Energy[] energyRequired = {new Energy("Lighting",6)};
//		newAbilities.add(new damageAbility("Thunder Bolt", 20, energyRequired, "Pokemon"));
//		Pokemon pikachu = new Pokemon(2, "Raichu", newPokemonStage, 80, newAbilities);
//		ability[] ability = pikachu.getAbilities();
//		
//		System.out.println(pikachu.getStage() +" "+ pikachu.getName() + " " + pikachu.getDamage() +" "+ ability[0].getClass().getName());
//		
//		Pokemon meow = new Pokemon(1, "Meow", newPokemon2Stage, 80, newAbilities); 
//		System.out.print(meow.getStage());
//	}

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
	
	public ArrayList<ability> getActiveAbilities(){
		return this.activeAbilities;
	}
	
	public String getAbilityIndex(ability newAbility){
		for(int i=0;i<this.activeAbilities.size();i++){
			if(this.activeAbilities.get(i) == newAbility){
				return Integer.toString(i);
			}
		}
		return null;
	}
	
	public static void getTurnEndAbilities(Player player)
	{
		if(player.getActivePokemon().getActiveAbilities().size() != 0)
		{
			for( ability a: player.getActivePokemon().getActiveAbilities())
			{
				Debug.message("get turnend abiltites");
				if(a.getTriggerCondition() == "turnend")
				{
					a.useAbility();	
				}
			}
		}
	}

	public int getAttachedCardsCount(Class<?> classtype) {
		int i=0;
		for(cardItem tempcard : this.attachedCards){
			if(tempcard.getClass()==classtype){
				i++;
			}
		}
		return i;
	}
}
