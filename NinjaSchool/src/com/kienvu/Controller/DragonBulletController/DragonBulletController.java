package com.kienvu.Controller.DragonBulletController;

import com.kienvu.Controller.Colliable.Colliable;
import com.kienvu.Controller.Colliable.ColliablePool;
import com.kienvu.Controller.Ninja.NinjaController;
import com.kienvu.Controller.SingleController;
import com.kienvu.GameConfig;
import com.kienvu.Model.GameObject;
import com.kienvu.Model.GameObjectWithHP;
import com.kienvu.Model.GameVector;
import com.kienvu.Model.LifeState;
import com.kienvu.View.GameDrawer;

import java.awt.*;

/**
 * Created by vukien on 21/05/2016.
 */
public class DragonBulletController extends SingleController implements Colliable {
    public DragonBulletController(GameObject gameObject, GameDrawer gameDrawer, GameVector gameVector) {
        super(gameObject, gameDrawer, gameVector);
        ColliablePool.getInst().add(this);
    }

    @Override
    public void paint(Graphics g) {
        if (gameObject.isALive()) {
            super.paint(g);
        }
    }

    @Override
    public void run() {
        if (gameObject.isALive()) {
            super.run();
            if (gameObject.getY() - gameObject.getHeight() > GameConfig.getInst().getCellar()) {
                gameVector.dx = 0;
                gameVector.dy = 0;
                gameObject.setALive(false);
                System.out.println((gameObject).isALive());
            }
        }
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof NinjaController) {
            if (((GameObjectWithHP)c.getGameObject()).getHp() >= 0) {
                ((GameObjectWithHP)c.getGameObject()).descrease(((GameObjectWithHP)gameObject).getDamage());
            } else {
                ((GameObjectWithHP)c.getGameObject()).setLifeState(LifeState.DYING_RIGHT);
            }
            ((GameObjectWithHP)gameObject).setALive(false);
        }
    }
}
