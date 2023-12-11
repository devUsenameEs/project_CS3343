package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import core.Bullet_buff_05x;
import core.Entity;
import core.JetFighter;
import main.EnergyBar;
import main.GameUI;

public class Test_Bullet_buff_05x extends TestBase{
	@Test
	public void testDefaultSetting() throws IOException{
		GameUI gameUI = new GameUI();
		Entity entity = new Entity(gameUI);

		// Define test values
		int x = 100;
		int y = 200;

		// Create a Bullet_buff_05x object
		Bullet_buff_05x bullet_buff_05x = new Bullet_buff_05x(gameUI, x, y, entity);

		// Verify that the x and y coordinates were set correctly
		assertEquals(x, bullet_buff_05x.getX(), 0.01);
		assertEquals(y, bullet_buff_05x.getY(), 0.01);

		// Verify that the type was set correctly
		assertEquals("bullet_buff_05x", bullet_buff_05x.getID());
	}
	
	@Test
	public void test_fireBullet_buff_05x() throws IOException {
		GameUI gameUI = new GameUI();
		JetFighter jet = new JetFighter(gameUI,gameUI.keyHandler,new EnergyBar());
		jet.changeBullet("Bullet_Debuff");
		gameUI.keyHandler.spacePressed = true;
		jet.getBullet().changeTime(0);
		jet.controlKey();	
	}
}
