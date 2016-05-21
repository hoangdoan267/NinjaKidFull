package com.kienvu.View.AnimationDragon;

import com.kienvu.Model.GameObject;
import com.kienvu.View.GameDrawer;

import java.awt.*;

/**
 * Created by vukien on 21/05/2016.
 */
public class DragonComboDrawer extends GameDrawer {
    DragonDrawer dragonDrawer;
    DragonHPDrawer dragonHPDrawer;

    public DragonComboDrawer(DragonDrawer dragonDrawer, DragonHPDrawer dragonHPDrawer) {
        this.dragonDrawer = dragonDrawer;
        this.dragonHPDrawer = dragonHPDrawer;
    }

    @Override
    public void paint(Graphics g, GameObject gameObject) {
        dragonDrawer.paint(g, gameObject);
        dragonHPDrawer.paint(g, gameObject);
    }
}
