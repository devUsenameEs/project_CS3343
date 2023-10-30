package core;

import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import globalData.*;
import main.GameUI;

public class Obstacle extends Entity implements Renderable,Updateable{
	
	int beginner_y;
	int beginner_x;
	
	public Obstacle(GameUI gameUI,int x,int y) {
		super(gameUI);
		Render.addRenderableObject(this);
		Updater.addUpdateList(this);
		
		this.x = x;
		this.y = y;
		this.beginner_x = y;
		this.beginner_y = y;
		
		defaultSetting();
		getImage();
	}

	public void defaultSetting() {
		this.width= Constant.tileSize;
		this.height = Constant.tileSize;
		this.speed = 2;
	}
	
	public void getImage() {
		try {
			bufferedImage = ImageIO.read(getClass().getResourceAsStream("/obstacle_img/stone.png"));
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
		y += speed;
		if(y >= Constant.screenHeight) {
			y = beginner_y;
		}
		
		Updateable collisionObj = isColliding(this,"JetFight");
		if(collisionObj != null) {
				collisionObj.getRenderable().reduceHP(0.5);
		}
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Renderable getRenderable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reduceHP(double x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addHP(double x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getHP() {
		// TODO Auto-generated method stub
		return 0;
	}
}
