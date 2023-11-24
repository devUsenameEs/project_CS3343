package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import globalData.Constant;

public class Score {
	
	private int score;
	private double bouns;
	private boolean isContinuously;
	
	public Score() {
		score = 0;
		bouns = 0.05;
		isContinuously = false;
	}
	
	public void addScore(int x) {
		if(isContinuously) {
			score += (x*(1+bouns));
			bouns += 0.05;
		}
		else {
			isContinuously = true;
			bouns = 0.05;
			score += x;
		}
	}
	
	public void setContinuously(boolean n) {
		isContinuously = n;
	}
	
	public int getScore() {
		return score;
	}
	
	public void draw(Graphics2D g2) {
		String text = "Score: " + score;
		int x = Constant.tileSize/2;
		int y = Constant.screenHeight - Constant.tileSize - 8;
		g2.setFont(g2.getFont().deriveFont(12F));
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
	}
}
