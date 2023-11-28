package globalData;

import java.util.ArrayList;

public interface Updateable {
	public void update();
	public String getID();
	public Renderable getRenderable();
	
	public default Updateable isColliding(Renderable thisObject, String otherObjID) {
		ArrayList<Updateable> objects = Updater.getUpdateableObjects();
		System.out.println("Check: " + objects.isEmpty());
		for(Updateable o: objects) {
			if(o.getID() == otherObjID)
				if(thisObject.getX() < o.getRenderable().getX() + o.getRenderable().getWidth() && thisObject.getX() + thisObject.getWidth() > o.getRenderable().getX())
					if(thisObject.getY() < o.getRenderable().getY() + o.getRenderable().getHeight() && thisObject.getY() + thisObject.getHeight() > o.getRenderable().getY())
						return o;
		}
		return null;
	}
}
