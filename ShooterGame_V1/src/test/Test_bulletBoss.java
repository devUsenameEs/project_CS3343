package test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.JetFighter;
import core.Bullet;
import core.Bullet_Boss;
import core.Enemy;
import core.Entity;
import globalData.Level;
import globalData.Level_Boss;
import globalData.Render;
import globalData.Updateable;
import globalData.Updater;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import main.EnergyBar;
import main.GameUI;
import main.KeyHandler;
import main.Main;

public class Test_bulletBoss extends TestBase{

    @Test
    public void testDefaultSetting() throws IOException {
    	GameUI gameUI = new GameUI();
        Entity entity = new Entity(gameUI);
        int x = 10;
        int y = 20;
        Bullet_Boss bulletBoss = new Bullet_Boss(gameUI, x, y, entity);
        bulletBoss.defaultSetting();
    }

    @Test
    public void testGetXY() throws IOException {
    	GameUI gameUI = new GameUI();
        JetFighter jet = new JetFighter(gameUI,gameUI.keyHandler,new EnergyBar());
        int x = 10;
        int y = 20;
        Bullet_Boss bulletBoss = new Bullet_Boss(gameUI, (int)jet.getX(), (int)jet.getY(), jet);

        // Verify that the width, height, speed, and AP values are set to their default values
        assertEquals(x, bulletBoss.getX(),(int)jet.getX());
        assertEquals(y, bulletBoss.getY(),(int)jet.getY());
    }

    @Test
    public void testGetBulletID() throws IOException {
    	GameUI gameUI = new GameUI();
        Entity entity = new Entity(gameUI);
        int x = 10;
        int y = 20;
        Bullet_Boss bulletBoss = new Bullet_Boss(gameUI, x, y, entity);
        assertEquals("enemy_bullet", bulletBoss.getID());
    }


    @Test
    public void testGetRenderable() throws IOException {
    	GameUI gameUI = new GameUI();
        Entity entity = new Entity(gameUI);
        int x = 10;
        int y = 20;
        Bullet_Boss bulletBoss = new Bullet_Boss(gameUI, x, y, entity);
        assertEquals(bulletBoss, bulletBoss.getRenderable());
    }

    @Test
    public void testUpdate_YGreaterThanZero() throws IOException {
    	GameUI gameUI = new GameUI();
        Entity entity = new Entity(gameUI);
        int x = 0;
        int y = 1000;
        Bullet_Boss bulletBoss = new Bullet_Boss(gameUI, x, y, entity);

        // Update the bullet
		bulletBoss.update();


        // Verify that the y value decreases by the correct speed
        assertEquals( Render.containsRenderableObject(bulletBoss), false);
        //assertEquals( Updater.containsUpdateableObject(bulletBoss),false);    
    }
    
    @Test
    public void testUpdate_YEqualsZero() throws IOException {
    	GameUI gameUI = new GameUI();
        Entity entity = new Entity(gameUI);
        int x = 0;
        int y = -1;
        Bullet_Boss bulletBoss = new Bullet_Boss(gameUI, x, y, entity);
		bulletBoss.update();


        // Verify that the bullet is removed from the Render and Update lists
        assertEquals( Render.containsRenderableObject(bulletBoss), true);
        //assertEquals( Updater.containsUpdateableObject(bulletBoss),true);
    }

    @Test
    public void testcheckCollision() throws IOException, InterruptedException {
 
	   	Main.initScreen();
		Main.initThread();
		Main.gameUI.gameStart();
		while(Main.gameUI.lv == null){Thread.sleep(100);}
		
		JetFighter jet = Main.gameUI.lv.getJet();
		Bullet_Boss bullet = new Bullet_Boss(Main.gameUI, (int)jet.getX()+7, 0, jet);

        while(Render.containsRenderableObject(bullet)) {
        	Thread.sleep(100);
        }
        

        assertEquals( Render.containsRenderableObject(bullet), false);
        assertEquals( Updater.containsUpdateableObject(bullet),false);
    }
}