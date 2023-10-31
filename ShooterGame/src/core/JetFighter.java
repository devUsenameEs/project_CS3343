package core;

import main.*;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;

import globalData.*;

public class JetFighter extends Entity implements ModuleHP,Updateable,Renderable{
	
	private KeyHandler keyHandler;
	private boolean hurtState;
	private int score;
	private Timer hurtTimer;
	
	public JetFighter(GameUI gameUI, KeyHandler keyHandler) {
		super(gameUI);
		this.keyHandler = keyHandler;
		Render.addRenderableObject(this);
		Updater.addUpdateList(this);
		
		//default setting
		this.x = Constant.screenWidth/2 - (Constant.tileSize/2);
		this.y = Constant.screenHeight/2 + (Constant.tileSize*3);
		this.width = Constant.tileSize;
		this.height = Constant.tileSize;
		this.speed = 5;
		//bullet setting
		this.bulletType = "Bullet";
		this.bullet = new BulletController(gameUI,100);
		this.score = 0;
		//heart setting
		this.heart = new Heart(this);
		this.maxLife = 6;
		this.HP = maxLife;
		
		hurtState = false;
		getImage();
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
		hurtState = true;
		hurtTimer = new Timer(800);
		changeImage();
	}
	
	public boolean isGetHurt() {
		return hurtState;
	}
	
	public void addScore(int x) {
		score += x;
		System.out.println(score);
	}
	
	public void changeBullet(String x) {
		bulletType = x;
	}
	
	@Override
	public void update() {
		//JET not out of bounds
		if( x == Constant.screenWidth ) {x = -Constant.tileSize;}
		else if( (x+Constant.tileSize) == 0 ) {x = Constant.screenWidth;}
		else if( y == Constant.screenHeight ) {y = -Constant.tileSize;}
		else if( (y+Constant.tileSize) == 0 ) {y = Constant.screenHeight;}
		
		//control key
		if(keyHandler.upPressed == true) {y -= speed;}
		if(keyHandler.downPressed == true) {y += speed;}
		if(keyHandler.leftPressed == true) {x -= speed;}
		if(keyHandler.rightPressed == true) {x += speed;}
				
		//When HP is 0 and will die
		if(this.HP == 0) {
			changeImage();
			Updater.removeUpdateList(this);
			Render.removeRenderableObject(this);
		}

		//getHurtState return to false
		if(hurtTimer != null && hurtTimer.TimeToZero()) {
			Updater.removeUpdateList(hurtTimer);
			hurtTimer = null;
			hurtState = false;
			getImage();
		}
		
		//bullet fire
		if(bullet.canFire() && !hurtState) {
			bullet.fireBullet(bulletType, x, y, this);
		}
	}
	
	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(bufferedImage, x, y, width, height,null);
		heart.draw(g2);
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
}
