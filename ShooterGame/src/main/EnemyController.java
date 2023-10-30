package main;

import core.Enemy;
import globalData.Constant;
import java.util.Random;

public class EnemyController {
	
	Enemy[] enemy;
	GameUI gameUI;
	Timer timerForRound;
	Random random;
	private int init_round;
	private int round;
	
	public EnemyController(GameUI gameUI,int round) {
		random = new Random();
		this.gameUI = gameUI;
		timerForRound = new Timer(5000);
		init_round = round;
		this.round = 1;
		createEnemy();
	}
	
	private void createEnemy() {
		//if(round <= init_round) {
			int num = random.nextInt(5)+1;
			this.enemy = new Enemy[num];
			for(int i=0;i<num;i++) {
				int x = random.nextInt(Constant.screenWidth-48);
				int y = random.nextInt(151) - 200;
				int width = 48;
				int time = random.nextInt(1000)+500;
				enemy[i] = new Enemy(gameUI,x,y,width,width,time);
				round = round+1;;
		}
	}
	
	public void update() {
		if(timerForRound.TimeToZero()) {
			System.out.print("Time Zero");
			createEnemy();
			timerForRound.reset();
		}
	}
}
