package core;


import java.awt.image.BufferedImage;

import main.BulletController;
import main.GameUI;

public class Entity {
	
	protected GameUI gameUI;
	protected BufferedImage bufferedImage;
	
	protected int x,y;
	protected int width,height;
	protected double speed;
	
	protected BulletController bullet;
	protected String bulletType; 
	
	protected double HP;
	protected double maxLife;
	
	
	public Entity(GameUI gameUI) {
		this.gameUI = gameUI;
	}
}
