package com.kienvu.View.AnimationNinja;

import com.kienvu.View.AnimationGameDrawer;

import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Created by NGUYEN DINH QUAN on 5/15/2016.
 */
public class RunRightNinjaDrawer extends AnimationGameDrawer {
    private static int DELAY_DEFAULT = 50;
    public RunRightNinjaDrawer(Vector<BufferedImage> imageVect) {
        super(imageVect);
        this.delay = DELAY_DEFAULT;
    }

}
