package main;

import globalData.Renderable;
import globalData.Updateable;
import globalData.Updater;

public class Timer implements Updateable{
	public int interval = 0;
	int begginning_Interval;

	public Timer (int interval) {
		this.interval = interval;
		this.begginning_Interval = interval;
		Updater.addUpdateList(this);
	}
	
	public void update() {
		interval -= 10;
	}
	
	public boolean TimeToZero() {
		if(interval <= 0)
			return true;
		return false;
	}
	
	public void reset() {
		interval = begginning_Interval;
	}
	
	public int getTime() {
		return interval;
	}

	@Override
	public String getID() {
		return null;
	}

	@Override
	public Renderable getRenderable() {
		return null;
	}
}
