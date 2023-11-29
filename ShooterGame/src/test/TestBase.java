package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import globalData.Render;
import globalData.Updater;
import main.Main;

public class TestBase {
	
	@BeforeEach
	public void setUp() {
		System.out.println("setUp");
			
		while(Main.gameUI != null) {
			Main.gameUI.gameThread = null;
			Main.gameUI = null;
			Main.window.dispose();
		}
		
		Render.clearRenderableObject();
		Updater.ClearAllUpdateableObjects();
		
	}
}
