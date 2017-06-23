package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import controller.GameController;
import javafx.scene.layout.HBox;
import model.Pokemon;
import model.Search;
import model.Trainer;
import model.ability;
import model.cardItem;
import model.healingAbility;
import model.swapAbility;

public class addCardsToAIPanelTest {
	
		cardItem expected;
		cardItem actual;
		HBox panel;
		@Test
		public void test(){
			
			ArrayList<ability> abilities = new ArrayList<ability>();
			
			Pokemon p1=new Pokemon(0, null, null, 0, abilities, null);
			healingAbility h1=new healingAbility("healing", 0, null);
			swapAbility s1=new swapAbility("swap", "Pikachu", "Raichu");
			Search s2=new Search("search", null, null, null, null, 0);
			
			abilities.add(h1);
			abilities.add(s1);
			abilities.add(s2);

	//	cardItem trainercard=new Trainer(32,"Potion", null, null);
		cardItem pokemon1=new Pokemon(80, "Pikachu", null, 0, abilities, null);

		//ArrayList<cardItem> pokemoncard = new ArrayList<cardItem>();
		//pokemoncard.add(null);
	//	pokemoncard.add(trainercard);
		//pokemoncard.add(pokemon1);
		
		GameController.getInstance().addCardToPanel(pokemon1, panel);
			
			
		cardItem card=(cardItem) panel.getChildren();
		
			assertEquals(pokemon1,card);
		
	
		
		
	}
}
