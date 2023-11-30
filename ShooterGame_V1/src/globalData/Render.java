package globalData;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

public class Render {
	private static ArrayList<Renderable> renderList = new ArrayList<Renderable>();
	private static Object renderListLock = new Object();
	
	public static void draw(Graphics2D g2) {
		synchronized(renderListLock) {
			for(Renderable o: renderList)
				o.draw(g2);
		}
	}
	
	public static void addRenderableObject(Renderable o) {
		synchronized(renderListLock) {
			renderList.add(o);
			Collections.sort(renderList);
		}
	}
	
	public static void removeRenderableObject(Renderable o) {
		synchronized(renderListLock) {
			renderList.remove(o);
		}
	}
	
	public static void clearRenderableObject() {
		synchronized(renderListLock) {
			renderList.clear();
		}
	}

	public static int RenderListIsEmpty() {
		return renderList.size();
	}
	
	public static boolean containsRenderableObject(Renderable o) {
		return renderList.contains(o);
	}
}
