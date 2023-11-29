package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.junit.Test;

import core.Buff;
import core.JetFighter;
import globalData.Constant;
import globalData.Updater;
import main.GameUI;

public class Test_Buff {

	@Test
	public void testGetImage() {
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 200;

		// Create a Buff object
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

		// Create a Buff object
		Buff buff = new Buff(gameUI, x, y);

		// Call the update method
		buff.update();

		// Verify that the enemy has moved out of the screen
		assertTrue(buff.getY() >= Constant.screenHeight);
	}
	
	@Test
	public void testBuffCollision() {
		// Create a GameUI object
		GameUI gameUI = new GameUI();
		
		//The target JetFighter to be collide with Buff
		JetFighter jet = new JetFighter(gameUI, gameUI.keyHandler);

		// Define test values
		int x = Constant.screenWidth/2 - (Constant.tileSize/2);
		int y = Constant.screenHeight/2 + (Constant.tileSize*3) - 1;
		
		// Create a Buff object
		Buff buff = new Buff(gameUI, x, y);

		// Call the update method
		Updater.update();	//Add jet and buff into updateList for collision detection
		buff.update();

		// Check if jet's bulletType can change into Bullet_Buff after jet collides with buff
		assertEquals(jet.getBulletType(), "Bullet_Buff");
	}
	
	@Test
	public void TestBuffRenderable() {
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 1000;

		// Create a Buff object
		Buff buff = new Buff(gameUI, x, y);

		// Call the update method
		assertEquals(buff, buff.getRenderable());

	}
	
	@Test
	public void TestBuff_Draw() {
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 1000;

		// Create a Buff object
		Buff buff = new Buff(gameUI, x, y);

		// Set up a BufferedImage
		BufferedImage image = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);

		// Create a Graphics2D object
		Graphics2D g2 = image.createGraphics();

		// Call the draw method
		buff.draw(g2);
	}

}
