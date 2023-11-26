package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import javax.swing.JPanel;

import core.Background;
import globalData.*;

public class GameUI extends JPanel implements Runnable{

	private static final long serialVersionUID = 1L;
	//Set Value
	public Thread gameThread;
	public KeyHandler keyHandler = new KeyHandler(this);
	public Level lv;
	public int commandNum;
	public Graphics2D g2;
	public Font yellowStyle;
	//Game state
	public final int titleState = 0;
	public final int playState = 1;
	public final int deadState = 2;	
	public final int winState = 3;
	public boolean startGameSetting;
	public int gameState;
	//Game Background
	private Background start_bg;
	private Background bg1;
	private Background bg2;
	
	//Constructor Setting
	public GameUI() {
		this.setPreferredSize(new Dimension(Constant.screenWidth,Constant.screenHeight));
		this.setBackground(Color.BLACK);
		this.start_bg = new Background(this,0, 0, "/background/start_bg.jpg");
		this.bg1 = new Background(this,0, 0, "/background/code_bg.jpg");
		this.bg2 = new Background(this,0, (-Constant.screenHeight),"/background/code_bg.jpg");
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
		this.gameState = titleState;
		this.commandNum = 0;
	}
	
	//Change Game Level
	public void changeLevel(Level lv) {
		this.lv = lv;
	}
	
	//Update The Window
	public void update() {
		if(gameState == playState && lv != null) {
			bg1.update();
			bg2.update();
			lv.update();
		}
	}
	
	//Drawing The Window
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D)g;

		try {
			InputStream is = getClass().getResourceAsStream("/font/Retro.ttf");
			yellowStyle = Font.createFont(Font.TRUETYPE_FONT,is);
		}catch (FontFormatException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		g2.setFont(yellowStyle);
		
		if(gameState == titleState) drawTitleScreen();
		if(gameState == playState) drawPlayScreen();
		if(gameState == deadState) drawEndScreen();
		if(gameState == winState) {
			/*
			 * 			try {
				wait(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 */

			drawWinnerScreen();
		}
		g2.dispose();
	}
	
	//Draw TitleScreen
	public void drawTitleScreen() {
		
		start_bg.draw(g2);
		
		//Draw Title Text "CityU CS Shooting Game"
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,33F));
		String text1 = "CityU CS";
		drawText(text1,getCenterText(text1),120,true);
		String text2 = "Shooting Game";
		drawText(text2,getCenterText(text2),160,true);
		
		//Draw Option Text "Start" and "Quit"
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
		String text3 = "Start Game";
		drawText(text3,getCenterText(text3),300,false);
		String text4 = "Quit Game";
		drawText(text4,getCenterText(text4),350,false);
			
		//Draw Arrow
		int length = (int)g2.getFontMetrics().getStringBounds(text4, g2).getWidth() - 130;
		if(commandNum == 0 ) drawText(">",length,300,false);
		if(commandNum == 1 ) drawText(">",length,350,false);
	}

	//Draw PlayScreen
	public void drawPlayScreen() {
		if(startGameSetting) {
			System.out.print("NEW GAME");
			lv = new Level_One(this,keyHandler);
			startGameSetting = false;
		}
		if(lv != null ) {
			bg1.draw(g2);
			bg2.draw(g2);
			lv.draw(g2);
		}
	}
	
	//Start The Game
	public void gameStart() {
		startGameSetting = true;
		Render.clearRenderableObject();
		Updater.ClearAllUpdateableObjects();
		lv = null;
		gameState = playState;
	}
	
	//Draw EndScreen
	public void drawEndScreen() {
		
		start_bg.draw(g2);
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
		
		text = "You Are Die...";
		g2.setColor(Color.white);
		g2.drawString(text,getCenterText(text)-3,132);
		g2.setColor(Color.red);
		g2.drawString(text,getCenterText(text),130);
		text = "Total Score: "; 
		g2.setColor(Color.white);
		g2.drawString(text,getCenterText(text)-3,182);
		g2.setColor(Color.magenta);
		g2.drawString(text,getCenterText(text),180);
		text = "" + lv.getScore();
		g2.setColor(Color.white);
		g2.drawString(text,getCenterText(text)-3,232);
		g2.setColor(Color.magenta);
		g2.drawString(text,getCenterText(text),230);
		text = "Try Again? ^^";
		drawText(text,getCenterText(text),280,true);		
		
		//Draw Option Text "Start" and "Quit"
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,25F));
		text = "Restart Game";
		drawText(text,getCenterText(text),360,false);
		text = "Quit Game";
		drawText(text,getCenterText(text),400,false);
		
		//Draw Arrow
		int length = (int)g2.getFontMetrics().getStringBounds("Restart Game", g2).getWidth() - 160;
		if(commandNum == 0 ) drawText(">",length,360,false);
		if(commandNum == 1 ) drawText(">",length,400,false);		
	}
	
	//Draw WinnerScreen
	public void drawWinnerScreen() {
		start_bg.draw(g2);
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
		
		text = "Congratulation!!!";
		drawText(text,getCenterText(text),100,true);
		text = "You are Win!";
		drawText(text,getCenterText(text),150,true);
		text = "Total Score: "; 
		g2.setColor(Color.white);
		g2.drawString(text,getCenterText(text)-3,222);
		g2.setColor(Color.magenta);
		g2.drawString(text,getCenterText(text),220);
		text = "" + lv.getScore();
		g2.setColor(Color.white);
		g2.drawString(text,getCenterText(text)-3,282);
		g2.setColor(Color.magenta);
		g2.drawString(text,getCenterText(text),280);
		
		//Draw Option Text "Start" and "Quit"
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,25F));
		text = "Play Again";
		drawText(text,getCenterText(text),360,false);
		text = "Quit Game";
		drawText(text,getCenterText(text),400,false);
				
		//Draw Arrow
		int length = (int)g2.getFontMetrics().getStringBounds("Restart Game", g2).getWidth() - 160;
		if(commandNum == 0 ) drawText(">",length,360,false);
		if(commandNum == 1 ) drawText(">",length,400,false);
	}
	
	//Get The X Position Of Text in Center
	public int getCenterText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = Constant.screenWidth/2 - length/2;
		return x;
	}
	
	//Draw text
	private void drawText(String text, int x, int y, boolean needShadow) {
		int shadow = 3;
		if(needShadow) {
			g2.setColor(Color.gray);
			g2.drawString(text,x-shadow,y+shadow);
		}
		g2.setColor(Color.white);
		g2.drawString(text,x,y);
	}
	
	//Return GameState
	public int getGameState() {return gameState;}
	
	
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
