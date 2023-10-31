package core;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import globalData.*;

public class Background{
	
	private int x,y,speed;
	private BufferedImage backgroundImage;
	
	public Background(int x, int y, String path) {		
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
