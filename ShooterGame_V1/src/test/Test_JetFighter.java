package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import core.Enemy;
import core.JetFighter;
import globalData.Constant;
import globalData.Render;
import globalData.Updater;
import main.EnergyBar;
import main.EnergyBar_stub;
import main.GameUI;
import main.Main;

public class Test_JetFighter extends TestBase {
	
	@Test
	public void test_addScoreWithNotContinuously() throws Exception
	{
		JetFighter jet;

		Main.initScreen();
		jet = new JetFighter(Main.gameUI,Main.gameUI.keyHandler,new EnergyBar());
		jet.setContin(false);
		jet.setScore(10);
		jet.addScore(20);
		
		int result = jet.getScore();
		assertEquals(result, 30);
	}
	
	@Test
	public void test_addScoreWithContinuously() throws Exception
	{
		JetFighter jet;

		Main.initScreen();
		jet = new JetFighter(Main.gameUI,Main.gameUI.keyHandler,new EnergyBar());
		jet.setContin(true);
		jet.setScore(10);
		jet.addScore(20);
		
		int result = jet.getScore();
		assertEquals(result, 31);
	}
	
	@Test
	public void test_changeBulletType() throws Exception
	{
		JetFighter jet;

		Main.initScreen();
		jet = new JetFighter(Main.gameUI,Main.gameUI.keyHandler,new EnergyBar());
		jet.changeBullet("TestBullet");
		
		String result = jet.getBulletType();;
		assertEquals(result, "TestBullet");
	}
	
	@Test
	public void test_checkIfHurtAndDie() throws Exception
	{
		Main.initScreen();
		Main.initThread();
		Main.gameUI.gameStart();
		while(Main.gameUI.lv == null){Thread.sleep(100);}
		JetFighter jet = Main.gameUI.lv.getJet();
		
		//Jet Collision with the enemy and get hurt
		new Enemy(Main.gameUI,(int)jet.getX(),(int)jet.getY(),"enemy01");
		while(!jet.isGetHurt()) {Thread.sleep(100);}	
		assertEquals((int)jet.getHP(), 5);
		
		//Jet Die
		jet.reduceHP(6);
		Thread.sleep(1000);		
		boolean result = Render.containsRenderableObject(jet);
		assertEquals(result, false);
	}
	
	@Test
	public void test_upPressed_leftPressed_01() throws IOException {
		
		GameUI gameUI = new GameUI();
		JetFighter jet = new JetFighter(gameUI,gameUI.keyHandler,new EnergyBar());
		
		//test upPressed, Y will decrease
		gameUI.keyHandler.upPressed = true;
		int original = (int)jet.getY();
		jet.controlKey();
		assertEquals((int)jet.getY(), original-5);
		
		//test leftPressed, X will decrease
		gameUI.keyHandler.leftPressed = true;	
		original = (int)jet.getX();
		jet.controlKey();
		assertEquals((int)jet.getX(), original-5);
	}
	
	@Test
	public void test_test_downPressed_rightPressed_01() throws IOException {
		
		GameUI gameUI = new GameUI();
		JetFighter jet = new JetFighter(gameUI,gameUI.keyHandler,new EnergyBar());
		
		//test downPressed, Y will increase
		gameUI.keyHandler.downPressed = true;
		int original = (int)jet.getY();
		jet.controlKey();
		assertEquals((int)jet.getY(), original+5);
		
		//test rightPressed, X will increase
		gameUI.keyHandler.rightPressed = true;
		original = (int)jet.getX();
		jet.controlKey();
		assertEquals((int)jet.getX(), original+5);
	}

	@Test
	public void test_upPressed_leftPressed_02() throws IOException {
		
		GameUI gameUI = new GameUI();
		JetFighter jet = new JetFighter(gameUI,gameUI.keyHandler,new EnergyBar());
		jet.setXandY(5, 5);
		
		//test upPressed, Y will not change
		gameUI.keyHandler.upPressed = true;
		int original = (int)jet.getY();
		jet.controlKey();
		assertEquals((int)jet.getY(), original);
		
		//test leftPressed, X will not change
		gameUI.keyHandler.leftPressed = true;
		original = (int)jet.getX();
		jet.controlKey();
		assertEquals((int)jet.getX(), original);
	}
	
	@Test
	public void test_downPressed_rightPressed_02() throws IOException {
		
		GameUI gameUI = new GameUI();
		JetFighter jet = new JetFighter(gameUI,gameUI.keyHandler,new EnergyBar());
		int x = (int) (Constant.screenWidth-jet.getWidth()-5);
		int y = (int) (Constant.screenHeight-jet.getHeight()-5);
		jet.setXandY(x, y);
		
		//test downPressed, Y will not change
		gameUI.keyHandler.downPressed = true;
		
		int original = (int)jet.getY();
		jet.controlKey();
		assertEquals((int)jet.getY(), original);
		
		//test rightPressed, X will not change
		gameUI.keyHandler.rightPressed = true;
				
		original = (int)jet.getX();
		jet.controlKey();
		assertEquals((int)jet.getX(), original);
	}
	
	@Test
	public void test_fireBullet() throws IOException {
		GameUI gameUI = new GameUI();
		JetFighter jet = new JetFighter(gameUI,gameUI.keyHandler,new EnergyBar());
		
		gameUI.keyHandler.spacePressed = true;
		jet.getBullet().changeTime(0);
		jet.controlKey();	
	}
	
	@Test
	public void test_fireSuperBullet() throws IOException {
		GameUI gameUI = new GameUI();
		JetFighter jet = new JetFighter(gameUI,gameUI.keyHandler,new EnergyBar_stub());
		
		gameUI.keyHandler.vPressed = true;
		jet.energyBarUpdate();	
		//after superBullet, energy return to 0
		assertEquals(jet.getEnergyBar().getEnergy(), 0);
	}

}
