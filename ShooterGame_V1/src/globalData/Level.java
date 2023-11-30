package globalData;

import java.awt.Graphics2D;
import java.io.IOException;

import core.JetFighter;


public interface Level{
	public void LevelDesign() throws IOException;
	public void draw(Graphics2D g2);
	public void update() throws IOException;
	public int getScore();
	public JetFighter getJet();
}
