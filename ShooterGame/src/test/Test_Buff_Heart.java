package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.junit.Test;

import core.Buff_Heart;
import core.JetFighter;
import globalData.Constant;
import globalData.Updater;
import main.GameUI;

public class Test_Buff_Heart {

	@Test
	public void testGetImage() {
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 200;

		// Create a Buff_Heart object
		Buff_Heart buffHeart = new Buff_Heart(gameUI, x, y);

		// Verify that the x and y coordinates were set correctly
		assertEquals(x, buffHeart.getX(), 0.01);
		assertEquals(y, buffHeart.getY(), 0.01);

		// Verify that the type was set correctly
		assertEquals("heartbuff", buffHeart.getID());
	}
	
	@Test
	public void testBuffHeartUpdate() {
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 1000;

		// Create a Buff_Heart object
		Buff_Heart buffHeart = new Buff_Heart(gameUI, x, y);

		// Call the update method
		buffHeart.update();

		// Verify that buffHeart has moved out of the screen
		assertTrue(buffHeart.getY() >= Constant.screenHeight);
	}
	
	@Test
	public void testBuffHeartCollision() {
		// Create a GameUI object
		GameUI gameUI = new GameUI();
		
		//The target JetFighter to be collide with Buff
		JetFighter jet = new JetFighter(gameUI, gameUI.keyHandler);
		
		jet.reduceHP(2);

		// Define test values
		int x = Constant.screenWidth/2 - (Constant.tileSize/2);
		int y = Constant.screenHeight/2 + (Constant.tileSize*3);
		
		// Create a Buff_Heart object
		Buff_Heart buffHeart = new Buff_Heart(gameUI, x, y);

		// Call the update method
		Updater.update();	//Add jet and buffHeart into updateList for collision detection
		buffHeart.update();

		// Check whether jet's HP has changed from 4 to 5 after collision
		assertTrue(jet.getHP() == 5);
	}
	
	@Test
	public void TestBuffHeartRenderable() {
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 1000;

		// Create a Buff_Heart object
		Buff_Heart buffHeart = new Buff_Heart(gameUI, x, y);

		// Call the update method
		assertEquals(buffHeart, buffHeart.getRenderable());

	}
	
	@Test
	public void TestBuffHeart_Draw() {
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 1000;

		// Create a Buff_Heart object
		Buff_Heart buffHeart = new Buff_Heart(gameUI, x, y);

		// Set up a BufferedImage
		BufferedImage image = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);

		// Create a Graphics2D object
		Graphics2D g2 = image.createGraphics();

		// Call the draw method
		buffHeart.draw(g2);
	}

}
