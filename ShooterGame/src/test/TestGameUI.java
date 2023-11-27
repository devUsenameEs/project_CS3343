package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import core.Enemy_Boss;
import core.JetFighter;
import globalData.Level_Boss;
import globalData.Level;
import main.GameUI;
import main.Main;

public class TestGameUI {

	@Test
	public void test_titleScreen() throws Exception
	{
		Main.init();
		GameUI gameUI = Main.gameUI;
		
		int gameState = gameUI.gameState;
		assertEquals(gameState, gameUI.titleState);
	}
	
	@Test
	public void test_playScreen() throws Exception
	{
		Main.init();
		GameUI gameUI = Main.gameUI;
		gameUI.gameStart();
		
		int gameState = gameUI.gameState;
		assertEquals(gameState, gameUI.playState);
	}
	
	@Test
	public void test_endScreen() throws Exception
	{
		JetFighter jet;
		
		Main.init();
		Main.gameUI.gameStart();
		Main.gameUI.drawPlayScreen();
		
		jet = Main.gameUI.lv.getJet();
		jet.reduceHP(jet.getMaxlife());
		jet.update();
		int gameState = Main.gameUI.gameState;
		assertEquals(gameState, Main.gameUI.deadState);
	}
	
	@Test
	public void test_winScreen() throws Exception
	{	
		Main.init();
		Main.gameUI.gameStart();
		Main.gameUI.drawPlayScreen();
	
		Level lvv = new Level_Boss(Main.gameUI,Main.gameUI.keyHandler,Main.gameUI.lv.getJet());
		Main.gameUI.changeLevel(lvv);
		
		Level_Boss lv_boss = (Level_Boss)lvv;
		Enemy_Boss boss = lv_boss.getBoss();
		
		boss.reduceHP(boss.getMaxlife());
		while(!boss.changeToWinnerScreen()) {boss.update();};
		lv_boss.update();
		int gameState = Main.gameUI.gameState;
		assertEquals(gameState, Main.gameUI.winState);
	}
	
	
}
