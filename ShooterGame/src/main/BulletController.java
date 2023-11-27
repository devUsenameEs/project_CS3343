package main;

import core.*;
import globalData.Constant;
public class BulletController {
	
	private GameUI gameUI;
	private Timer bulletTimer;

	public BulletController(GameUI gameUI,int x) {
		this.gameUI = gameUI;
		bulletTimer = new Timer(x);
	}
	
	public void fireBullet(String type,int x,int y,Entity obj) {
		switch(type) {
			case("Bullet"):
				new Bullet(gameUI,x,y,obj);
				break;
			case("Bullet_Buff"):
				new Bullet_buff_2x(gameUI,x,y,obj);
				break;
			case("Bullet_Debuff"):
				new Bullet_buff_05x(gameUI,x,y,obj);
				break;
			case("SuperBullet"):
				for(int i = 0 ; i < 6 ; i++){
					new Bullet_Super(gameUI, x+Constant.tileSize/2 - 200 + i*100, y, obj);
					for(int j = 0; j < 3; j++){
						new Bullet_Super(gameUI, x+Constant.tileSize/2 - 200 + i*100, y - 200 + j * 100,obj);					}
				}
				break;
			case("BossBullet"):
			    new Bullet_Boss(gameUI,x,y,obj);
			    break;
		}
	}
	
	public boolean canFire() {
		if(bulletTimer.TimeToZero()) {
			bulletTimer.reset();
			return true;
		}
		return false;
	}
}
