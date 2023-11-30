package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import core.JetFighter;
import globalData.Render;
import globalData.Updater;
import main.EnergyBar;
import main.Main;

public class Test_energyBar extends TestBase{
	
	@Test
	public void test_energyBarCanFull() throws Exception
	{
		Main.initScreen();
		Main.initThread();
		Main.gameUI.gameStart();
		while(Main.gameUI.lv == null){Thread.sleep(100);}
	
		EnergyBar energyBar = Main.gameUI.lv.getJet().getEnergyBar();
		while(!energyBar.getEnergyIsFull()) {
			//System.out.println(energyBar.getEnergy());
			Thread.sleep(100);
		};		
		
		boolean result = energyBar.getEnergyIsFull();
		assertEquals(result, true);
	}
	
	@Test
	public void test_energyBarReset() throws Exception
	{	
		Main.initScreen();
		EnergyBar energyBar = new JetFighter(Main.gameUI,Main.gameUI.keyHandler,new EnergyBar()).getEnergyBar();
		
		energyBar.resetEnergyBar();
		
		boolean result = energyBar.getEnergyIsFull();
		assertEquals(result,false);
	}
	
	@Test
	public void test_energyBarState() throws Exception
	{	

		Main.initScreen();
		EnergyBar energyBar = new JetFighter(Main.gameUI,Main.gameUI.keyHandler,new EnergyBar()).getEnergyBar();
		
		energyBar.setEnergyBarStateCanStore(false);
		boolean result = energyBar.getEnergyBarStateCanStore();
		
		assertEquals(result,false);
	}
}
