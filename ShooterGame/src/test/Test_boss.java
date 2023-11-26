package test_boss;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.xml.datatype.DatatypeConstants.Field;

import org.junit.Test;

import core.Enemy_Boss;
import main.GameUI;

public class Test_boss {
	@Test
	public void testEnemyBossConstruction() {
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Create an Enemy_Boss object
		int x = 400;
		int y = 200;
		String type = "enemy02";
		Enemy_Boss enemyBoss = new Enemy_Boss(gameUI, x, y, type);

		// Verify that the type was set correctly
		assertEquals("enemy", enemyBoss.getID());

	}

	@Test
	public void testEnemyIfDie() {

		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Create an Enemy_Boss object
		int x = 400;
		int y = 200;
		String type = "enemy02";
		Enemy_Boss enemyBoss = new Enemy_Boss(gameUI, x, y, type);

		enemyBoss.reduceHP(30);

		// Call the checkIfDie method
		int result = enemyBoss.checkIfDie();

		// Verify that the method returns the expected value
		assertEquals(3343, result);
	}

	@Test
	public void testEnemyIfNotDie() {

		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Create an Enemy_Boss object
		int x = 400;
		int y = 200;
		String type = "enemy02";
		Enemy_Boss enemyBoss = new Enemy_Boss(gameUI, x, y, type);

		enemyBoss.reduceHP(1);

		// Call the checkIfDie method
		int result = enemyBoss.checkIfDie();

		// Verify that the method returns the expected value
		assertEquals(-1, result);
	}

	@Test
	public void testUpdate_BossSpeedX_LargerThan0()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 200;
		String type = "enemy02";

		// Create an Enemy_Boss object
		Enemy_Boss enemyBoss = new Enemy_Boss(gameUI, x, y, type);

		// Use reflection to set the value of speed_x to a positive value

		java.lang.reflect.Field speedXField = Enemy_Boss.class.getDeclaredField("speed_x");
		speedXField.setAccessible(true);
		speedXField.setDouble(enemyBoss, 1);

		// Call the update method
		enemyBoss.update();

		// Verify that the x-coordinate has increased
		assertEquals(x + 1, enemyBoss.getX(), 2);
	}

	@Test
	public void testUpdate_BossSpeedX_SmallerThan0()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 200;
		String type = "enemy02";

		// Create an Enemy_Boss object
		Enemy_Boss enemyBoss = new Enemy_Boss(gameUI, x, y, type);

		// Use reflection to set the value of speed_x to a positive value

		java.lang.reflect.Field speedXField = Enemy_Boss.class.getDeclaredField("speed_x");
		speedXField.setAccessible(true);
		speedXField.setDouble(enemyBoss, -1);

		// Call the update method
		enemyBoss.update();

		// Verify that the x-coordinate has increased
		assertEquals(x - 1, enemyBoss.getX(), -1);
	}

	@Test
	public void testUpdate_BossSpeedX_ReverseWhenXLessThanOrEqualToRandomLeftOr0()
			throws NoSuchFieldException, IllegalAccessException {
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 200;
		String type = "enemy02";

		// Create an Enemy_Boss object
		Enemy_Boss enemyBoss = new Enemy_Boss(gameUI, x, y, type);

		// Use reflection to set the value of speed_x to a negative value
		java.lang.reflect.Field speedXField = Enemy_Boss.class.getDeclaredField("speed_x");
		speedXField.setAccessible(true);
		speedXField.setDouble(enemyBoss, -1);

		// Set randomLeft to a value greater than x to trigger the condition
		java.lang.reflect.Field randomLeftField = Enemy_Boss.class.getDeclaredField("randomLeft");
		randomLeftField.setAccessible(true);
		randomLeftField.setInt(enemyBoss, x + 1);

		// Call the update method
		enemyBoss.update();

		// Verify that the speed_x has reversed
		double updatedSpeedX = (double) speedXField.getDouble(enemyBoss);
		assertEquals(1, updatedSpeedX, 0.001);//value of 0.001 is used to provide a tolerance for comparing floating-point values
	}

	@Test
	public void testUpdate_BossSpeedY_ReverseWhenYLessThanOrEqualTo10()
			throws NoSuchFieldException, IllegalAccessException {
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 100;
		int y = 9; // Set y to 10 to trigger the condition
		String type = "enemy02";

		// Create an Enemy_Boss object
		Enemy_Boss enemyBoss = new Enemy_Boss(gameUI, x, y, type);

		// Use reflection to set the value of speed_y to a negative value
		java.lang.reflect.Field speedYField = Enemy_Boss.class.getDeclaredField("speed_y");
		speedYField.setAccessible(true);
		speedYField.setDouble(enemyBoss, -1);

		// Call the update method
		enemyBoss.update();

		// Verify that the speed_y has reversed
		double updatedSpeedY = (double) speedYField.getDouble(enemyBoss);
		assertEquals(1, updatedSpeedY, 0.001); //value of 0.001 is used to provide a tolerance for comparing floating-point values
	}

	@Test
	public void testUpdate_SpeedXReversedWhenXPlusWidthReachedRandomRight()
			throws NoSuchFieldException, IllegalAccessException {
		// Create a GameUI object
		GameUI gameUI = new GameUI();

		// Define test values
		int x = 1000;
		int y = 9; // Set y to 10 to trigger the condition
		String type = "enemy02";

		// Create an Enemy_Boss object
		Enemy_Boss enemyBoss = new Enemy_Boss(gameUI, x, y, type);

		// Use reflection to set the value of speed_y to a negative value
		java.lang.reflect.Field speedYField = Enemy_Boss.class.getDeclaredField("speed_y");
		speedYField.setAccessible(true);
		speedYField.setDouble(enemyBoss, -1);

		// Call the update method
		enemyBoss.update();

		// Verify that the speed_y has reversed
		double updatedSpeedY = (double) speedYField.getDouble(enemyBoss);
		assertEquals(1, updatedSpeedY, 0.001);//value of 0.001 is used to provide a tolerance for comparing floating-point values
	}
}
