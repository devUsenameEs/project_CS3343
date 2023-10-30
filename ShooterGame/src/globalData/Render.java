package globalData;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

public class Render {
	private static ArrayList<Renderable> renderList = new ArrayList<Renderable>();

	public static void draw(Graphics2D g2) {
		for(Renderable o: renderList)
			o.draw(g2);
	}
	
	public static void addRenderableObject(Renderable o) {
		renderList.add(o);
		Collections.sort(renderList);
	}
	
	public static void removeRenderableObject(Renderable o) {
		renderList.remove(o);
	}
	
	public static void clearRenderableObject() {
		renderList.clear();
	}
}
