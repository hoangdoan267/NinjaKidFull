package com.kienvu.Controller.DragonController;

import com.kienvu.Controller.ControllerManager;
import com.kienvu.GameConfig;

/**
 * Created by vukien on 21/05/2016.
 */
public class DragonControllerManager extends ControllerManager {
    private int count = 0;
    private DragonControllerManager() {
        super();
    }

    @Override
    public void run() {
        super.run();
        count++;
        if (GameConfig.getInst().durationInSeconds(count) > 6) {
            count = 0;
            this.add(DragonController.create(GameConfig.getInst().getScreenWidth(), 150));
        }
    }

    private static DragonControllerManager inst;
    public static DragonControllerManager getInst() {
        if (inst == null) {
            inst = new DragonControllerManager();
        }
        return inst;
    }
}
