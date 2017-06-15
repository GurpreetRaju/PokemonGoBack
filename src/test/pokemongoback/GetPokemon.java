package test.pokemongoback;

import java.util.ArrayList;

import model.Pokemon;
import model.ability;
import model.basicPokemon;
import model.pokemonStage;
import model.stageOnePokemon;

public class GetPokemon {
	
	public static Pokemon get(){
		ArrayList<ability> newAbilities=new ArrayList<ability>();
		pokemonStage basic=new basicPokemon();
		Pokemon p= new Pokemon(2, "Pikachu", basic, 80, newAbilities);
	
		return p;
	}
}
