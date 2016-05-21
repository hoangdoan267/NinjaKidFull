package com.kienvu.View.AnimationNinja;

import com.kienvu.Model.GameObject;
import com.kienvu.View.GameDrawer;

import java.awt.*;

/**
 * Created by Trà Đá on 5/20/2016.
 */
public class NinjaComboDrawer extends GameDrawer {
    private  NinjaDrawer ninjaBodyDrawer;
    private NinjaHPDrawer ninjaHPDrawer;
    private NinjaArmorDrawer ninjaArmorDrawer;

    public NinjaComboDrawer(NinjaDrawer ninjaBodyDrawer, NinjaHPDrawer ninjaHPDrawer, NinjaArmorDrawer ninjaArmorDrawer) {
        this.ninjaBodyDrawer = ninjaBodyDrawer;
        this.ninjaHPDrawer = ninjaHPDrawer;
        this.ninjaArmorDrawer = ninjaArmorDrawer;
    }

    @Override
    public void paint(Graphics g, GameObject gameObject) {
        ninjaBodyDrawer.paint(g, gameObject);
        ninjaHPDrawer.paint(g, gameObject);
        ninjaArmorDrawer.paint(g, gameObject);
    }
}
