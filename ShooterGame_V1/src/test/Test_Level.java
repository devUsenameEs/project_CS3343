package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import core.JetFighter;
import globalData.Level;
import globalData.Level_Boss;
import globalData.Level_One;
import globalData.Level_Transition;
import globalData.Render;
import globalData.Updater;
import main.EnergyBar;
import main.Main;

public class Test_Level extends TestBase{
	
	@Test
	public void test_LevelOne() throws IOException, InterruptedException {
		
		Main.initScreen();
		Main.initThread();
		Main.gameUI.gameStart();
		
		//Level One
		while(Main.gameUI.lv == null){Thread.sleep(100);}
		assertTrue(Main.gameUI.lv instanceof Level_One);
		assertEquals(Main.gameUI.lv.getScore(),Main.gameUI.lv.getJet().getScore());
		
		//Level Transition
		while(Render.RenderListIsEmpty() != 0) {
			Render.clearRenderableObject();
		}
		JetFighter jet = new JetFighter(Main.gameUI,Main.gameUI.keyHandler,new EnergyBar());
		Main.gameUI.update();
		assertTrue(Main.gameUI.lv instanceof Level_Transition);
		assertEquals(Main.gameUI.lv.getScore(),Main.gameUI.lv.getJet().getScore());
		clearAll();
	}
	
	@Test
	public void test_LevelBoss() throws IOException, InterruptedException {
		Main.initScreen();
		Main.initThread();
		Main.gameUI.gameState = Main.gameUI.playState;
		//Level Boss
		Level level = new Level_Transition(Main.gameUI,Main.gameUI.keyHandler,new JetFighter(Main.gameUI,Main.gameUI.keyHandler,new EnergyBar()),1);
		Main.gameUI.changeLevel(level);
		Level_Transition level_ = (Level_Transition)level;
		while(!level_.getTimer().TimeToZero()) {
			Thread.sleep(100);
		}
		Thread.sleep(1000);
		assertTrue(Main.gameUI.lv instanceof Level_Boss);
		assertEquals(Main.gameUI.lv.getScore(),Main.gameUI.lv.getJet().getScore());
		clearAll();
	}

	public void clearAll() {
		System.out.println("BeforeNotWork,UseClearAllMethod");
		while(Main.gameUI != null) {
			if(Main.gameUI.lv !=null)
				Main.gameUI.lv = null;
			if(Main.gameUI.gameThread!=null)
				Main.gameUI.gameThread.interrupt();
			Main.gameUI = null;
			
			if(Main.window != null)
				Main.window.dispose();
		}
		
		Render.clearRenderableObject();
		Updater.ClearAllUpdateableObjects();
		System.out.println("GameUI:"+ Main.gameUI + "\n");
	}
	
}
