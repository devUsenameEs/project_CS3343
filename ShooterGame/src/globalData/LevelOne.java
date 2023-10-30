package globalData;


import java.awt.Graphics2D;
import core.*;
import main.*;

public class LevelOne implements Level{
	
	//DefaultSetting
	public GameUI gameUI;
	public KeyHandler keyH;
	
	//My Setting
	public Background bg1;
	public Background bg2;
	public JetFighter jet;
	public ObstacleController obstacles;
	public EnemyController enemy;
	
	//Constructor
	public LevelOne(GameUI gameUI, KeyHandler keyH) {
		Render.clearRenderableObject();
		this.gameUI = gameUI;
		this.keyH = keyH;
		LevelDesign();
	}

	//Change Level
	public void changeLevel() {
		gameUI.changeLevel(new LevelTwo());
	}
	
	//LevelSetting_Design
	public void LevelDesign() {
		bg1 = new Background(gameUI, 0, 0, "/background/space_bg.jpg");
		bg2 = new Background(gameUI, 0, (-Constant.screenHeight),"/background/space_bg.jpg");
		jet = new JetFighter(gameUI,keyH);
		obstacles = new ObstacleController(gameUI,3);
		enemy = new EnemyController(gameUI,3);
		
	}
		

	public void update() {
		bg1.update();
		bg2.update();
		obstacles.update();
		enemy.update();
		Updater.update();
	}
	
	public void draw(Graphics2D g2) {
		bg1.draw(g2);
		bg2.draw(g2);
		Render.draw(g2);
	}

}
