package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import core.Bullet_buff_2x;
import core.Entity;
import main.GameUI;

public class Test_Bullet_buff_2x {
	@Test
	public void testGetImage() throws IOException{
		GameUI gameUI = new GameUI();
		Entity entity = new Entity(gameUI);

		// Define test values
		int x = 100;
		int y = 200;

		// Create a Buff_Heart object
		Bullet_buff_2x bullet_buff_2x = new Bullet_buff_2x(gameUI, x, y, entity);

		// Verify that the x and y coordinates were set correctly
		assertEquals(x, bullet_buff_2x.getX(), 0.01);
		assertEquals(y, bullet_buff_2x.getY(), 0.01);

		// Verify that the type was set correctly
		assertEquals("bullet_buff_2x", bullet_buff_2x.getID());
	}
}
