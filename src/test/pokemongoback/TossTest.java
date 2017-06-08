package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class TossTest {

	@Test
	public void testRandomnNumber() {
		
		Random random = new Random();
        int number = random.nextInt(2)+1;
		
	assertTrue(number==1||number==2);
		
	}

}
