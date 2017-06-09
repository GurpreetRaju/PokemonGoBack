package test.pokemongoback;

import java.util.ArrayList;

import model.Pokemon;
import model.ability;
import model.basicPokemon;
import model.pokemonStage;
import model.stageOnePokemon;

public class GetPokemon {
	
	
	ArrayList<ability> newAbilities=new ArrayList<ability>();
	pokemonStage basic=new basicPokemon();
	
	public void getPokemon(){
		
	Pokemon p= new Pokemon(2, "Pikachu", basic, 80, newAbilities);
	pokemonStage stageone = new stageOnePokemon("Pikachu");
	Pokemon e= new Pokemon(3, "Raichu", stageone, 90, newAbilities);
	
	}
}
