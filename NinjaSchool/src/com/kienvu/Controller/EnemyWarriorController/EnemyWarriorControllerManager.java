package com.kienvu.Controller.EnemyWarriorController;

import com.kienvu.Controller.ControllerManager;
import com.kienvu.Controller.SingleController;
import com.kienvu.GameConfig;
import com.kienvu.Model.EnemyWarrior;


/**
 * Created by vukien on 21/05/2016.
 */
public class EnemyWarriorControllerManager extends ControllerManager {
    private int count = 0;
    private int count_1 = 0;

    private EnemyWarriorControllerManager() {
        super();
    }

    @Override
    public void run() {
        super.run();
        count++;
        if (GameConfig.getInst().durationInSeconds(count) > 4) {
            count = 0;
            count_1++;

            if (count_1 > 4) {
                count_1 = 0;
                this.add(EnemyWarriorController.create(GameConfig.getInst().getScreenWidth(), 350, EnemyType.BOSS));
            }else{
                this.add(EnemyWarriorController.create(GameConfig.getInst().getScreenWidth(), 400, EnemyType.WARRIOR));
            }

        }



    }

    private static EnemyWarriorControllerManager inst;
    public static EnemyWarriorControllerManager getInst() {
        if (inst == null) {
            inst = new EnemyWarriorControllerManager();
        }
        return inst;
    }

    public static void setNULL() {
        inst = null;
    }
    public void dieAll(){
        for(SingleController c: this.singleControllerVector){
            ((EnemyWarriorController)c).die();
        }
    }
}
