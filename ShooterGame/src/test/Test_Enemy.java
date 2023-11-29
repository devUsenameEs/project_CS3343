package test;

import static org.junit.Assert.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import core.Enemy;
import core.JetFighter;
import globalData.Constant;
import globalData.Render;
import globalData.Renderable;
import globalData.Updateable;
import globalData.Updater;
import main.GameUI;

public class Test_Enemy extends TestBase{

	@Test
	public void testEnemyConstruction1() throws IOException {
		GameUI gameUI = new GameUI();
		int x = 100;
		int y = 200;
		String type = "enemy01";

		Enemy enemy = new Enemy(gameUI, x, y, type);

//        assertEquals(gameUI, enemy.getGameUI());
		assertEquals(x, enemy.getX(), 0);
		assertEquals(y, enemy.getY(), 0);
	}

	@Test
	public void testEnemyConstruction2() throws IOException {
		GameUI gameUI = new GameUI();
		int x = 100;
		int y = 200;
		String type = "enemy02";

		Enemy enemy = new Enemy(gameUI, x, y, type);

//        assertEquals(gameUI, enemy.getGameUI());
		assertEquals(x, enemy.getX(), 0);
		assertEquals(y, enemy.getY(), 0);
	}

	@Test
	public void testEnemyUpdate() throws IOException {
		GameUI gameUI = new GameUI();
		Enemy enemy = new Enemy(gameUI, 100, 200, "enemy01");

		double initialY = enemy.getY();
		double speed = 1;

		try {
			enemy.update();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(initialY + speed, enemy.getY(), 0);
	}

	@Test
	public void testEnemyGetWidth() throws IOException {
		GameUI gameUI = new GameUI();
		Enemy enemy = new Enemy(gameUI, 100, 200, "enemy01");

		assertEquals(32, enemy.getWidth(), 0);
	}

	@Test
	public void testEnemyGetHeight() throws IOException {
		GameUI gameUI = new GameUI();
		Enemy enemy = new Enemy(gameUI, 100, 200, "enemy01");

		assertEquals(32, enemy.getHeight(), 0);
	}
	@Test
	public void testEnemyGetspeed() throws IOException {
		GameUI gameUI = new GameUI();
		Enemy enemy = new Enemy(gameUI, 100, 200, "enemy01");

		assertEquals(1, enemy.getSpeed(), 0);
	}

    @Test
	public void testEnemyDraw() throws IOException {
        // Create an Enemy object
        GameUI gameUI = new GameUI();
        int x = 100;
        int y = 200;
        String type = "enemy01";
        Enemy enemy = new Enemy(gameUI, x, y, type);

        // Set up a BufferedImage
        BufferedImage image = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        
        // Create a Graphics2D object
        Graphics2D g2 = image.createGraphics();

        // Call the draw method
        enemy.draw(g2);
	}
	@Test
	public void testEnemyReduceHP() throws IOException {
		GameUI gameUI = new GameUI();
		Enemy enemy = new Enemy(gameUI, 100, 200, "enemy01");

		// Set initial HP
		enemy.addHP(1);

		// Reduce HP by a specific value
		double reduction = 1;
		enemy.reduceHP(reduction);

		// Verify that the HP is reduced by the expected amount
		double expectedHP = 1 - reduction;
		assertEquals(expectedHP, enemy.getHP(), 1);
	}

	@Test
	public void testEnemyGetID() throws IOException {
		GameUI gameUI = new GameUI();
		Enemy enemy = new Enemy(gameUI, 100, 200, "enemy01");

		String expectedID = "enemy";
		assertEquals(expectedID, enemy.getID());
	}

	@Test
	public void testEnemyCheckIfDie() throws IOException {
		// Create an Enemy object
		GameUI gameUI = new GameUI();
		int x = 100;
		int y = 200;
		String type = "enemy01";
		Enemy enemy = new Enemy(gameUI, x, y, type);

		// Set HP to a value indicating death
		enemy.reduceHP(1);

		// Call the checkIfDie method
		int result = enemy.checkIfDie();

		// Verify that the method returns the expected value
		assertEquals(100, result);
	}
	@Test
	public void testEnemyCheckIfNotDie() throws IOException {
		// Create an Enemy object
		GameUI gameUI = new GameUI();
		int x = 100;
		int y = 200;
		String type = "enemy01";
		Enemy enemy = new Enemy(gameUI, x, y, type);

		// Set HP to a value indicating death
		enemy.reduceHP(0);

		// Call the checkIfDie method
		int result = enemy.checkIfDie();

		// Verify that the method returns the expected value
		assertEquals(-1, result);
	}
	@Test
	public void testGetRenderable() throws IOException {
		// Create an Enemy object
		GameUI gameUI = new GameUI();
		int x = 100;
		int y = 200;
		String type = "enemy01";
		Enemy enemy = new Enemy(gameUI, x, y, type);

		// Verify that the returned Renderable object is the same instance as the Enemy
		// object
		assertSame(enemy, enemy.getRenderable());
	}

	@Test
	public void testEnemyUpdateRemoveObj() throws IOException {
		// Create an Enemy object
		GameUI gameUI = new GameUI();
		int x = 100;
		int y = 1000; // Set y to a large number to simulate being out of the screen
		String type = "enemy01";
		Enemy enemy = new Enemy(gameUI, x, y, type);

		// Call the update method
		try {
			enemy.update();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Verify that the enemy has moved out of the screen
		assertTrue(enemy.getY() >= Constant.screenHeight);
	}


}
