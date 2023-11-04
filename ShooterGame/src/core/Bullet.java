package core;

import main.GameUI;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import globalData.Render;
import globalData.Renderable;
import globalData.Updateable;
import globalData.Updater;


public class Bullet extends Objects implements Renderable,Updateable{
	
	Entity obj;
	
	public Bullet(GameUI gameUI,int x, int y, Entity obj) {
		super(gameUI);
		Render.addRenderableObject(this);
		Updater.addUpdateList(this);
		
		defaultSetting();
		getImage();
		
		this.x = x + (obj.width/2);
		this.y = y;
		this.obj = obj;
	}
	
	public void defaultSetting() {
		this.width= 4;
		this.height = 8;
		this.speed = 12;
	}

	public void getImage() {
		try {
			bufferedImage = ImageIO.read(getClass().getResourceAsStream("/bullet/bullet_player01.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(bufferedImage,x,y,width,height,null);
	}

	@Override
	public int getLayer() {
		return 1;
	}
	
	@Override
	public void update() {
		y -= speed;
		if(y <= 0) {
			Updater.removeUpdateList(this);
			Render.removeRenderableObject(this);
		}
		
		//Collision
		Updateable collisionObj = isColliding(this,"enemy");
		if(collisionObj != null) {
				Enemy emy = (Enemy)collisionObj;
				Updater.removeUpdateList(this);
				Render.removeRenderableObject(this);
				reduceHP(emy);
				if(emy.HP == 0) {
					JetFighter jet = (JetFighter)obj;
					jet.addScore(100);
				}
		}
	}
	
	public void reduceHP(Enemy emy) {
		emy.reduceHP(1);
	}

	@Override
	public String getID() {
		return "bullet";
	}

	@Override
	public Renderable getRenderable() {
		return this;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getHeight() {
		return height;
	}
}
