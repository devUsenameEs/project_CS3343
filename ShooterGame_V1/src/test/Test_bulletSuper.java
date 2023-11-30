package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.JetFighter;
import core.Bullet;
import core.Bullet_Super;
import core.Enemy;
import core.Entity;
import globalData.Render;
import globalData.Updater;
import main.EnergyBar;
import main.GameUI;
import main.Main;

public class Test_bulletSuper extends TestBase{

    @Test
    public void testDefaultSetting() throws IOException {
        
    	GameUI gameUI = new GameUI();
        Entity entity = new Entity(gameUI);
        JetFighter jet = new JetFighter(gameUI,gameUI.keyHandler,new EnergyBar());
        int x = 10;
        int y = 20;
        int width = 70;
        int height = 20;
        int speed = 4;
        int AP = 2; 
    	Bullet_Super bulletSuper = new Bullet_Super(gameUI, x, y, entity);
        bulletSuper.defaultSetting();

        // Verify that the width, height, speed, and AP values are set to their default values
        assertEquals(width, bulletSuper.getWidth(),(int)jet.getX());
        assertEquals(height, bulletSuper.getHeight(),(int)jet.getY());
        assertEquals(speed, bulletSuper.getSpeed(),0);
        assertEquals(AP, bulletSuper.getAP(),0);
    }

    

}