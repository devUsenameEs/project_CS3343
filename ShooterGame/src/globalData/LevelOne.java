package globalData;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import core.*;
import main.*;

public class LevelOne implements Level{
	
	//DefaultSetting
	private GameUI gameUI;
	private KeyHandler keyH;
	private int x,y;
	
	//My Setting
	private Background bg1;
	private Background bg2;
	private JetFighter jet;
	
	//Constructor
	public LevelOne(GameUI gameUI,KeyHandler keyH){
		Render.clearRenderableObject();
		this.gameUI = gameUI;
		this.keyH = keyH;
		LevelDesign();
	}

	//Change Level
	public void changeLevel() {
		//
		gameUI.changeLevel(new Boss());
	}
	
	//LevelSetting_Design
	public void LevelDesign(){
		bg1 = new Background(gameUI,0, 0, "/background/space_bg.jpg");
		bg2 = new Background(gameUI,0, (-Constant.screenHeight),"/background/space_bg.jpg");
		jet = new JetFighter(gameUI,keyH);
		roundDesign();
	}
		

	public void update() {
		if(gameUI.gameState == gameUI.playState) {
			bg1.update();
			bg2.update();
			Updater.update();
			if (jet.getHP() == 0) gameUI.gameState = gameUI.deadState;
		}
	}
	
	public void draw(Graphics2D g2) {
		bg1.draw(g2);
		bg2.draw(g2);
		Render.draw(g2);
	}
	
	public void roundDesign() {
		//Round
		int eSize = 32;
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		int length = 76;
		
		InputStream is = getClass().getResourceAsStream("/enemy/enemy_round01.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while(col < Constant.maxScreenCol && row < length) {
			String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			while(col < Constant.maxScreenCol){
				String numbers[] = line.split(" ");
				int num = Integer.parseInt(numbers[col]);
				switch(num) {
					case(0): break;
					case(1): new Obstacle(gameUI,x,y,eSize,eSize);break;
					case(2): new Enemy(gameUI,x,y,eSize,eSize); break;
				}
				col++;
				x += eSize;
			}
			if(col == Constant.maxScreenCol) {
				x=  0 ;
				y -= eSize;
				col = 0;
				row++;
			}
		}
	}

}
