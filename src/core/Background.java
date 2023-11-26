package core;

import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;

import globalData.*;
import main.GameUI;

public class Background extends Objects{
	
	public Background(GameUI gameUI,int x, int y, String path) {		
		super(gameUI);
		this.x = x;
		this.y = y;
		this.width = Constant.screenWidth;
		this.height = Constant.screenHeight;
		this.speed = 1;
		getImage(path);
	}
	
	public void getImage(String path) {
		try {
			bufferedImage = ImageIO.read(getClass().getResourceAsStream(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(bufferedImage, x, y, width, height,null);
	}
		
	public void update() {
		y += speed;
		if(y >= Constant.screenHeight) 
			y = - Constant.screenHeight;
	}
}
