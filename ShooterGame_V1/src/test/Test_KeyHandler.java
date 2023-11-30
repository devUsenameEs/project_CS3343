package test;

import static org.junit.Assert.assertEquals;

import java.awt.event.KeyEvent;
import java.io.IOException;

import org.junit.Test;

import main.GameUI;


public class Test_KeyHandler extends TestBase{
	
	private KeyEvent keyEvent ;
	
	@Test
	public void test_KeyTyped() throws IOException {
		GameUI gameUI = new GameUI();
		gameUI.keyHandler.keyTyped(keyEvent);
	}

	@Test
	public void test_keyPressed_playScreen() throws Exception
	{
		GameUI gameUI = new GameUI();
		gameUI.gameState = gameUI.playState;
		
		//test UpPressed
		setKeyEvent(gameUI,KeyEvent.VK_UP);
		assertEquals(gameUI.keyHandler.upPressed, true);
		//test DownPressed
		setKeyEvent(gameUI,KeyEvent.VK_DOWN);
		assertEquals(gameUI.keyHandler.downPressed, true);
		//test RightPressed
		setKeyEvent(gameUI,KeyEvent.VK_RIGHT);
		assertEquals(gameUI.keyHandler.rightPressed, true);
		//test LeftPressed
		setKeyEvent(gameUI,KeyEvent.VK_LEFT);
		assertEquals(gameUI.keyHandler.leftPressed, true);
		//test SpacePressed
		setKeyEvent(gameUI,KeyEvent.VK_SPACE);
		assertEquals(gameUI.keyHandler.spacePressed, true);
		//test vPressed
		setKeyEvent(gameUI,KeyEvent.VK_V);
		assertEquals(gameUI.keyHandler.vPressed, true);
	}
	
	@Test
	public void test_keyPressed_titleScreen() throws IOException {
		GameUI gameUI = new GameUI();
		gameUI.gameState = gameUI.titleState;
		
		gameUI.commandNum = 1;
		setKeyEvent(gameUI,KeyEvent.VK_UP);
		assertEquals(gameUI.commandNum, 0);
		
		gameUI.commandNum = 1;
		setKeyEvent(gameUI,KeyEvent.VK_DOWN);
		assertEquals(gameUI.commandNum, 0);
		
		gameUI.commandNum = 1;
		setKeyEvent(gameUI,KeyEvent.VK_ENTER);
		assertEquals(gameUI.exit, true);
		
		gameUI.commandNum = 0;
		setKeyEvent(gameUI,KeyEvent.VK_UP);
		assertEquals(gameUI.commandNum, 1);
		
		gameUI.commandNum = 0;
		setKeyEvent(gameUI,KeyEvent.VK_DOWN);
		assertEquals(gameUI.commandNum, 1);
		
		gameUI.commandNum = 0;
		setKeyEvent(gameUI,KeyEvent.VK_ENTER);
		assertEquals(gameUI.gameState, gameUI.playState);
	}
	
	@Test
	public void test_keyPressed_deadScreen() throws IOException {
		GameUI gameUI = new GameUI();
		gameUI.gameState = gameUI.deadState;
		
		gameUI.commandNum = 1;
		setKeyEvent(gameUI,KeyEvent.VK_UP);
		assertEquals(gameUI.commandNum, 0);
		
		gameUI.commandNum = 1;
		setKeyEvent(gameUI,KeyEvent.VK_DOWN);
		assertEquals(gameUI.commandNum, 0);
		
		gameUI.commandNum = 1;
		setKeyEvent(gameUI,KeyEvent.VK_ENTER);
		assertEquals(gameUI.exit, true);
		
		gameUI.commandNum = 0;
		setKeyEvent(gameUI,KeyEvent.VK_UP);
		assertEquals(gameUI.commandNum, 1);
		
		gameUI.commandNum = 0;
		setKeyEvent(gameUI,KeyEvent.VK_DOWN);
		assertEquals(gameUI.commandNum, 1);
		
		gameUI.commandNum = 0;
		setKeyEvent(gameUI,KeyEvent.VK_ENTER);
		assertEquals(gameUI.gameState, gameUI.playState);
	}
	
	@Test
	public void test_keyPressed_winScreen() throws IOException {
		GameUI gameUI = new GameUI();
		gameUI.gameState = gameUI.winState;
		
		gameUI.commandNum = 1;
		setKeyEvent(gameUI,KeyEvent.VK_UP);
		assertEquals(gameUI.commandNum, 0);
		
		gameUI.commandNum = 1;
		setKeyEvent(gameUI,KeyEvent.VK_DOWN);
		assertEquals(gameUI.commandNum, 0);
		
		gameUI.commandNum = 1;
		setKeyEvent(gameUI,KeyEvent.VK_ENTER);
		assertEquals(gameUI.exit, true);
		
		gameUI.commandNum = 0;
		setKeyEvent(gameUI,KeyEvent.VK_UP);
		assertEquals(gameUI.commandNum, 1);
		
		gameUI.commandNum = 0;
		setKeyEvent(gameUI,KeyEvent.VK_DOWN);
		assertEquals(gameUI.commandNum, 1);
		
		gameUI.commandNum = 0;
		setKeyEvent(gameUI,KeyEvent.VK_ENTER);
		assertEquals(gameUI.gameState, gameUI.playState);
	}
	
	@Test
	public void test_keyReleased() throws IOException {
		GameUI gameUI = new GameUI();
		gameUI.keyHandler.downPressed = true;
		gameUI.keyHandler.upPressed = true;
		gameUI.keyHandler.leftPressed = true;
		gameUI.keyHandler.rightPressed = true;
		gameUI.keyHandler.spacePressed = true;
		gameUI.keyHandler.vPressed = true;
		
		setreleasedKeyEvent(gameUI,KeyEvent.VK_DOWN);
		assertEquals(gameUI.keyHandler.downPressed, false);
		setreleasedKeyEvent(gameUI,KeyEvent.VK_UP);
		assertEquals(gameUI.keyHandler.upPressed, false);
		setreleasedKeyEvent(gameUI,KeyEvent.VK_LEFT);
		assertEquals(gameUI.keyHandler.leftPressed, false);
		setreleasedKeyEvent(gameUI,KeyEvent.VK_RIGHT);
		assertEquals(gameUI.keyHandler.rightPressed, false);
		setreleasedKeyEvent(gameUI,KeyEvent.VK_SPACE);
		assertEquals(gameUI.keyHandler.spacePressed, false);
		setreleasedKeyEvent(gameUI,KeyEvent.VK_V);
		assertEquals(gameUI.keyHandler.vPressed, false);
	}
	
	private void setKeyEvent(GameUI gameUI, int keyCode) {
		keyEvent = new KeyEvent(
				gameUI, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
	            0,keyCode , KeyEvent.CHAR_UNDEFINED
	    );
		gameUI.keyHandler.keyPressed(keyEvent);
	}
	
	private void setreleasedKeyEvent(GameUI gameUI, int keyCode) {
		keyEvent = new KeyEvent(
				gameUI, KeyEvent.KEY_RELEASED, System.currentTimeMillis(),
	            0,keyCode , KeyEvent.CHAR_UNDEFINED
	    );
		gameUI.keyHandler.keyReleased(keyEvent);
	}
}
