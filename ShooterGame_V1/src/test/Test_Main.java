package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import globalData.Constant;
import globalData.Render;
import globalData.Updater;
import main.Main;
import main.Timer;

public class Test_Main extends TestBase{

	
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
		
		@Test
		public void getTimerRender() {
			Timer timer = new Timer(0);
			assertEquals(timer.getRenderable(), null);
		}
}
