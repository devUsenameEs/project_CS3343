package core;

import main.*;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;

import globalData.*;

public class JetFighter extends Entity implements ModuleHP,Updateable,Renderable{
	
	private KeyHandler keyHandler;
	private boolean hurtState;
	private Score score;
	private EnergyBar energyBar;
	private Timer hurtTimer;
	private boolean energyBarCanStore;
	private Heart heart;
	
	public JetFighter(GameUI gameUI, KeyHandler keyHandler) {
		super(gameUI);
		this.keyHandler = keyHandler;
		Render.addRenderableObject(this);
		Updater.addUpdateList(this);
		
		//default setting
		this.x = Constant.screenWidth/2 - (Constant.tileSize/2);
		this.y = Constant.screenHeight/2 + (Constant.tileSize*3);
		this.width = 40;//Constant.tileSize;
		this.height = 40;//Constant.tileSize;
		this.speed = 5;
		//bullet setting
		this.bulletType = "Bullet";
		this.bullet = new BulletController(gameUI,300);
		this.score = new Score();
		this.energyBar = new EnergyBar(this);
		//heart setting
		this.heart = new Heart(this);
		this.maxLife = 6;
		this.HP = maxLife;
		this.energyBarCanStore= true; 
		
		
		hurtState = false;
		getImage();
	}
	
	private void getImage() {
		try {
			bufferedImage = ImageIO.read(getClass().getResourceAsStream("/resourse/jetFighters/jet01.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void changeImage() {
		try {
			bufferedImage = ImageIO.read(getClass().getResourceAsStream("/resourse/jetFighters/jetGetHurt.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getHurt() {
		hurtState = true;
		score.setContinuously(false);
		hurtTimer = new Timer(800);
		changeImage();
	}
	
	public boolean isGetHurt() {
		return hurtState;
	}
	
	public void addScore(int x) {
		score.addScore(x);
		System.out.println(score.getScore());
	}
	
	public void changeBullet(String x) {
		bulletType = x;
	}
	
	public int getScore() {
		return score.getScore();
	}
	
	public void changeEnergyBarState(boolean b) {
		energyBar.changeEnergyBarState(b);
	}
	
	@Override
	public void update() {
		//control key
		if(keyHandler.upPressed == true && y > 5) {y -= speed;}
		if(keyHandler.downPressed == true && (y < Constant.screenHeight-height-5)) {y += speed;}
		if(keyHandler.leftPressed == true && (x > 5)) {x -= speed;}
		if(keyHandler.rightPressed == true && (x < Constant.screenWidth-width-5)) {x += speed;}
		if(keyHandler.spacePressed == true && bullet.canFire() && !hurtState) {
		bullet.fireBullet(bulletType, x, y, this);}
				
		//When HP is 0 and will die
		if(this.HP == 0) {
			changeImage();
			Updater.removeUpdateList(this);
			Render.removeRenderableObject(this);
			gameUI.gameState = gameUI.deadState;
		}

		//getHurtState return to false
		if(hurtTimer != null && hurtTimer.TimeToZero()) {
			Updater.removeUpdateList(hurtTimer);
			hurtTimer = null;
			hurtState = false;
			getImage();
		}
		
		//energy bar update
		energyBar.update();
		if(keyHandler.vPressed == true && energyBar.getBarState()) {
			bullet.fireBullet("SuperBullet", x, y, this); 
			energyBar.resetEnergyBar();
		}
	}
	
	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(bufferedImage, x, y, width, height,null);
		heart.draw(g2);
		score.draw(g2); 
		if(energyBarCanStore)
			energyBar.draw(g2);
	}

	@Override
	public int getLayer() {
		return 3;
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
	
	public String getBulletType() {
		return bulletType;
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
