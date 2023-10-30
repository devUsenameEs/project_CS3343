package main;

import core.Obstacle;
import globalData.Constant;
import java.util.Random;

public class ObstacleController{
	
	GameUI gameUI;
	Obstacle[] obstacle;
	Timer timer;
	private int num;
	
	public ObstacleController(GameUI gameUI,int num) {
		this.obstacle = new Obstacle[num];
		this.gameUI = gameUI;
		this.num = num;
		timer = new Timer(1500);
		createObstacle();
	}
	
	private void createObstacle() {
		Random random = new Random();
		for(int i=0;i<num;i++) {
			int x = random.nextInt(Constant.screenWidth);
			int y = random.nextInt(151) - 200;
			int[] availableValues = {8, 16, 32,48};
			int randomIndex = random.nextInt(availableValues.length);
			int width = availableValues[randomIndex];
			obstacle[i] = new Obstacle(gameUI,x,y,width,width);
		}
	}

	public void update() {
		if(timer.TimeToZero()) {
			createObstacle();
			timer.reset();
		}
	}
}
