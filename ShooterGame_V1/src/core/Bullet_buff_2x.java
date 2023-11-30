package core;

import main.GameUI;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Bullet_buff_2x extends Bullet{
	
	
	public Bullet_buff_2x(GameUI gameUI,int x, int y, Entity obj) throws IOException {
		super(gameUI,x,y,obj);
		defaultSetting();
		getImage();
	}
	
	public void defaultSetting() {
		this.width= 8;
		this.height = 16;
		this.speed = 20;
		this.AP = 2;
	}

	public void getImage() throws IOException {
		bufferedImage = ImageIO.read(getClass().getResourceAsStream("/bullet/bullet01.png"));
	}
	
	@Override
	public String getID() {
		return "bullet_buff_2x";
	}

}
	
	
		
	