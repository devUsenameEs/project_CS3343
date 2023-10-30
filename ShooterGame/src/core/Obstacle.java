package core;

import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import globalData.*;
import main.GameUI;
import main.Timer;

public class Obstacle extends Entity implements Renderable,Updateable{
	
	int beginner_y;
	int beginner_x;
	Timer timer;
	boolean firstTime;
	
	public Obstacle(GameUI gameUI,int x,int y, int width, int height) {
		super(gameUI);
		Render.addRenderableObject(this);
		Updater.addUpdateList(this);
		
		this.x = x;
		this.y = y;
		this.width= width;
		this.height = height;
		firstTime = true;
		
		defaultSetting();
		getImage();
	}

	public void defaultSetting() {
		
		this.speed = 1;
	}
	
	public void getImage() {
		try {
			bufferedImage = ImageIO.read(getClass().getResourceAsStream("/obstacle_img/obstacle2.png"));
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

	@Override
	public ModuleHP getHPinterface() {
		return null;
	}
}
