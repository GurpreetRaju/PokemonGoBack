package test.pokemongoback;
import controller.GameController;
import org.omg.Messaging.SyncScopeHelper;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import model.Pokemon;
import model.UserPlayer;
import model.ability;
import model.basicPokemon;
import model.cardItem;
import model.pokemonStage;

public class RemoveCardTest {

	HBox panel;
	
	@Test
	public void test(){
		
		Node expected = null;
		ArrayList<ability> newAbilities1=new ArrayList<ability>();
		pokemonStage basic=new basicPokemon();
		Pokemon card= new Pokemon(2, "Pikachu",  basic, 80, newAbilities1);
		GameController.getInstance().addCardToPanel(card, panel);
		System.out.println("before removing it should be 1---"+panel.getChildren().get(0));
		GameController.getInstance().removeCard(card,panel);
		System.out.println("after removing it should be 0---"+panel.getChildren().get(0));
		Node actual = panel.getChildren().get(0);
		assertEquals(expected,actual);
		
		
	}
}

 