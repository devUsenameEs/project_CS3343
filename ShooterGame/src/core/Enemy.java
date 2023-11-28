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

public class Enemy extends Entity implements ModuleHP,Updateable,Renderable{
	private String type;

	public Enemy(GameUI gameUI,int x,int y,String type) throws IOException {
		super(gameUI);
		Render.addRenderableObject(this);
		Updater.addUpdateList(this);
		this.x = x;
		this.y = y;
		this.type = type;
		//Enemy Setting
		defaultSetting();
		getImage();
	}

	private void defaultSetting() {
		switch(type){
			case "enemy01": this.maxLife = 1; 
							this.HP = maxLife;
							this.width   = 32;
							this.height  = 32;
							this.speed   = 1;
							this.bulletType   = "";
							break;
					
			case "enemy02": this.maxLife = 2; 
							this.HP = maxLife;
							this.width   = 32;
							this.height  = 32;
							this.speed   = 1;
							this.bulletType   = "";
							break;
		}
	}

	private void getImage() throws IOException {
		switch(type){
			case "enemy01": bufferedImage = ImageIO.read(getClass().getResourceAsStream("/enemy/enemy01.png")); break;
			case "enemy02": bufferedImage = ImageIO.read(getClass().getResourceAsStream("/enemy/enemy02.png")); break;
		}
	}
	
	@Override
	public void update() throws IOException {
		y += speed;
		if(y >= Constant.screenHeight) {
			Updater.removeUpdateList(this);
			Render.removeRenderableObject(this);
		}
		checkCollision();
		checkIfDie();		
	}

	public void checkCollision() throws IOException {
		Updateable collisionObj = isColliding(this,"jetFighter");
		if(collisionObj != null) {
			JetFighter obj = (JetFighter)collisionObj;
			if(obj.isGetHurt() == false) {
				obj.reduceHP(1);
				obj.getHurt();
			}
		}	
	}

	public int checkIfDie() throws IOException {
		if(HP <= 0) {
			Updater.removeUpdateList(this);
			Render.removeRenderableObject(this);
			return 100;
		}
		return -1;
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
	
	public double getMaxlife() {
		return maxLife;
	}

	public double getSpeed() {
		return speed;
	}
}
