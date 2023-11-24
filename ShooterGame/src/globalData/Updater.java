package globalData;

import java.util.ArrayList;

public class Updater {
	private static ArrayList<Updateable> updateList = new ArrayList<Updateable>();
	private static ArrayList<Updateable> addUpdateList = new ArrayList<Updateable>();
	private static ArrayList<Updateable> removeUpdateList = new ArrayList<Updateable>();
	
	public static void update() {
		for(Updateable o: updateList)
			o.update();
		
		updateList.removeAll(removeUpdateList);
		updateList.addAll(addUpdateList);
		
		addUpdateList.clear();
		removeUpdateList.clear();
	}
	
	public static void addUpdateList(Updateable o) {
		addUpdateList.add(o);
	}
	
	public static void removeUpdateList(Updateable o) {
		removeUpdateList.add(o);
	}

	public static ArrayList<Updateable> getUpdateableObjects() {
		return updateList;
	}
	
	public static void ClearAllUpdateableObjects() {
		updateList.clear();
		addUpdateList.clear();
		removeUpdateList.clear();
	}

}
