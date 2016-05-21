package com.kienvu.Controller.GiftController;

import com.kienvu.Controller.ControllerManager;
import com.kienvu.GameConfig;

/**
 * Created by vukien on 21/05/2016.
 */
public class DragonGiftControllerManager extends ControllerManager {
    private int count = 0;
    private DragonGiftControllerManager() {
        super();
    }

    @Override
    public void run() {
        super.run();
        count++;
        if (GameConfig.getInst().durationInSeconds(count) > 5) {
            this.add(DragonGiftController.create(1000, 100));
            count = 0;
        }
    }

    private static DragonGiftControllerManager inst;
    public static DragonGiftControllerManager getInst() {
        if (inst == null) {
            inst = new DragonGiftControllerManager();
        }

        return inst;
    }

    public static void setNULL(){
        inst = null;
    }
}