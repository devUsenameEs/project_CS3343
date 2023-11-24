package main;

import javax.swing.JFrame;

public class Main {
	
	public static GameUI gameUI;

	public static void main(String[] args) {
		init();
	}
	
	public static void init() {
		//Create The Window Frame
		JFrame window = new JFrame();
		window.setTitle("CityU Shooter Game");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
			
		gameUI = new GameUI();
		window.add(gameUI);	
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		//Looping Game
		gameUI.startGameThread();
	}
}
