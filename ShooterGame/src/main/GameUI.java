package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import globalData.*;

public class GameUI extends JPanel implements Runnable{

	private static final long serialVersionUID = 1L;
	//Set Value
	private Thread gameThread;
	private KeyHandler keyHandler = new KeyHandler();
	public Level lv = new LevelOne(this,keyHandler);
	public int commandNum;
	//Game state
	public final int deadState = 2;
	public final int playState = 1;
	public final int titleSate = 0;
	public int gameState;
	
	//Construstor Setting
	public GameUI() {
		this.setPreferredSize(new Dimension(Constant.screenWidth,Constant.screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
		this.gameState = titleSate;
		this.commandNum = 0;
	}
	
	//Change Game Level
	public void changeLevel(Level lv) {
		this.lv = lv;
	}
	
	//Update The Window
	public void update() {
		if(gameState==playState)
			lv.update();
	}
	
	//Drawing The Window
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		//Draw Title Screen
		if(gameState == titleSate){
			Font font = new Font("Arial", Font.BOLD, 20);
			g2.setFont(font);
			String text = "CItyU Shooting Game";
			//Shadow
			g2.setColor(Color.gray);
			g2.drawString(text, Constant.screenWidth/2 - 98 ,102);
			//main text
			g2.setColor(Color.white);
			g2.drawString(text, Constant.screenWidth/2 -100 ,100);
			//image
			//g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
			//text = "(ﾟ皿ﾟ)ｒ┏┳－－－＊";
			//g2.drawString(text, x-5+120 ,y-5+150);
			if (keyHandler.upPressed == true) {	if (commandNum == 1) commandNum = 0; else commandNum = 1;}
			if (keyHandler.downPressed == true) { if (commandNum == 1) commandNum = 0; else commandNum = 1;}
			if(keyHandler.enterPressed == true){ if (commandNum == 0) gameState = playState; else System.exit(0);}			
			text = "Start";
			g2.drawString(text, Constant.screenWidth/2 - 20 ,300);
			if(commandNum == 0 )
				g2.drawString(">", Constant.screenWidth/2 - 40 ,300);
			
			text = "Quit";
			g2.drawString(text, Constant.screenWidth/2 - 20 ,350);
			if(commandNum == 1 )
				g2.drawString(">", Constant.screenWidth/2 - 40 ,350);
		}
		
		if(gameState == playState)
			lv.draw(g2);
		
		if(gameState == deadState) {
			Font font = new Font("Arial", Font.BOLD, 20);
			g2.setFont(font);
			String text = "You Died!!!";
			//Shadow
			g2.setColor(Color.gray);
			g2.drawString(text, Constant.screenWidth/2 - 98 ,102);
			//main text
			g2.setColor(Color.white);
			g2.drawString(text, Constant.screenWidth/2 -100 ,100);
		}
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
