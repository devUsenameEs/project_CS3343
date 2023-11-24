package core;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import globalData.Constant;
import globalData.Render;
import globalData.Updater;
import main.BulletController;
import main.GameUI;

public class Enemy_Boss extends Enemy{
	
	private Random random;
	private int randomLeft;
	private int randomRight;
	private double speed_x;
	private double speed_y;

	public Enemy_Boss(GameUI gameUI, int x, int y, String type) {
		super(gameUI, x, y, type);
		defaultSetting();
		getImage();
	}

	private void defaultSetting() {
		this.maxLife = 1; 
		this.HP = maxLife;
		this.speed   = 1;
		this.speed_x = speed;
		this.speed_y = speed;
		this.width   = 100;
		this.height  = 100;
		this.bulletType = "BossBullet";
		this.bullet = new BulletController(gameUI,600);
		
		//position setting
		random = new Random();
		randomLeft = random.nextInt(Constant.screenWidth/2+1);
		randomRight = random.nextInt(Constant.screenWidth/2+1)+Constant.screenWidth/2;
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
			x += speed_x;
			y += speed_y;
		
		
		if(speed_x > 0) {  
			if((x+width) >= randomRight || (x+width) >= Constant.screenWidth) {
				speed_x *= -1;
				randomRight = random.nextInt(183)+182;
			}
		}else{  
			if(x <= randomLeft || x <= 0) {
				speed_x *= -1;
				randomLeft = random.nextInt(183);
			}
		}
		if(speed_y > 0) {
			if(y>=100) 
				speed_y *= -1;
		}
		else {
			if(y <= 10) 
				speed_y *= -1;
		}
		
		super.checkCollision();
		super.checkIfDie();	
	}
	
	public int checkIfDie() {
		if(HP <= 0) {		
			Updater.removeUpdateList(this);
			Render.removeRenderableObject(this);
			return 3343; // return score
		}
		return -1;
	}
	

}
