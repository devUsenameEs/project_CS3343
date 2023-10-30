package core;

import main.GameUI;
import main.KeyHandler;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import globalData.Render;
import globalData.Renderable;
import globalData.Updateable;
import globalData.Updater;


public class Bullet extends Entity implements Renderable,Updateable{
	
	public Bullet(GameUI gameUI,int x, int y) {
		super(gameUI);
		Render.addRenderableObject(this);
		Updater.addUpdateList(this);
		
		this.x = x - (width/2);
		this.y = y;
		
		defaultSetting();
		getImage();
	}
	
	public void defaultSetting() {
		this.width= 4;
		this.height = 8;
		this.speed = 5;
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
		
		//Collision
		Updateable collisionObj = isColliding(this,"enemy");
		if(collisionObj != null) {
				System.out.println("Fire!!!");
				Updater.removeUpdateList(this);
				Render.removeRenderableObject(this);
				collisionObj.getRenderable().reduceHP(1);
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

	@Override
	public void reduceHP(double x) {
	}

	@Override
	public double getHP() {
		return 0;
	}

	@Override
	public void addHP(double x) {
		// TODO Auto-generated method stub
		
	}

}
