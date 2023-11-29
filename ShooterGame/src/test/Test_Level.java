package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
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
import main.GameUI;
import main.Main;

public class Test_Level extends TestBase{
	
	@Test
	public void test_Level_Transition() throws IOException, InterruptedException {
		Main.initScreen();
		Main.initThread();
		Main.gameUI.gameStart();
		
		//Level One
		while(Main.gameUI.lv == null){Thread.sleep(100);}
		assertTrue(Main.gameUI.lv instanceof Level_One);
		
		//Level_transtion
		while(Render.RenderListIsEmpty() != 0) {
			Render.clearRenderableObject();
		}
		JetFighter jet = new JetFighter(Main.gameUI,Main.gameUI.keyHandler);
		assertTrue(Main.gameUI.lv instanceof Level_Transition);
		assertEquals(Main.gameUI.lv.getScore(),Main.gameUI.lv.getJet().getScore());
	}
	
	@Test
	public void test_Level_Boss() throws IOException, InterruptedException {
		Main.initScreen();
		Main.initThread();
		JetFighter jet = new JetFighter(Main.gameUI,Main.gameUI.keyHandler);
		Level level = new Level_Transition(Main.gameUI,Main.gameUI.keyHandler,jet,100);
		Main.gameUI.gameState = Main.gameUI.playState;
		
		while(! (Main.gameUI.lv instanceof Level_Boss)) {
			level.update();
		}
		
		Main.gameUI.lv.getJet();
		assertTrue(Main.gameUI.lv instanceof Level_Boss);
	}
	
	
	
}
