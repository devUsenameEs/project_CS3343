package core;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import globalData.Constant;
import main.GameUI;


public class Heart{
	
	private JetFighter jet;
	private BufferedImage heartHalf,heartFull;
	
	public Heart(JetFighter jet) {
		this.jet = jet;
		getImage();
	}

	public void getImage(){
		try {
			heartHalf  = ImageIO.read(getClass().getResourceAsStream("/heart/heart_half.png"));
			heartFull  = ImageIO.read(getClass().getResourceAsStream("/heart/heart_full.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		int x = Constant.tileSize/2;
		int y = Constant.screenHeight - Constant.tileSize;
		int width = Constant.tileSize/2;
		int height = Constant.tileSize/2;
		int i = 0;
		
		while(i < jet.HP) {
			g2.drawImage(heartHalf, x, y, width,height,null);
			i++;
			if(i < jet.HP) {
				g2.drawImage(heartFull, x,y,width,height,null);
			}
			i++;
			x += Constant.tileSize;
		}
	}
}
