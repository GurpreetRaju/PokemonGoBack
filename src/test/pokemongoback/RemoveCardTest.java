package test.pokemongoback;
import controller.GameController;
import org.omg.Messaging.SyncScopeHelper;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.Pokemon;
import model.UserPlayer;
import model.ability;
import model.basicPokemon;
import model.cardItem;
import model.pokemonStage;
import view.PokemonCard;

public class RemoveCardTest {

	HBox panel=null;
	
	@Test
	public void test(){
		// first check if 	this.cardID = new Label(); in PokemonCard class is working or not?

		Node expected = null;
		ArrayList<ability> newAbilities1=new ArrayList<ability>();
		pokemonStage basic=new basicPokemon();

		cardItem card= new Pokemon(4, "Pikachu",  basic, 80, newAbilities1);
		
		GameController.getInstance().addCardToPanel(card, panel);
		System.out.println("before removing it should be 1---"+panel.getChildren().get(0));
		GameController.getInstance().removeCard(card,panel);
		System.out.println("after removing it should be 0---"+panel.getChildren().get(0));
		Node actual = panel.getChildren().get(0);
		//assertEquals(expected,actual);
		
		
	}
}

 