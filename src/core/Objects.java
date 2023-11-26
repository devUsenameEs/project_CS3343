package core;

import java.awt.image.BufferedImage;

import main.GameUI;

public class Objects {
	
	protected GameUI gameUI;
	protected BufferedImage bufferedImage;
	
	protected int x,y;
	protected int width,height;
	protected double speed;
	
	public Objects(GameUI gameUI) {
		this.gameUI = gameUI;
	}
}
