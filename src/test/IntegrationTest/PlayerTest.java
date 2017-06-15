package test.IntegrationTest;
import model.Player;
import model.UserPlayer;
import model.AIplayer;
import org.junit.experimental.categories.Category;
import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {
	
	private String string = "Flash";

	@Test
	public void integrationTest(){
		UserPlayer uip=new UserPlayer(string);
		AIplayer aip=new AIplayer(string);
		
		String expected="Flash";
		String actual1=uip.getName();
		String actual2=aip.getName();
		
		Assert.assertEquals(expected, actual1);
		Assert.assertEquals(expected, actual2);
		
	}

}
