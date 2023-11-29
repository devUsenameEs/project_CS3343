package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed,enterPressed,vPressed;
	public GameUI gameUI;
	public KeyHandler(GameUI gameUI) {
		this.gameUI = gameUI;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(gameUI.gameState == gameUI.titleState || gameUI.gameState == gameUI.deadState || gameUI.gameState == gameUI.winState) {
			if (code == KeyEvent.VK_UP) {
				if (gameUI.commandNum == 1) gameUI.commandNum = 0; else gameUI.commandNum = 1;
			}
			if (code == KeyEvent.VK_DOWN) {
				if (gameUI.commandNum == 1) gameUI.commandNum = 0; else gameUI.commandNum = 1;
			}
	        if (code == KeyEvent.VK_ENTER) {
	        	if (gameUI.commandNum == 0) {
	        		gameUI.gameStart();
	        	}else
	        		System.exit(0);
	        }
		}
		
		
		if(gameUI.gameState == gameUI.playState) {
			if (code == KeyEvent.VK_DOWN) {
				downPressed = true;
			}
			if (code == KeyEvent.VK_UP) {
				upPressed = true;
			}
			if (code == KeyEvent.VK_LEFT) {
				leftPressed = true;
			}
			if (code == KeyEvent.VK_RIGHT) {
				rightPressed = true;
			}
	        if (code == KeyEvent.VK_SPACE) {
	        	spacePressed = true;
	        }
	        if (code == KeyEvent.VK_V) {
	        	vPressed = true;
	        }
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
			if (code == KeyEvent.VK_DOWN) {
				downPressed = false;
			}
			if (code == KeyEvent.VK_UP) {
				upPressed = false;
			}
			if (code == KeyEvent.VK_LEFT) {
				leftPressed = false;
			}
			if (code == KeyEvent.VK_RIGHT) {
				rightPressed = false;
			}
			if (code == KeyEvent.VK_SPACE) {
	        	spacePressed = false;
	        }
			if (code == KeyEvent.VK_V) {
	        	vPressed = false;
	        }
	}

}
