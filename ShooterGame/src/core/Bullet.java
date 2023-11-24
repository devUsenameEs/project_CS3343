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
	
	protected Entity entity;
	protected double AP;
	
	public Bullet(GameUI gameUI,int x, int y, Entity obj) {
		super(gameUI);
		Render.addRenderableObject(this);
		Updater.addUpdateList(this);
		this.x = x + (obj.width/2);
		this.y = y;
		this.entity = obj;
		defaultSetting();
		getImage();
	}
	
	public void defaultSetting() {
		this.width= 4;
		this.height = 12;
		this.speed = 12;
		this.AP = 1;
	}

	public void getImage() {
		try {
			bufferedImage = ImageIO.read(getClass().getResourceAsStream("/bullet/bullet01.png"));
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
		checkCollision();
	}
	
	private void checkCollision() {
		Updateable collisionObj = isColliding(this,"enemy");
		if(collisionObj != null) {
				Enemy enemy = (Enemy)collisionObj;
				enemy.reduceHP(AP);
				int score = enemy.checkIfDie();
				if(score > 0) {
					JetFighter jet = (JetFighter)entity;
					jet.addScore(score);
				}
				Updater.removeUpdateList(this);
				Render.removeRenderableObject(this);
		}
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
