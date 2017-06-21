package test.pokemongoback;

import static org.junit.Assert.*;

import org.junit.Test;

import test.stubs.CardsGroup;
import model.Search;

public class SearchEqualsTest {
	
		Boolean actual;
		Boolean expected;
		
		@Test
		public void test() {
			Search o1=new Search("search", null, null, null, null, 10);
			CardsGroup cg=new CardsGroup();
			expected=false;
			actual=o1.equals(cg);
			
			assertEquals(expected,actual);
		}
		
		@Test
		public void test1() {
			Search o2=new Search("search", null, null, null, "ability", 10);
			actual=o2.equals(o2);
			expected=true;
			assertEquals(expected,actual);
			}
	}


