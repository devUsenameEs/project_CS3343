package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.Test;

import core.Buff;
import core.JetFighter;
import globalData.Constant;
import globalData.Render;
import globalData.Updater;
import main.EnergyBar;
import main.GameUI;

public class Test_Buff extends TestBase{

	@Test
	public void testGetImage() throws IOException {
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
	public void testBuffUpdate() throws IOException {
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 1000;

		// Create a Buff object
		Buff buff = new Buff(gameUI, x, y);

		// Call the update method
		buff.update();

		// Verify that buff has moved out of the screen
		assertEquals(Render.containsRenderableObject(buff),false);
	}

	@Test
	public void testBuffCollision() throws IOException {
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		//The target JetFighter to be collide with Buff
		JetFighter jet = new JetFighter(gameUI, gameUI.keyHandler, new EnergyBar());

		// Define test values
		int x = Constant.screenWidth/2 - (Constant.tileSize/2);
		int y = Constant.screenHeight/2 + (Constant.tileSize*3);

		// Create a Buff object
		Buff buff = new Buff(gameUI, x, y);

		// Call the update method
		Updater.update();	//Add jet and buff into update_list for collision detection
		buff.update();

		// Check if jet's bulletType can change into Bullet_Buff after jet collides with buff
		assertEquals(jet.getBulletType(), "Bullet_Buff");
	}

	@Test
	public void TestBuffRenderable() throws IOException {
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
	public void TestBuff_Draw() throws IOException {
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