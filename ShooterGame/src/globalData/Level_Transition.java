package globalData;

import java.awt.Color;
import java.awt.Graphics2D;
import core.JetFighter;
import main.GameUI;
import main.KeyHandler;
import main.Timer;

public class Level_Transition implements Level{
	
	//DefaultSetting
	private GameUI gameUI;
	private KeyHandler keyH;
	private JetFighter jet;
	private Timer timer;

	public Level_Transition(GameUI gameUI,KeyHandler keyH,JetFighter jet) {
		this.gameUI = gameUI;
		this.keyH = keyH;
		this.jet = jet;
		this.jet.changeEnergyBarState(false);
		LevelDesign();
	}
	
	@Override
	public void LevelDesign() {
		timer = new Timer(500);
	}

	@Override
	public void draw(Graphics2D g2) {
		Render.draw(g2);
		if(gameUI.gameState == gameUI.playState) {
			String text1 = "Waiting For The Boss.";
			String text2 = "Are YOU Ready...?";
			drawText(g2,text1,100);
			drawText(g2,text2,140);
		}
	}

	@Override
	public void update() {
		Updater.update();
		if(gameUI.gameState == gameUI.playState) {
			if(timer.TimeToZero()) {
				Updater.removeUpdateList(timer);
				gameUI.changeLevel(new Level_Boss(gameUI,keyH,jet));
			}
		}
	}

	@Override
	public int getScore() {
		return jet.getScore();
	}
	
	@Override
	public JetFighter getJet() {
		return jet;
	}
	
	public void drawText(Graphics2D g2,String text,int _y) {
		int shadow = 2;
		int x = Constant.screenWidth/2 - (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth()/2;
		int y = _y;
		g2.setColor(Color.gray);
		g2.drawString(text, x-shadow,y+shadow);
		g2.setColor(Color.white);
		g2.drawString(text, x,y);
	}
}
