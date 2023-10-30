package globalData;


import java.awt.Graphics2D;
import core.*;
import main.GameUI;
import main.KeyHandler;

public class LevelOne implements Level{
	
	//DefaultSetting
	public GameUI gameUI;
	public KeyHandler keyH;
	
	//My Setting
	public Background bg1;
	public Background bg2;
	public JetFighter jet;
	public Heart heart;
	public Obstacle[] obstacles = new Obstacle[3];
	public Enemy[] enemy = new Enemy[3];
	
	//Constructor
	public LevelOne(GameUI gameUI, KeyHandler keyH) {
		Render.clearRenderableObject();
		this.gameUI = gameUI;
		this.keyH = keyH;
		LevelDesign();
	}

	//Change Level
	public void changeLevel() {

	}
	
	//LevelSetting_Design
	public void LevelDesign() {
		bg1 = new Background(gameUI, 0, 0, "/background/space_bg.jpg");
		bg2 = new Background(gameUI, 0, (-Constant.screenHeight),"/background/space_bg.jpg");
		jet = new JetFighter(gameUI,keyH);
		ObstaclesDesign();
		EnemyDesign();
		ObjectDesign();
	}
	
	private void ObstaclesDesign() {
		int[] Obs_x = {64,300,650};
		int[] Obs_y = {-160,-400,-320};
		for(int i=0;i<3;i++) 
			obstacles[i] = new Obstacle(gameUI, Obs_x[i], Obs_y[i]);
	}
	
	private void EnemyDesign() {
		int[] Obs_x = {64,300,650};
		int[] Obs_y = {-160,-400,-320};
		for(int i=0;i<3;i++) 
			enemy[i] = new Enemy(gameUI, Obs_x[i], Obs_y[i]);
	}
	
	private void ObjectDesign() {
		// TODO Auto-generated method stub
		
	}

	public void update() {
		bg1.update();
		bg2.update();
		Updater.update();
	}
	
	public void draw(Graphics2D g2) {
		bg1.draw(g2);
		bg2.draw(g2);
		Render.draw(g2);
	}

}
