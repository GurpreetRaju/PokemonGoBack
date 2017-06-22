package test.stubs;

import java.util.ArrayList;

public class CompositeAbility extends ability{

	private ArrayList<ability> abilities;
	
	public CompositeAbility(ArrayList<ability> newAbility){
		this.abilities = newAbility;
	}
	
	public void add(ability newAbility){
		this.abilities.add(newAbility);
	}
	
	public void remove(ability newAbility){
		this.abilities.remove(newAbility);
	}
	
	public void useAbility() {
		for(ability a : this.abilities){
			a.useAbility();
		}
	}
	
	public boolean equals(Object o) {
		if(o instanceof CompositeAbility && this.name.equals(((CompositeAbility) o).name)){
			return true;
		}
		return false;
	}

}
