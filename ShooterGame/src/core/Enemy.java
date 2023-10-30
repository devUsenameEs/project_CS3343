package core;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import globalData.Constant;
import globalData.ModuleHP;
import globalData.Render;
import globalData.Renderable;
import globalData.Updateable;
import globalData.Updater;
import main.GameUI;
import main.Timer;

public class Enemy extends Entity implements ModuleHP,Updateable,Renderable{

	Timer bulletTimer;
	boolean firstTime;
	
	public Enemy(GameUI gameUI , int x, int y, int width, int height,int time) {
		super(gameUI);
		Render.addRenderableObject(this);
		Updater.addUpdateList(this);
		firstTime = true;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bulletTimer = new Timer(time);
		defaultSetting();
		getImage();
	}
	
	private void defaultSetting() {
		this.HP = 2;
		this.speed = 2;
	}

	private void getImage() {
		try {
			bufferedImage = ImageIO.read(getClass().getResourceAsStream("/enemy/enemy.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
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
		return HP;
	}
	
	@Override
	public void update() {
		y += speed;
		if(y >= Constant.screenHeight)
			Updater.removeUpdateList(this);
		
		Updateable collisionObj = isColliding(this,"jetFighter");
		if(collisionObj != null) {
			JetFighter obj = (JetFighter)collisionObj;
			if(obj.isGetHurt() == false) {
				obj.reduceHP(1);
				obj.getHurt();
			}
		}	
		
		//bullet fire
		if(bulletTimer.TimeToZero() && y>=0) {
			new Bullet_Enemy(gameUI, x+Constant.tileSize/2, y);
			bulletTimer.reset();
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
	public ModuleHP getHPinterface() {
		return this;
	}
}
