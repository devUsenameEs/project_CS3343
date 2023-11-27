package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import core.JetFighter;
import globalData.Updater;
import main.EnergyBar;
import main.Main;

public class TestJet {
	
	@Test
	public void test_getHurt() throws Exception
	{
		JetFighter jet;

		Main.init();
		jet = new JetFighter(Main.gameUI,Main.gameUI.keyHandler);
		jet.setHurt(false);
		
		jet.getHurt();
		boolean result = jet.isGetHurt();
		assertEquals(result, true);
	}
	
	@Test
	public void test_addScoreWithNotContinuously() throws Exception
	{
		JetFighter jet;

		Main.init();
		
		jet = new JetFighter(Main.gameUI,Main.gameUI.keyHandler);
		jet.setScore(0);
		jet.addScore(10);
		
		int result = jet.getScore();
		assertEquals(result, 10);
	}
	
	@Test
	public void test_addScoreWithContinuously() throws Exception
	{
		JetFighter jet;

		Main.init();
		jet = new JetFighter(Main.gameUI,Main.gameUI.keyHandler);
		jet.setScore(10);
		jet.setContin(true);
		jet.addScore(20);
		
		int result = jet.getScore();
		assertEquals(result, 31);
	}
	
	@Test
	public void test_energyBarCanFull() throws Exception
	{
		Main.init();
		JetFighter jet = new JetFighter(Main.gameUI,Main.gameUI.keyHandler);
		EnergyBar energyBar = jet.getEnergyBar();	
		
		while(!energyBar.getEnergyIsFull()) {
			Updater.update();
		};		
		boolean result = energyBar.getEnergyIsFull();
		assertEquals(result, true);
	}
	
	@Test
	public void test_energyBarReset() throws Exception
	{	
		JetFighter jet;
		EnergyBar energyBar;
		Main.init();
		
		jet = new JetFighter(Main.gameUI,Main.gameUI.keyHandler);
		energyBar = jet.getEnergyBar();
		
		energyBar.resetEnergyBar();
		boolean result = energyBar.getEnergyIsFull();
		assertEquals(result,false);
	}
	
	@Test
	public void test_energyBarState() throws Exception
	{	
		JetFighter jet;
		EnergyBar energyBar;
		Main.init();
		
		jet = new JetFighter(Main.gameUI,Main.gameUI.keyHandler);
		energyBar = jet.getEnergyBar();
		
		energyBar.setEnergyBarStateCanStore(false);
		boolean result = energyBar.getEnergyBarStateCanStore();
		
		assertEquals(result,false);
	}
	

	
	
}
