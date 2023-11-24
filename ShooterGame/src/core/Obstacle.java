package core;

import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;

import globalData.*;
import main.GameUI;
import main.Timer;

public class Obstacle extends Entity implements Renderable,Updateable{
	private String type;
	Timer timer;
	
	public Obstacle(GameUI gameUI,int x,int y,String type) {
		super(gameUI);
		Render.addRenderableObject(this);
		Updater.addUpdateList(this);
		this.x = x;
		this.y = y;
		this.type = type;
		defaultSetting();
		getImage();
	}

	public void defaultSetting() {
		this.width = Constant.tileSize;
		this.height = Constant.tileSize;
		this.speed = 1;
	}
	
	public void getImage() {
		try {
			switch(type){
				case "obstacle01": bufferedImage = ImageIO.read(getClass().getResourceAsStream("/obstacle_img/obstacle03.png"));break;
				case "obstacle02": bufferedImage = ImageIO.read(getClass().getResourceAsStream("/obstacle_img/obstacle02.png"));break;
				case "obstacle03": bufferedImage = ImageIO.read(getClass().getResourceAsStream("/obstacle_img/obstacle03.png"));break;
			}
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
			Updater.removeUpdateList(this);
			Render.removeRenderableObject(this);
		}
		checkCollision();
	}

	private void checkCollision() {
		Updateable collisionObj = isColliding(this,"jetFighter");
		if(collisionObj != null) {
			JetFighter obj = (JetFighter)collisionObj;
			if(obj.isGetHurt() == false) {
				System.out.println("GetHurt");
				obj.reduceHP(1);
				obj.getHurt();
			}
		}
	}

	@Override
	public String getID() {
		return "obstacle";
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
