package test;

import static org.junit.Assert.assertEquals;


import org.junit.Test;
import core.Enemy;
import core.JetFighter;
import globalData.Render;
import main.Main;

public class Test_JetFighter extends TestBase {
	

	@Test
	public void test_addScoreWithNotContinuously() throws Exception
	{
		JetFighter jet;

		Main.initScreen();
		jet = new JetFighter(Main.gameUI,Main.gameUI.keyHandler);
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
		jet = new JetFighter(Main.gameUI,Main.gameUI.keyHandler);
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
		jet = new JetFighter(Main.gameUI,Main.gameUI.keyHandler);
		jet.changeBullet("TestBullet");
		
		String result = jet.getBulletType();;
		assertEquals(result, "TestBullet");
	}
	
	@Test
	public void test_checkIfDie() throws Exception
	{
		Main.initScreen();
		Main.initThread();
		Main.gameUI.gameStart();
		while(Main.gameUI.lv == null){Thread.sleep(100);}
		
		JetFighter jet = Main.gameUI.lv.getJet();
		jet.reduceHP(6);
		Thread.sleep(100);	
		
		boolean result = Render.containsRenderableObject(jet);
		assertEquals(result, false);
	}
	
	@Test
	public void test_checkIfHurt() throws Exception
	{
		Main.initScreen();
		Main.initThread();
		Main.gameUI.gameStart();
		while(Main.gameUI.lv == null){Thread.sleep(100);}
		
		JetFighter jet = Main.gameUI.lv.getJet();
		new Enemy(Main.gameUI,(int)jet.getX(),(int)jet.getY(),"enemy01");
		Thread.sleep(100);	
		
		int result = (int)jet.getHP();
		assertEquals(result, 5);
	}
	
	
	

	
	
}
