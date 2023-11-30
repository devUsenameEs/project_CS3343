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

public class Buff_Heart extends Objects implements Updateable,Renderable{
	
	public Buff_Heart(GameUI gameUI, int x, int y) throws IOException {
		super(gameUI);
		Render.addRenderableObject(this);
		Updater.addUpdateList(this);
		this.x = x;
		this.y = y;
		defaultSetting();
		getImage();
	}
	
	private void defaultSetting() {
		this.width = 32;
		this.height = 32;
		this.speed = 1;
	}
	
	private void getImage() throws IOException {
		bufferedImage = ImageIO.read(getClass().getResourceAsStream("/buff/heartbuff.png")); 
	}

	@Override
	public void update() {
		y += speed;
		if(y >= Constant.screenHeight) {
			Updater.removeUpdateList(this);
			Render.removeRenderableObject(this);
		}
		checkCollision();
	}
	
	protected void checkCollision() {
		Updateable collisionObj = isColliding(this,"jetFighter");
		if(collisionObj != null) {
			JetFighter obj = (JetFighter)collisionObj;
			System.out.println("Jet is collion and HP:"+obj.getHP()+" "+obj);
			System.out.println("Jet is collion and hurt:"+obj.isGetHurt()+" "+obj);
			if(obj.isGetHurt() == false && obj.getHP()!=6) {
				obj.addHP(1);
			}
			Updater.removeUpdateList(this);
			Render.removeRenderableObject(this);
		}	
	}

	@Override
	public String getID() {
		return "heartbuff"; 
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
		return 1;
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
