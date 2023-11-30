package globalData;

import java.io.IOException;
import java.util.ArrayList;

public class Updater {
	private static ArrayList<Updateable> updateList = new ArrayList<Updateable>();
	private static ArrayList<Updateable> addUpdateList = new ArrayList<Updateable>();
	private static ArrayList<Updateable> removeUpdateList = new ArrayList<Updateable>();
	private static Object updateListLock = new Object();
	
	public static void update() throws IOException {
		synchronized(updateListLock) {
			for(Updateable o: updateList)
				o.update();
		
			updateList.removeAll(removeUpdateList);
			updateList.addAll(addUpdateList);
		
			addUpdateList.clear();
			removeUpdateList.clear();
		}
	}
	
	public static void addUpdateList(Updateable o) {
		synchronized(updateListLock) {
			addUpdateList.add(o);
		}
	}
	
	public static void removeUpdateList(Updateable o) {
		synchronized(updateListLock) {
			removeUpdateList.add(o);
		}
	}

	public static ArrayList<Updateable> getUpdateableObjects() {
		synchronized(updateListLock) {
			return updateList;
		}
	}
	
	public static void ClearAllUpdateableObjects() {
		synchronized(updateListLock) {
			updateList.clear();
			addUpdateList.clear();
			removeUpdateList.clear();
		}
	}
	
	public static boolean containsUpdateableObject(Updateable o) {
		return updateList.contains(o);
	}
}
