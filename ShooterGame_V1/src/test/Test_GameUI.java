package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import core.Enemy_Boss;
import core.JetFighter;
import globalData.Level_Boss;
import globalData.Render;
import globalData.Updater;
import globalData.Level;
import main.EnergyBar;
import main.GameUI;
import main.Main;

public class Test_GameUI extends TestBase{

	@Test
	public void test_Screen() throws Exception
	{
		int gameState;
		Main.initScreen();
		Main.initThread();
		
		//titleScreen
		while(Main.gameUI.g2 == null){Thread.sleep(100);}
		gameState = Main.gameUI.gameState;
		assertEquals(gameState, Main.gameUI.titleState);
		
		//playScreen
		Main.gameUI.gameStart();
		while(Main.gameUI.lv == null){Thread.sleep(100);}
		gameState = Main.gameUI.gameState;
		assertEquals(gameState, Main.gameUI.playState);
		
		//endScreen
		Main.gameUI.lv.getJet().reduceHP(6);
		Main.gameUI.update();
		gameState = Main.gameUI.gameState;
		assertEquals(gameState, Main.gameUI.deadState);
		clearAll();
	}
	
	@Test
	public void test_winScreen() throws Exception
	{	
		Main.initScreen();
		Main.initThread();

		//Setting
		Main.gameUI.gameState = Main.gameUI.playState;
		Level level = new Level_Boss(Main.gameUI,Main.gameUI.keyHandler,new JetFighter(Main.gameUI,Main.gameUI.keyHandler,new EnergyBar()));
		Main.gameUI.lv = level;
		//while(Main.gameUI.lv == null){Thread.sleep(100);}
		
		Level_Boss lv_boss = (Level_Boss)level;
		Enemy_Boss boss = lv_boss.getBoss();
		
		//Boss Die
		boss.reduceHP(boss.getMaxlife());
		while(!boss.changeToWinnerScreen()) {Thread.sleep(100);};
		
		//winScreen
		int gameState = Main.gameUI.gameState;
		assertEquals(gameState, Main.gameUI.winState);
		clearAll();
	}
	
	@Test
	public void test_changeLevel() throws Exception
	{	
		GameUI gameUI = new GameUI();
		Level level = new Level_Boss(gameUI,gameUI.keyHandler,new JetFighter(gameUI,gameUI.keyHandler,new EnergyBar()));
		
		//Change From LevelOne To LevelBoss
		gameUI.changeLevel(level);
		assertTrue(gameUI.lv instanceof Level_Boss);
		clearAll();
	}
	
	public void clearAll() {
		System.out.println("BeforeNotWork,UseClearAllMethod");
		while(Main.gameUI != null) {

			Main.gameUI.lv = null;
			Main.gameUI.gameThread.interrupt();
			Main.gameUI = null;
			Main.window.dispose();
		}
		
		Render.clearRenderableObject();
		Updater.ClearAllUpdateableObjects();
		System.out.println("GameUI:"+ Main.gameUI + "\n");
	}
}
