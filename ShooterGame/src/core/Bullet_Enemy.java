package core;

import java.io.IOException;
import javax.imageio.ImageIO;
import globalData.Constant;
import globalData.Render;
import globalData.Updateable;
import globalData.Updater;
import main.GameUI;

public class Bullet_Enemy extends Bullet{

	public Bullet_Enemy(GameUI gameUI, int x, int y,Entity obj) {
		super(gameUI, x, y,obj);
	}
	
	public void defaultSetting() {
		this.width= 4;
		this.height = 8;
		this.speed = 2;
	}
	
	public void getImage() {
		try {
			bufferedImage = ImageIO.read(getClass().getResourceAsStream("/bullet/bullet_enemy01.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update() {
		y += speed;
		if(y >= Constant.screenHeight) {
			Updater.removeUpdateList(this);
			Render.removeRenderableObject(this);
		}
		
		//Collision
		Updateable collisionObj = isColliding(this,"jetFighter");
		if(collisionObj != null) {
			JetFighter obj = (JetFighter)collisionObj;
			Updater.removeUpdateList(this);
			Render.removeRenderableObject(this);
			if(obj.isGetHurt() == false) {
				System.out.println("GetHurt");
				obj.reduceHP(1);
				obj.getHurt();
			}
		}
	}

}
