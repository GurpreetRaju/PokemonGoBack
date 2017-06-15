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
	public void TestRemoveCard(){
		Node expected = null;
		GameController.getInstance().addCardsToPanel(null, panel);
		Node actual = panel.getChildren().get(0);
		assertEquals(expected,actual);
	}
}

 