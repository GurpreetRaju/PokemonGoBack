package test.pokemongoback;
import controller.GameController;
import org.omg.Messaging.SyncScopeHelper;
import static org.junit.Assert.*;

import org.junit.Test;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import model.UserPlayer;
import model.cardItem;

public class RemoveCardTest {

	HBox panel;
	
	@Test
	public void test(){
		Node expected = null;
		GameController.getInstance().addCardsToPanel(null, panel);
		System.out.println(panel.getChildren().get(0));
		GameController.getInstance().removeCard(null,panel);
		System.out.println(panel.getChildren().get(0));
		Node actual = panel.getChildren().get(0);
		assertEquals(expected,actual);
	}
}

 