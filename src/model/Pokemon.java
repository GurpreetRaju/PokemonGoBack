package model;

import java.util.ArrayList;

import controller.GameController;

public class Pokemon implements cardItem{
	private int id;
	private String cardName;
	private int hitpoints;
	private pokemonStage pStage;
	private int damage = 0;
	private String state = "deck";
	private ArrayList<ability> abilities;
	private String status;
	private ArrayList<cardItem> attachedCards;
	
	public Pokemon(int newId, String name, pokemonStage newPokemonStage, int newHp, ArrayList<ability> newAbilities){
		this.id = newId;
		this.cardName = name;
		this.pStage = newPokemonStage;
		this.hitpoints = newHp;
		this.attachedCards = new ArrayList<cardItem>();
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
	
	public void attachCard(cardItem newCard){
		this.attachedCards.add(newCard);
	}
	
	public void dettachCard(cardItem newCard){
		this.attachedCards.remove(newCard);
	}
	
	public void dettachCardType(Class<? extends cardItem> newtype, int i){
		while(i>0){
			for(cardItem tempcard : this.attachedCards){
				if(tempcard.getClass()==newtype){
					this.attachedCards.remove(tempcard);
					break;
				}
			}
			i--;
		}
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
			int temp = ((damageAbility) ablt).getEnergyInfo().length;
			if(temp>totalEnergy){
				totalEnergy = temp;
			}
		}
		return totalEnergy;
	}
	
	public void evolve(Pokemon basicCard){
		this.pStage.evolve(basicCard);
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
	
	public static void main(String[] arg){
		pokemonStage newPokemonStage = new stageOnePokemon("Pikachu");
		pokemonStage newPokemon2Stage = new basicPokemon();
		ArrayList<ability> newAbilities = new ArrayList<ability>();
		Energy[] energyRequired = {new Energy("Lighting",6)};
		newAbilities.add(new damageAbility("Thunder Bolt", 20, energyRequired, "Pokemon"));
		Pokemon pikachu = new Pokemon(2, "Raichu", newPokemonStage, 80, newAbilities);
		ability[] ability = pikachu.getAbilities();
		
		System.out.println(pikachu.getStage() +" "+ pikachu.getName() + " " + pikachu.getDamage() +" "+ ability[0].getClass().getName());
		
		Pokemon meow = new Pokemon(1, "Meow", newPokemon2Stage, 80, newAbilities); 
		System.out.print(meow.getStage());
	}

	public int getHP() {
		// TODO Auto-generated method stub
		return this.hitpoints;
	}

	public String getBasePokemonName() {
		return ((stageOnePokemon) this.pStage).getBasicPokemonName();
	}
}
