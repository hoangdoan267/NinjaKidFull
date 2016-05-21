package com.kienvu.View.AnimationNinja;

import com.kienvu.Model.ArmorBar;
import com.kienvu.Model.GameObject;
import com.kienvu.Model.Ninja;
import com.kienvu.View.GameDrawer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Created by Trà Đá on 5/21/2016.
 */
public class NinjaArmorDrawer extends GameDrawer {
//    private final int X = 20;
//    private final int Y = 20;
    private Vector<BufferedImage> imageVector;

    public NinjaArmorDrawer(Vector<BufferedImage> imageVector) {
        this.imageVector = imageVector;

    }

    @Override
    public void paint(Graphics g, GameObject gameObject) {
        if(imageVector != null) {
            Ninja ninja = (Ninja)gameObject;
            ArmorBar armorBar = ninja.getArmorBar();
            int ArmorTotalLevel = imageVector.size();

            int armor = ninja.getArmor();

            int armorLevel = (int)(ArmorTotalLevel * (double)armor / Ninja.DEFAULT_ARMOR);

            armorLevel = (armorLevel - 1) % ArmorTotalLevel;

            if(armorLevel >= 0){
                Image image = imageVector.get(armorLevel);
                g.drawImage(image, armorBar.getX(), armorBar.getY(), armorBar.getWidth(), armorBar.getHeight(), null);
            }


        }
    }
}
