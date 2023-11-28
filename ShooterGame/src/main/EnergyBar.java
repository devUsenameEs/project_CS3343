package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import globalData.Constant;

public class EnergyBar {
	private int energy;
	private boolean barIsFull;
	private Timer timer;
	private BufferedImage energy0,energy1,energy2,energy3,energy4,energy5;
	private int x,y,width,height;
	private boolean energyBarCanStore;
	
	public EnergyBar() throws IOException {
		energy = 0;
		barIsFull = false;
		energyBarCanStore = true;
		timer = new Timer(1500);
		width = 100;
		height = Constant.tileSize/2;
		x = Constant.tileSize/2;
		y = Constant.screenHeight - Constant.tileSize;
		getImage();
	}
	
	private void getImage() throws IOException {
		energy0 = ImageIO.read(getClass().getResourceAsStream("/energyBar/energy_bar0.png"));
		energy1 = ImageIO.read(getClass().getResourceAsStream("/energyBar/energy_bar1.png"));
		energy2 = ImageIO.read(getClass().getResourceAsStream("/energyBar/energy_bar2.png"));
		energy3 = ImageIO.read(getClass().getResourceAsStream("/energyBar/energy_bar3.png"));
		energy4 = ImageIO.read(getClass().getResourceAsStream("/energyBar/energy_bar4.png"));
		energy5 = ImageIO.read(getClass().getResourceAsStream("/energyBar/energy_bar5.png"));
	}

	public void draw(Graphics2D g2) {
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,15F));
		
		if(!barIsFull && energyBarCanStore) {
			switch(energy) {
				case 0: g2.drawImage(energy0, x, y, width,height,null);break;
				case 1: g2.drawImage(energy1, x, y, width,height,null);break;
				case 2: g2.drawImage(energy2, x, y, width,height,null);break;
				case 3: g2.drawImage(energy3, x, y, width,height,null);break;
				case 4: g2.drawImage(energy4, x, y, width,height,null);break;
			} 
		}
		else if(barIsFull) {
			
			g2.drawImage(energy5, x, y, width,height,null);
			if(energyBarCanStore) {
				String text = "Press Button V";
				int x = Constant.screenWidth/2 - (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth()/2;
				int y = 100;
				g2.setColor(Color.black);
				g2.drawString(text, x-3,y+3);
				g2.setColor(Color.white);
				g2.drawString(text, x,y);
				text = " to use super bullet!";
				x = Constant.screenWidth/2 - (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth()/2;
				y = 140;
				g2.setColor(Color.black);
				g2.drawString(text, x-3,y+3);
				g2.setColor(Color.white);
				g2.drawString(text, x,y);
			}
		}
	}
	
	public void update() {
		if(!barIsFull && timer.TimeToZero()&& energyBarCanStore) {
			energy++;
			timer.reset();
		}
		if(energy == 5) {
			barIsFull = true;
			energy = 0;
		}
	}
	
	public boolean getEnergyBarStateCanStore() {
		return energyBarCanStore;
	}
	
	public boolean getEnergyIsFull() {
		return barIsFull;
	}
	
	public void setEnergyBarStateCanStore(boolean b) {
		energyBarCanStore = b;
	}
	
	public void resetEnergyBar() {
		barIsFull = false;
	}
	
	public int getEnergy() {
		return energy;
	}
}
