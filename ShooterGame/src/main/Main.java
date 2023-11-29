package main;

import java.io.IOException;

import javax.swing.JFrame;

public class Main {
	
	public static GameUI gameUI;
	public static JFrame window;

	public static void main(String[] args) throws IOException {
		initScreen();
		initThread();
	}
	
	public static void initScreen() throws IOException {
		//Create The Window Frame
		window = new JFrame();
		window.setTitle("CityU Shooter Game");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setResizable(false);
			
		gameUI = new GameUI();
		window.add(gameUI);	
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);	
	}
	
	public static void initThread() {
		gameUI.startGameThread();
	}
}
