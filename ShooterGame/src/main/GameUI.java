package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import globalData.*;

public class GameUI extends JPanel implements Runnable{
	
	//Set Value
	private Thread gameThread;
	private KeyHandler keyHandler = new KeyHandler();
	public Level lv = new LevelOne(this,keyHandler);
	
	//Construstor Setting
	public GameUI() {
		this.setPreferredSize(new Dimension(Constant.screenWidth,Constant.screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}
	
	//Change Game Level
	public void changeLevel(Level lv) {
		this.lv = lv;
	}
	
	//Update The Window
	public void update() {
		lv.update();
	}
	
	//Drawing The Window
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		lv.draw(g2);
		g2.dispose();
	}
	
	//GameThread
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
		double drawInterval = 1000000000/Constant.FPS; //60FPS
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if(delta>=1) {
				update();
				repaint();
				delta--;
			}
		}
	}
	
	
	
}
