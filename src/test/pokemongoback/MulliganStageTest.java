package test.pokemongoback;

import static org.junit.Assert.*;

import org.junit.Test;

import model.CardsGroup;
import model.cardItem;
import model.pokemonStage;

public class MulliganStageTest {

	private CardsGroup inhand;
	String expected="Basic";
	pokemonStage actual;
	private pokemonStage pStage;
	cardItem[] card;

	@Test
	public void test() {
		
	
	 card= ((CardsGroup) this.inhand).getCard();

		for(int i=0;i>((CardsGroup) this.inhand).getCard().length;i++){
			
		actual=pStage;
		assertEquals(expected,actual);
		}
		
		
				
	

}
}
