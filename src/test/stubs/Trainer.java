package test.stubs;

import model.ability;
import model.cardItem;

public class Trainer implements cardItem{

	private String cardName;
	private String category;
	private int id;
	private ability cardAbility;
	
	public Trainer(int id, String name, String newCategory,ability newAbility){
		this.cardName = name;
		this.category = newCategory;
		this.id = id;
		this.cardAbility = newAbility;
	}
	
	@Override
	public String getName() {
		return this.cardName;
	}
	
	public int getID(){
		return this.id;
	}
	
	public ability getAbility(){
		return this.cardAbility;
	}
	
	public String getCategory(){
		return this.category;
	}
}