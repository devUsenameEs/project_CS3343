package source.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import source.core.Buff;
import source.globalData.Constant;
import source.main.GameUI;

public class Test_Buff {

	@Test
	public void testGetImage() {
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 200;

		// Create an Obstacle object
		Buff buff = new Buff(gameUI, x, y);

		// Verify that the x and y coordinates were set correctly
		assertEquals(x, buff.getX(), 0.01);
		assertEquals(y, buff.getY(), 0.01);

		// Verify that the type was set correctly
		assertEquals("buff", buff.getID());
	}
	
	@Test
	public void testBuffUpdate() {
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 1000;

		// Create an Obstacle object
		Buff buff = new Buff(gameUI, x, y);

		// Call the update method
		buff.update();

		// Verify that the enemy has moved out of the screen
		assertTrue(buff.getY() >= Constant.screenHeight);
	}
	
	@Test
	public void TestBuffRenderable() {
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 1000;

		// Create an Obstacle object
		Buff buff = new Buff(gameUI, x, y);

		// Call the update method
		assertEquals(buff, buff.getRenderable());

	}

}
