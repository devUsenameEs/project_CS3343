package core;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import globalData.Constant;
import globalData.Render;
import globalData.Renderable;
import globalData.Updateable;
import globalData.Updater;
import main.GameUI;
import main.Timer;

public class Enemy extends Entity implements Renderable,Updateable{

	Timer timer;
	boolean firstTime;
	
	public Enemy(GameUI gameUI , int x, int y) {
		super(gameUI);
		Render.addRenderableObject(this);
		Updater.addUpdateList(this);
		
		this.x = x;
		this.y = y;
		
		firstTime = true;
		
		
		defaultSetting();
		getImage();
	}
	
	private void defaultSetting() {
		this.HP = 2;
		this.speed = 1.5;
		this.width = Constant.tileSize;
		this.height = Constant.tileSize;
	}

	private void getImage() {
		try {
			bufferedImage = ImageIO.read(getClass().getResourceAsStream("/enemy/enemy.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update() {
		y += speed;
		if(y >= Constant.screenHeight)
			Updater.removeUpdateList(this);
		
		Updateable collisionObj = isColliding(this,"JetFighter");
		if(collisionObj != null) {
			if(firstTime) {
				System.out.println("collision happen");
				collisionObj.getRenderable().reduceHP(0.5);
				timer = new Timer(600);
				firstTime = false;
			}
			else {
				if(timer.TimeToZero()) {
					collisionObj.getRenderable().reduceHP(0.5);
					timer.reset();
				}
			}
		}		
		
		if(HP == 0) {
			Updater.removeUpdateList(this);
			Render.removeRenderableObject(this);
		}
	}

	@Override
	public String getID() {
		return "enemy";
	}

	@Override
	public Renderable getRenderable() {
		return this;
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(bufferedImage, x, y, width, height,null);
	}

	@Override
	public int getLayer() {
		return 2;
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
		HP -= x;
		
	}

	@Override
	public void addHP(double x) {
		HP += x;
	}

	@Override
	public double getHP() {
		// TODO Auto-generated method stub
		return HP;
	}



}
