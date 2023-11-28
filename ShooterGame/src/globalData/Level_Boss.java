package globalData;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import core.*;
import main.*;

public class Level_Boss implements Level{
	//DefaultSetting
	private GameUI gameUI;
	private KeyHandler keyH;
	private JetFighter jet;
	private Enemy_Boss boss;

	
	//Constructor
	public Level_Boss(GameUI gameUI,KeyHandler keyH,JetFighter jet){
		this.gameUI = gameUI;
		this.keyH = keyH;
		this.jet = jet;
		jet.changeEnergyBarState(true);
		LevelDesign();
	}

	@Override
	public void draw(Graphics2D g2) {
		Render.draw(g2);
	}

	@Override
	public void update() {
		Updater.update();
		if(boss.changeToWinnerScreen())
			gameUI.gameState = gameUI.winState;
	}
	
	public void LevelDesign() { 
	    roundDesign();
	    int width = 100; int height = 100;
		int x = (Constant.screenWidth/2) - (width/2);
		int y = -height;
		boss = new Enemy_Boss(gameUI,x,y,"boss");
	}
	
	public void roundDesign() {
		//Round
		int eSize = 32;
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		int length = 70;
		
		InputStream is = getClass().getResourceAsStream("/resourse/enemy/enemy_boss.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while(col < Constant.maxScreenCol && row < length) {
			System.out.println("Drawing Level two Map");
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
					case(1): new Obstacle(gameUI,x,y,"obstacle02");break;
					case(2): new Enemy(gameUI,x,y,"enemy01"); break;
					case(3): new Enemy(gameUI,x,y,"enemy02"); break;
					case(4): new Buff(gameUI,x,y); break;
					case(5): new Debuff(gameUI,x,y); break;
					case(6): new Buff_Heart(gameUI,x,y); break;
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

	@Override
	public int getScore() {
		return jet.getScore();
	}
}
