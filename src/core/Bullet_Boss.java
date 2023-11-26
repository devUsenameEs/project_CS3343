package core;

import main.GameUI;
import java.io.IOException;
import javax.imageio.ImageIO;
import globalData.Constant;
import globalData.Render;
import globalData.Updateable;
import globalData.Updater;

public class Bullet_Boss extends Bullet{

	public Bullet_Boss(GameUI gameUI,int x, int y, Entity obj) {
		super(gameUI,x,y,obj);
		defaultSetting(x,y,obj);
		getImage();
	}
	
	public void defaultSetting(int x,int y,Entity obj) {
		this.width= 7;
		this.height = 15;
		this.x = x - (width/2);
		this.y = y;
		this.speed = 10;
	}

	public void getImage() {
		try {
			bufferedImage = ImageIO.read(getClass().getResourceAsStream("/bullet/bullet01.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		y += speed;
		if(y>=Constant.screenHeight) {
			Updater.removeUpdateList(this);
			Render.removeRenderableObject(this);
		}
		checkCollision();	
	}
	
	private void checkCollision() {
		Updateable collisionObj = isColliding(this,"jetFighter");
		if(collisionObj != null) {
			JetFighter obj = (JetFighter)collisionObj;
			if(obj.isGetHurt() == false) {
				obj.reduceHP(1);
				obj.getHurt();
			}
		}
	}
	
	public String getID() {
		return "enemy_bullet";
	}

}
