package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import main.GameUI;
import main.Main;

public class TestGaneUI {

	@Test
	public void test_titleScreen() throws Exception
	{
		Main.init();
		int gameState = Main.gameUI.gameState;
		assertEquals(gameState, Main.gameUI.titleState);
	}
	
	@Test
	public void test_playScreen() throws Exception
	{
		Main.init();
		Main.gameUI.gameStart();
		int gameState = Main.gameUI.gameState;
		assertEquals(gameState, Main.gameUI.playState);
	}
	
	@Test
	public void test_endScreen() throws Exception
	{

	}
	
	@Test
	public void test_winScreen() throws Exception
	{
		
	}
}
