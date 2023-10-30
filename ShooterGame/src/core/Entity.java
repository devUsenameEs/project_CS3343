package core;


import java.awt.image.BufferedImage;
import main.GameUI;

public class Entity {
	
	protected GameUI gameUI;
	protected BufferedImage bufferedImage;
	
	protected int x,y;
	protected int width,height;
	protected double speed;
	
	protected Heart heart;
	protected double HP;
	protected double maxLife;
	
	
	public Entity(GameUI gameUI) {
		this.gameUI = gameUI;
	}

}
