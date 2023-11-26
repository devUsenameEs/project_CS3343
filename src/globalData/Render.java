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
	public static int getNum() {
		return renderList.size() ;
	}
	public static boolean RenderListIsEmpty() {
		if(renderList.size() == 1)
			return true;
		return false;
	}
	public static boolean containsRenderableObject(Renderable o) {
		return renderList.contains(o);
	}
}
