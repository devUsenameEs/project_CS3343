package globalData;

import java.awt.Graphics2D;

import core.JetFighter;


public interface Level{
	public void LevelDesign();
	public void draw(Graphics2D g2);
	public void update();
	public int getScore();
	public JetFighter getJet();
}
