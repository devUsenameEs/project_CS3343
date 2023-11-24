package globalData;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import core.*;
import main.*;

public class Level_One implements Level{
	
	//DefaultSetting
	private GameUI gameUI;
	private KeyHandler keyH;
	private JetFighter jet;
	
	//Constructor
	public Level_One(GameUI gameUI,KeyHandler keyH){
		Render.clearRenderableObject();
		Updater.ClearAllUpdateableObjects();
		this.gameUI = gameUI;
		this.keyH = keyH;
		LevelDesign();
	}

	
	@Override
	public void LevelDesign(){	
			jet = new JetFighter(gameUI,keyH);
			//Setting Map
			int eSize = 32;
			int col = 0;
			int row = 0;
			int x = 0;
			int y = 0;
			int length = 101;
			InputStream is = getClass().getResourceAsStream("/enemy/enemy_round01.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			while(col < Constant.maxScreenCol && row < length) {
				System.out.println("Drawing Level One Map");
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
						case(2): new Obstacle(gameUI,x,y,"obstacle03");break;
						case(3): new Enemy(gameUI,x,y,"enemy01"); break;
						case(4): new Enemy(gameUI,x,y,"enemy02"); break;
						case(5): new Buff(gameUI,x,y); break;
						case(6): new Debuff(gameUI,x,y); break;
						case(7): new Buff_Heart(gameUI,x,y); break;
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
	public void update() {
		Updater.update();
		if(Render.RenderListIsEmpty()) {
			gameUI.changeLevel(new Level_Transition(gameUI,keyH,jet));
		}
	}
	
	@Override
	public void draw(Graphics2D g2) {
		Render.draw(g2);
	}
	
	@Override
	public int getScore() {
		return jet.getScore();
	}
	

}
