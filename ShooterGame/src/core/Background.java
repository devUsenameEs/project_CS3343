package core;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import globalData.*;
import main.GameUI;

public class Background{
	
	GameUI gameUI;
	
	protected int x,y,speed;
	protected BufferedImage backgroundImage;
	
	public Background(GameUI gameUI , int x, int y, String path) {
		this.gameUI = gameUI;		
		this.x = x;
		this.y = y;
		this.speed = 1;
		getImage(path);
	}
	
	public void getImage(String path) {
		try {
			backgroundImage = ImageIO.read(getClass().getResourceAsStream(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(backgroundImage, x, y, Constant.screenWidth, Constant.screenHeight,null);
	}
		
	public void update() {
		y += speed;
		if(y >= Constant.screenHeight) 
			y = - Constant.screenHeight;
	}
}
