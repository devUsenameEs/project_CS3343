package test;

import org.junit.jupiter.api.Test;

import main.Main;

public class TestMain {
	
		@Test
		public void test_main() throws Exception
		{
			Main main = new Main();
			main.main(null);
		}
}
