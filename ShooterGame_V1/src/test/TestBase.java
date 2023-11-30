package test;

import org.junit.Before;

import globalData.Render;
import globalData.Updater;
import main.Main;

public class TestBase {
	
	@Before
	public void setUp() {
		System.out.println("\n\nsetUp");
			
		while(Main.gameUI != null) {
			if(Main.gameUI.lv!=null)
				Main.gameUI.lv = null;
			if(Main.gameUI.gameThread!=null)
				Main.gameUI.gameThread.interrupt();
			Main.gameUI = null;
			
			if(Main.window != null)
				Main.window.dispose();
		}
		
		Render.clearRenderableObject();
		Updater.ClearAllUpdateableObjects();
		//System.out.println("Render Object Contains:"+Render.RenderListIsEmpty());
		System.out.println("GameUI:"+ Main.gameUI);
		
	}
}
