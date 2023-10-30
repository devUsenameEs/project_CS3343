package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		//Create The Window Frame
		JFrame window = new JFrame();
		window.setTitle("CityU Shooter Game");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
			
		GameUI gameUI = new GameUI();
		window.add(gameUI);	
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		//Looping Game
		gameUI.startGameThread();
	}
}
