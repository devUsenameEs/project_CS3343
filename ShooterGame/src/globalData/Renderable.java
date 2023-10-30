package globalData;

import java.awt.Graphics2D;

public interface Renderable extends Comparable<Object>{
	public void draw(Graphics2D g2);
	public void reduceHP(double x);
	public void addHP(double x);
	
	public int getLayer();
	public double getX();
	public double getY();
	public double getWidth();
	public double getHeight();
	public double getHP();
	
	public default int compareTo(Object o) {
		Renderable object = (Renderable)o;	
		if(getLayer() < object.getLayer())
			return -1;
		else if (getLayer() > object.getLayer())
			return 1;
		else
			return 0;
	}
}
