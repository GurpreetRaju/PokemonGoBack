package test.IntegrationTest;

import model.Deck;
import model.Player;
import model.cardItem;

import org.junit.experimental.categories.Category;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import controller.GameController;

public class DealCardsTest {
	
	@Test
	public void integrationTestDealCard(){
		GameController.getInstance().test=true;
		Player userPlayer=new Player("h");
		Deck deck=userPlayer.getDeck();
		cardItem[] cards=deck.getCard();
		
		Assert.assertEquals(cards[0], userPlayer.dealCard());
	}
	

}
