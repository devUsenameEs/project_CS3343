package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import core.Enemy_Boss;
import globalData.Constant;
import globalData.Level;
import globalData.Level_Boss;
import globalData.Level_Transition;
import globalData.Render;
import globalData.Updater;
import main.Main;

public class Test_Main{

	
		@Test
		public void test_main() throws Exception
		{
			Main main = new Main();
			Main.main(null);
		}
		
		@Test
		public void test_render() throws Exception
		{
			Render render = new Render();
		}
		
		@Test
		public void test_update() throws Exception
		{
			Updater updater = new Updater();
		}
		
		@Test
		public void test_constant() throws Exception
		{
			Constant constant = new Constant();
		}
}
