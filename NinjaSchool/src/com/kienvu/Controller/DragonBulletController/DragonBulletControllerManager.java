package com.kienvu.Controller.DragonBulletController;

import com.kienvu.Controller.ControllerManager;

/**
 * Created by vukien on 21/05/2016.
 */
public class DragonBulletControllerManager extends ControllerManager {


    private static DragonBulletControllerManager inst;
    public static DragonBulletControllerManager getInst() {
        if (inst == null) {
            inst = new DragonBulletControllerManager();
        }
        return inst;
    }

}
