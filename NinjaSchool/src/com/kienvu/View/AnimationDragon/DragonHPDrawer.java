package com.kienvu.View.AnimationDragon;

import com.kienvu.Model.Dragon;
import com.kienvu.Model.EnemyWarrior;
import com.kienvu.Model.GameObject;
import com.kienvu.Model.HPbar;
import com.kienvu.View.GameDrawer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Created by vukien on 21/05/2016.
 */
public class DragonHPDrawer extends GameDrawer {
    private Vector<BufferedImage> images;

    public DragonHPDrawer(Vector<BufferedImage> images) {
        this.images = images;
    }

    @Override
    public void paint(Graphics g, GameObject gameObject) {
        if (images != null) {
            Dragon dragon = (Dragon) gameObject;
            HPbar hPbar = dragon.gethPbar();
            int totalLevel = images.size();
            int hp = dragon.getHp();
            int hpLevel = (int)(totalLevel *(double)hp / EnemyWarrior.DEFAULT_HP);
            hpLevel = (hpLevel - 1)%totalLevel;
            //System.out.println("hpLevel: " + hpLevel);
            if (hpLevel >= 0) {
                Image image = images.get(hpLevel);
                g.drawImage(image, hPbar.getX(), hPbar.getY(), hPbar.getWidth(), hPbar.getHeight(),  null);
                //System.out.println("hpLevel: " + hpLevel);
            }

        }
    }
}
