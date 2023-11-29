package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.xml.datatype.DatatypeConstants.Field;

import org.junit.Test;

import core.Enemy;
import core.JetFighter;
import core.Obstacle;
import globalData.Constant;
import globalData.Renderable;
import globalData.Updateable;
import main.GameUI;

public class Test_Obstacle {
	@Test
	public void testObstacleGetImage1() throws IOException {
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 200;
		String type = "obstacle01";

		// Create an Obstacle object
		Obstacle obstacle = new Obstacle(gameUI, x, y, type);

		// Verify that the x and y coordinates were set correctly
		assertEquals(x, obstacle.getX(), 0.01);
		assertEquals(y, obstacle.getY(), 0.01);

		// Verify that the type was set correctly
		assertEquals("obstacle", obstacle.getID());

	}

	@Test
	public void testObstacleGetImage2() throws IOException {
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 200;
		String type = "obstacle02";

		// Create an Obstacle object
		Obstacle obstacle = new Obstacle(gameUI, x, y, type);

		// Verify that the x and y coordinates were set correctly
		assertEquals(x, obstacle.getX(), 0.01);
		assertEquals(y, obstacle.getY(), 0.01);

		// Verify that the type was set correctly
		assertEquals("obstacle", obstacle.getID());

	}

	@Test
	public void testObstacleGetImage3() throws IOException {
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 200;
		String type = "obstacle03";

		// Create an Obstacle object
		Obstacle obstacle = new Obstacle(gameUI, x, y, type);

		// Verify that the x and y coordinates were set correctly
		assertEquals(x, obstacle.getX(), 0.01);
		assertEquals(y, obstacle.getY(), 0.01);

		// Verify that the type was set correctly
		assertEquals("obstacle", obstacle.getID());

	}

	@Test
	public void testObstacleUpdate() throws IOException {
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 1000;
		String type = "obstacle03";

		// Create an Obstacle object
		Obstacle obstacle = new Obstacle(gameUI, x, y, type);

		// Call the update method
		obstacle.update();

		// Verify that the enemy has moved out of the screen
		assertTrue(obstacle.getY() >= Constant.screenHeight);
	}

	@Test
	public void TestObstacleRenderable() throws IOException {
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 1000;
		String type = "obstacle03";

		// Create an Obstacle object
		Obstacle obstacle = new Obstacle(gameUI, x, y, type);

		// Call the update method
		assertEquals(obstacle, obstacle.getRenderable());

	}

	@Test
	public void TestObstacleGetWidth() throws IOException {
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 1000;
		String type = "obstacle03";

		// Create an Obstacle object
		Obstacle obstacle = new Obstacle(gameUI, x, y, type);
		assertEquals(Constant.tileSize, obstacle.getWidth(), 0);
	}

	@Test
	public void TestObstacleGetHeight() throws IOException {
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 1000;
		String type = "obstacle03";

		// Create an Obstacle object
		Obstacle obstacle = new Obstacle(gameUI, x, y, type);
		assertEquals(Constant.tileSize, obstacle.getHeight(), 0);
	}

	@Test
	public void TestObstacle_Draw() throws IOException {
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 1000;
		String type = "obstacle03";

		// Create an Obstacle object
		Obstacle obstacle = new Obstacle(gameUI, x, y, type);

		// Set up a BufferedImage
		BufferedImage image = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);

		// Create a Graphics2D object
		Graphics2D g2 = image.createGraphics();

		// Call the draw method
		obstacle.draw(g2);
	}

}