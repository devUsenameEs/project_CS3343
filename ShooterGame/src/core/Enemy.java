package core;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import globalData.Constant;
import globalData.ModuleHP;
import globalData.Render;
import globalData.Renderable;
import globalData.Updateable;
import globalData.Updater;
import main.BulletController;
import main.GameUI;

public class Enemy extends Entity implements ModuleHP,Updateable,Renderable{
	
	public Enemy(GameUI gameUI , int x, int y,int width,int height) {
		super(gameUI);
		Render.addRenderableObject(this);
		Updater.addUpdateList(this);
		//default setting
		this.x = x;
		this.y = y;
		this.width = 32;
		this.height = 32;
		defaultSetting();
		getImage();
		//bullet setting
		Random random = new Random();
		int timeI = random.nextInt(1000)+500;
		bullet = new BulletController(gameUI,timeI);
		bulletType = "";
	}
	
	private void defaultSetting() {
		this.maxLife = 1;
		this.HP = maxLife;
		this.speed = 1;
	}

	private void getImage() {
		try {
			bufferedImage = ImageIO.read(getClass().getResourceAsStream("/enemy/enemy01.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update() {
		y += speed;
		if(y >= Constant.screenHeight)
			Updater.removeUpdateList(this);
		
		//collision happen
		Updateable collisionObj = isColliding(this,"jetFighter");
		if(collisionObj != null) {
			JetFighter obj = (JetFighter)collisionObj;
			if(obj.isGetHurt() == false) {
				obj.reduceHP(1);
				obj.getHurt();
			}
		}			
		
		//if die
		if(HP <= 0) {
			Updater.removeUpdateList(this);
			Render.removeRenderableObject(this);
		}
		
		//bullet fire
		if(bullet.canFire()) {
			bullet.fireBullet(bulletType, x, y, this);
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
}
