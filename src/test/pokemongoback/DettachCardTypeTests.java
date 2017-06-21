package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Energy;
import model.Pokemon;
import model.Trainer;
import model.ability;
import model.basicPokemon;
import model.cardItem;
import model.pokemonStage;

public class DettachCardTypeTests {

		
		ArrayList<ability> newAbilities=new ArrayList<ability>();
		pokemonStage basic=new basicPokemon();
		Pokemon pk = new Pokemon(2, "Pikachu", basic, 80, newAbilities);
		
		
		@Test
		public void test1() {
		// test if  card have 0 attached energy card,then detach 1 card
				// result should be 0.
		int expected1=0;
		pk.dettachCardType(Energy.class, 1);
		assertEquals(expected1,pk.getAttachedCardsCount());
		}
		
		@Test
		public void test2() {
			
		cardItem newCard = new Energy("Lighting", 2);
		cardItem newCard1 = new Energy("Colorless", 3);
		cardItem newCard2 = new Energy("Water", 4);
		cardItem newCard3 = new Energy("Fight", 5);
		cardItem newCard4 = new Energy("Psychic", 6);
		
		pk.attachCard(newCard);
		pk.attachCard(newCard1);
		pk.attachCard(newCard2);
		pk.attachCard(newCard3);
		pk.attachCard(newCard4);
		
		
		// test if  card have 5 attached energy cards,then detach 5 card
		// result should be 0.
		int expected3=0;
		pk.dettachCardType(Energy.class, 5);
		assertEquals(expected3,pk.getAttachedCardsCount());
		}
		
		@Test
		public void test4() {
		// test if card have 0 attached trainer card, then detach 1 card
				// result should be 0.
		int expected4=0;
		pk.dettachCardType(Trainer.class, 1);
		assertEquals(expected4,pk.getAttachedCardsCount());
		}
		
		
		@Test
		public void test5() {
			
		ability ability = null;
		cardItem newCard5 = new Trainer(22,"Misty's Determination", null,ability);
		cardItem newCard6 = new Trainer(23,"Clemont",null ,ability);
		cardItem newCard7 = new Trainer(24,"Potion", null,ability);
		cardItem newCard8 = new Trainer(25,"Tierno", null,ability);
		cardItem newCard9 = new Trainer(26,"Pokémon Center Lady", null,ability);
		
		pk.attachCard(newCard5);
		pk.attachCard(newCard6);
		pk.attachCard(newCard7);
		pk.attachCard(newCard8);
		pk.attachCard(newCard9);
		
		// test if card have attached 5 trainer cards, detach 1 card
				// result should be 4
		int expected5=4;
		pk.dettachCardType(Trainer.class, 1);
		System.out.println("Trainer ==4"+pk.getAttachedCardsCount());
		assertEquals(expected5,pk.getAttachedCardsCount());
		}
		
		
		@Test
		public void test6() {
		// test if card have attached 5 trainer cards, detach 5 card
		// result should be 0
		int expected6=0;
		pk.dettachCardType(Trainer.class, 5);
		assertEquals(expected6,pk.getAttachedCardsCount());
		
	}

}
