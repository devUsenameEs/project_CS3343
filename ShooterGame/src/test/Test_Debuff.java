package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.Test;

import core.Debuff;
import core.JetFighter;
import globalData.Constant;
import globalData.Updater;
import main.GameUI;

public class Test_Debuff {

	@Test
	public void testGetImage() throws IOException{
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 200;

		// Create a Debuff object
		Debuff debuff = new Debuff(gameUI, x, y);

		// Verify that the x and y coordinates were set correctly
		assertEquals(x, debuff.getX(), 0.01);
		assertEquals(y, debuff.getY(), 0.01);

		// Verify that the type was set correctly
		assertEquals("Debuff", debuff.getID());
	}
	
	@Test
	public void testDebuffUpdate() throws IOException{
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 1000;

		// Create a Debuff object
		Debuff debuff = new Debuff(gameUI, x, y);

		// Call the update method
		debuff.update();

		// Verify that debuff has moved out of the screen
		assertTrue(debuff.getY() >= Constant.screenHeight);
	}
	
	@Test
	public void testDebuffCollision() throws IOException{
		// Create a GameUI object
		GameUI gameUI = new GameUI();
		
		//The target JetFighter to be collide with Debuff
		JetFighter jet = new JetFighter(gameUI, gameUI.keyHandler);

		// Define test values
		int x = Constant.screenWidth/2 - (Constant.tileSize/2);
		int y = Constant.screenHeight/2 + (Constant.tileSize*3);
		
		// Create a Debuff object
		Debuff debuff = new Debuff(gameUI, x, y);

		// Call the update method
		Updater.update();	//Add jet and debuff into update_list for collision detection
		debuff.update();

		// Check if jet's bulletType can change into Bullet_Debuff after jet collides with buff
		assertEquals(jet.getBulletType(), "Bullet_Debuff");
	}
	
	@Test
	public void TestDebuffRenderable() throws IOException{
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 1000;

		// Create a Debuff object
		Debuff debuff = new Debuff(gameUI, x, y);

		// Call the update method
		assertEquals(debuff, debuff.getRenderable());

	}
	
	@Test
	public void TestDebuff_Draw() throws IOException{
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 1000;

		// Create a Debuff object
		Debuff debuff = new Debuff(gameUI, x, y);

		// Set up a BufferedImage
		BufferedImage image = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);

		// Create a Graphics2D object
		Graphics2D g2 = image.createGraphics();

		// Call the draw method
		debuff.draw(g2);
	}

}
