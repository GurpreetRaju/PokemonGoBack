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
	private boolean healed = false;
	private Retreat retreat;
	
	public Pokemon(int newId, String name, pokemonStage newPokemonStage, int newHp, ArrayList<ability> newAbilities, Retreat newRetreat){
		this.id = newId;
		this.cardName = name;
		this.pStage = newPokemonStage;
		this.hitpoints = newHp;
		this.attachedCards = new ArrayList<cardItem>();
		this.activeAbilities = new ArrayList<ability>();
		this.abilities = new ArrayList<ability>();
		this.abilities.addAll(newAbilities);
		this.retreat = newRetreat;
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
		this.healed = true;
	}
	
	public boolean isHealed(){
		return this.healed;
	}
	
	public void resetHealStatus(){
		this.healed = false;
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
	
	public void setStatus(String newstatus){
		this.status = newstatus;
	}
	
	public Retreat getRetreat(){
		return this.retreat;
	}
	
	public void attachCard(cardItem newCard){
		//GameController.getInstance().ulabelUpdate();
		this.attachedCards.add(newCard);
		
	}
	
	public void dettachCard(cardItem newCard){
		GameController.getInstance().ulabelUpdate();
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
		GameController.getInstance().ulabelUpdate();
		return cards.toArray(new cardItem[cards.size()]);
	}
	
	public void useAbility(ability uAbility){
		uAbility.useAbility();
		Turn.getInstance().changeTurn();
		GameController.getInstance().ulabelUpdate();
	}
	
	public int getAttachedCardsCount(){
		return this.attachedCards.size();
	}
	
	public cardItem[] getAttachedCards(){
		return this.attachedCards.toArray(new cardItem[this.attachedCards.size()]);
	}
	public boolean checkEnergyNeeds(ability a){
				
		int tempEnergyCount = 0;
		ArrayList<Energy> energyCard = new ArrayList<Energy>();
		for(cardItem card : this.getAttachedCards()){
			if(card.getClass() == Energy.class){
				energyCard.add((Energy) card);
			}
		}
		
		if(!(a.getEnergyInfoSize() <= energyCard.size())){
			Debug.message(a.getEnergyInfoSize()+" "+energyCard.size());
			
			return false;
		}
		
		for(EnergyNode e: a.getEnergyInfo()){
			if(!e.getEnergyType().equals("colorless")){
				for(Energy eCard : energyCard){
					Debug.message("1"+eCard.getName());
					if(eCard.getName().equals(e.getEnergyType())){
						tempEnergyCount++;
					}
				}
				if(tempEnergyCount!=e.getEnergyCount()){
					return false;
				}
			}
		}
		return true;
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
		GameController.getInstance().ulabelUpdate();
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

	
	public static void main(String[] arg){
//		pokemonStage newPokemonStage = new stageOnePokemon("Pikachu");
//		pokemonStage newPokemon2Stage = new basicPokemon();
//		ArrayList<ability> newAbilities = new ArrayList<ability>();
//		ArrayList<EnergyNode> energyRequired = new ArrayList<EnergyNode>();
//		energyRequired.add(new EnergyNode(new Energy("colorless"),6));
//		newAbilities.add(new damageAbility("Thunder Bolt", 20, energyRequired, "opponentactive", null));
//		Pokemon pikachu = new Pokemon(2, "Pikachu", newPokemon2Stage, 80, newAbilities);
		
		Deck deck = new Deck(2);
		deck.readFile();
		CardParser cparser = new CardParser(deck);
		String[] card = ("Pikachu:pokemon:cat:basic:cat:lightning:60:retreat:cat:colorless:1:attacks:cat:colorless:1:5,cat:colorless:2:6").split(":"); 
		
		Pokemon pikachu = (Pokemon) cparser.createPokemon(1, card);
		for(ability a:pikachu.getAbilities()){
			System.out.print(pikachu.getStage() +" "+ pikachu.getName() + " " + pikachu.getDamage() +" "+ a.getName()+" ");
			for(EnergyNode node:a.getEnergyInfo()){
				Debug.message(node.getEnergyType() + " " + node.getEnergyCount());
			}			
		}
	}

	public boolean dettachCardType(String cname, int count) {
		for(int i=0; i<count; i++){
			for(cardItem card : this.attachedCards){
				if(card.getName().equals(cname)){
					this.attachedCards.remove(card);
					return true;
				}
			}
		}
		return false;
	}
	
}
