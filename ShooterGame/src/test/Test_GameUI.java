package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import core.Enemy_Boss;
import core.JetFighter;
import globalData.Level_Boss;
import globalData.Render;
import globalData.Updater;
import globalData.Level;
import main.GameUI;
import main.Main;

public class Test_GameUI extends TestBase{

	@Test
	public void test_titleScreen() throws Exception
	{
		Main.initScreen();
		Main.initThread();
		GameUI gameUI = Main.gameUI;
		
		int gameState = gameUI.gameState;
		assertEquals(gameState, gameUI.titleState);
	}
	
	@Test
	public void test_playScreen() throws Exception
	{
		Main.initScreen();
		Main.initThread();
		Main.gameUI.gameStart();
		while(Main.gameUI.lv == null){Thread.sleep(100);}
		
		
		int gameState = Main.gameUI.gameState;
		assertEquals(gameState, Main.gameUI.playState);
	}
	
	@Test
	public void test_endScreen() throws Exception
	{
		JetFighter jet;
		
		Main.initScreen();
		Main.initThread();
		Main.gameUI.gameStart();
		while(Main.gameUI.lv == null){Thread.sleep(100);}
		
		jet = Main.gameUI.lv.getJet();
		jet.reduceHP(6);
		jet.update();
		int gameState = Main.gameUI.gameState;
		assertEquals(gameState, Main.gameUI.deadState);
	}
	
	@Test
	public void test_winScreen() throws Exception
	{	
		Main.initScreen();
		Main.initThread();
		Main.gameUI.gameStart();
		while(Main.gameUI.lv == null){Thread.sleep(100);}
	
		Level lvv = new Level_Boss(Main.gameUI,Main.gameUI.keyHandler,Main.gameUI.lv.getJet());
		Main.gameUI.changeLevel(lvv);
		
		Level_Boss lv_boss = (Level_Boss)lvv;
		Enemy_Boss boss = lv_boss.getBoss();
		
		boss.reduceHP(boss.getMaxlife());
		while(!boss.changeToWinnerScreen()) {Thread.sleep(100);};
		int gameState = Main.gameUI.gameState;
		assertEquals(gameState, Main.gameUI.winState);
	}
}
