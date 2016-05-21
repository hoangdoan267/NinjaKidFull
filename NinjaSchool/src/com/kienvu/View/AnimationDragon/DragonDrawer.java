package com.kienvu.View.AnimationDragon;

import com.kienvu.View.AnimationGameDrawer;

import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Created by vukien on 21/05/2016.
 */
public class DragonDrawer extends AnimationGameDrawer {
    public DragonDrawer(Vector<BufferedImage> imageVect) {
        super(imageVect);
        this.delay = 50;
    }
}
