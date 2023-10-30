package core;

import main.*;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;

import globalData.*;

public class JetFighter extends Entity implements ModuleHP,Updateable,Renderable{
	
	KeyHandler keyHandler;
	Timer bulletTimer;
	Timer hurtTimer;
	boolean getHurt;
	
	public JetFighter(GameUI gameUI, KeyHandler keyHandler) {
		super(gameUI);
		this.keyHandler = keyHandler;
		Render.addRenderableObject(this);
		Updater.addUpdateList(this);
		
		this.x = Constant.screenWidth/2 - (Constant.tileSize/2);
		this.y = Constant.screenHeight/2 + (Constant.tileSize*3);
		this.width = Constant.tileSize;
		this.height = Constant.tileSize;
		this.speed = 5;
		
		this.maxLife = 6;
		this.HP = maxLife;
		this.heart = new Heart(gameUI,this);
		getImage();
		
		bulletTimer = new Timer(100);
		getHurt = false;
	}
	
	private void getImage() {
		try {
			bufferedImage = ImageIO.read(getClass().getResourceAsStream("/jetFighters/jet01.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void changeImage() {
		try {
			bufferedImage = ImageIO.read(getClass().getResourceAsStream("/jetFighters/jetGetHurt.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getHurt() {
		getHurt = true;
		hurtTimer = new Timer(500);
		changeImage();
		
	}
	
	public boolean isGetHurt() {
		return getHurt;
	}
	
	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(bufferedImage, x, y, width, height,null);
		heart.draw(g2);
	}

	@Override
	public void update() {
		
		//JET not out of bounds
		if( x == Constant.screenWidth ) {
			x = -Constant.tileSize;
		}
		else if( (x+Constant.tileSize) == 0 ) {
			x = Constant.screenWidth;
		}
		else if( y == Constant.screenHeight ) {
			y = -Constant.tileSize;
		}
		else if( (y+Constant.tileSize) == 0 ) {
			y = Constant.screenHeight;
		}
		
		//control key
		if(keyHandler.upPressed == true) {
			y -= speed;
		}
		if(keyHandler.downPressed == true) {
			y += speed;
		}
		if(keyHandler.leftPressed == true) {
			x -= speed;
		}
		if(keyHandler.rightPressed == true) {
			x += speed;
		}
		
		//bullet fire
		if(bulletTimer.TimeToZero() && !getHurt) {
			new Bullet(gameUI, x+Constant.tileSize/2, y);
			bulletTimer.reset();
		}
		
		//Collision
		if(this.HP == 0) {
			Updater.removeUpdateList(this);
			Render.removeRenderableObject(this);
		}
		
		//getHurt
		if(hurtTimer != null && hurtTimer.TimeToZero()) {
			Updater.removeUpdateList(hurtTimer);
			hurtTimer = null;
			getHurt = false;
			getImage();
		}
	}

	@Override
	public int getLayer() {
		return 2;
	}
	
	@Override
	public String getID() {
		return "jetFighter";
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
	public double getHP() {
		return this.HP;
	}

	@Override
	public void reduceHP(double x) {
		this.HP -= x;
	}

	@Override
	public void addHP(double x) {
		this.HP += x;
	}

	@Override
	public ModuleHP getHPinterface() {
		return this;
	}
}
