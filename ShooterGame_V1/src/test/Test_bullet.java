package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.JetFighter;
import core.Bullet;
import core.Enemy;
import core.Entity;
import globalData.Render;
import globalData.Updater;
import main.EnergyBar;
import main.GameUI;
import main.Main;

public class Test_bullet extends TestBase{

    @Test
    public void testDefaultSetting() throws IOException {
    	GameUI gameUI = new GameUI();
        Entity entity = new Entity(gameUI);
        int x = 10;
        int y = 20;
    	Bullet bullet = new Bullet(gameUI, x, y, entity);
        bullet.defaultSetting();

        // Verify that the width, height, speed, and AP values are set to their default values
        assertEquals(x, bullet.getX(),0);
        assertEquals(y, bullet.getY(),0);
        assertEquals("bullet", bullet.getID());
        assertEquals( 4, bullet.getWidth(),0);
        assertEquals(12, bullet.getHeight(),0);
        assertEquals(12, bullet.getSpeed(),0);
        assertEquals(1, bullet.getAP(),0);
    }

    @Test
    public void testGetXY() throws IOException {
    	GameUI gameUI = new GameUI();
        Entity entity = new Entity(gameUI);
        int x = 10;
        int y = 20;
    	Bullet bullet = new Bullet(gameUI, x, y, entity);

        // Verify that the width, height, speed, and AP values are set to their default values
        assertEquals(x, bullet.getX(),0);
        assertEquals(y, bullet.getY(),0);
    }

    @Test
    public void testGetBulletID() throws IOException {
    	GameUI gameUI = new GameUI();
        Entity entity = new Entity(gameUI);
        int x = 10;
        int y = 20;
    	Bullet bullet = new Bullet(gameUI, x, y, entity);
        assertEquals("bullet", bullet.getID());
    }


    @Test
    public void testGetRenderable() throws IOException {
    	GameUI gameUI = new GameUI();
        Entity entity = new Entity(gameUI);
        int x = 10;
        int y = 20;
    	Bullet bullet = new Bullet(gameUI, x, y, entity);
        assertEquals(bullet, bullet.getRenderable());
    }

    @Test
    public void testUpdate_YGreaterThanZero() throws IOException {
    	GameUI gameUI = new GameUI();
        Entity entity = new Entity(gameUI);
        int x = 0;
        int y = 100;
    	Bullet bullet = new Bullet(gameUI, x, y, entity);

        // Update the bullet
        bullet.update();

        // Verify that the y value decreases by the correct speed
        assertEquals( 100 - bullet.getSpeed(), bullet.getY(),0);
    }

    @Test
    public void testUpdate_YEqualsZero() throws IOException {
    	GameUI gameUI = new GameUI();
        JetFighter jet = new JetFighter(gameUI,gameUI.keyHandler,new EnergyBar());
        int x = 0;
        int y = -1;
    	Bullet bullet = new Bullet(gameUI, x, y, jet);
        // Update the bullet
        bullet.update();

        // Verify that the bullet is removed from the Render and Update lists
        assertEquals( Render.containsRenderableObject(bullet), false);
        //assertEquals( Updater.containsUpdateableObject(bullet),false);
    }

       @Test
       public void testCheckCollision() throws IOException, InterruptedException {
    	   
    	   	Bullet bullet;
    	   	Main.initScreen();
   			Main.initThread();
   			Main.gameUI.gameStart();
   			while(Main.gameUI.lv == null){Thread.sleep(100);}
   			
   			JetFighter jet = Main.gameUI.lv.getJet();
   			new Enemy(Main.gameUI,(int)jet.getX(),(int)jet.getY()-100,"enemy01");
   			bullet = new Bullet(Main.gameUI, (int)jet.getX(), (int)jet.getY(), jet);
   			Thread.sleep(1000);
   			
   			assertEquals( Render.containsRenderableObject(bullet), false);
   			assertEquals( Updater.containsUpdateableObject(bullet),false);
        }

}