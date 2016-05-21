package com.kienvu.Controller.Kunai;

import com.kienvu.Controller.Colliable.Colliable;
import com.kienvu.Controller.Colliable.ColliablePool;
import com.kienvu.Controller.DragonController.DragonController;
import com.kienvu.Controller.EnemyWarriorController.EnemyWarriorController;
import com.kienvu.Controller.SingleController;
import com.kienvu.GameConfig;
import com.kienvu.Model.*;
import com.kienvu.Utils;
import com.kienvu.View.AnimationKunai.AnimationKunai;
import com.kienvu.View.GameDrawer;
import com.kienvu.View.ImageDrawer;

import java.awt.*;

/**
 * Created by vukien on 19/05/2016.
 */
public class KunaiController extends SingleController implements Colliable {
    public KunaiController(GameObject gameObject, GameDrawer gameDrawer, GameVector gameVector) {
        super(gameObject, gameDrawer, gameVector);
        ColliablePool.getInst().add(this);
    }




    @Override
    public void run() {
        if (gameObject.isALive()) {
            super.run();
            //System.out.println("dx = " + gameObject.getX());
            if (!GameConfig.getInst().isScreen(this.gameObject)) {
                this.gameObject.setALive(false);
            }
        }
    }


    @Override
    public void paint(Graphics g) {
        if(gameObject.isALive()) {
            super.paint(g);
        }

    }

    @Override
    public void onCollide(Colliable c) {

        if (c instanceof EnemyWarriorController) {
            ((GameObjectWithHP)c.getGameObject()).descrease(((GameObjectWithHP)gameObject).getDamage());
            if (((GameObjectWithHP)c.getGameObject()).getHp() <= 0) {
                ((GameObjectWithHP)c.getGameObject()).setLifeState(LifeState.DYING_LEFT);
                ((GameObjectWithHP)c.getGameObject()).setHp(0);
            }
        }

        if (c instanceof DragonController) {
            if (((GameObjectWithHP)c.getGameObject()).getHp() >= 0) {
                ((GameObjectWithHP)c.getGameObject()).descrease(((GameObjectWithHP)gameObject).getDamage());
                System.out.println("hp: " + ((GameObjectWithHP)c.getGameObject()).getHp());
            } else {
                ((GameObjectWithHP)c.getGameObject()).setALive(false);
            }
        }
    }
}
