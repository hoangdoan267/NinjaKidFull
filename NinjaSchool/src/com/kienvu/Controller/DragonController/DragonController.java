package com.kienvu.Controller.DragonController;

import com.kienvu.Controller.Colliable.Colliable;
import com.kienvu.Controller.Colliable.ColliablePool;
import com.kienvu.Controller.DragonBulletController.DragonBulletController;
import com.kienvu.Controller.DragonBulletController.DragonBulletControllerManager;
import com.kienvu.Controller.Kunai.KunaiController;
import com.kienvu.Controller.SingleController;
import com.kienvu.GameConfig;
import com.kienvu.Model.*;
import com.kienvu.Utils;
import com.kienvu.View.AnimationDragon.DragonComboDrawer;
import com.kienvu.View.AnimationDragon.DragonDrawer;
import com.kienvu.View.AnimationDragon.DragonHPDrawer;
import com.kienvu.View.GameDrawer;
import com.kienvu.View.ImageDrawer;

import java.awt.*;

/**
 * Created by vukien on 21/05/2016.
 */
public class DragonController extends SingleController implements Colliable {
    private int count = 0;
    private DragonBulletControllerManager dragonBulletControllerManager;
    private DragonController(GameObject gameObject, GameDrawer gameDrawer, GameVector gameVector) {
        super(gameObject, gameDrawer, gameVector);
        dragonBulletControllerManager = new DragonBulletControllerManager();
        ColliablePool.getInst().add(this);
    }

    @Override
    public void run() {
        if (gameObject.isALive()) {
            super.run();
            dragonBulletControllerManager.run();
            count++;
            if (GameConfig.getInst().durationInSeconds(count) > 1) {
                count = 0;
            ImageDrawer imageDrawer = new ImageDrawer(Utils.loadImage("resources/characters/Bullet/bullet1.png"));
            DragonBullet enemyBullet = new DragonBullet(
                    gameObject.getX(),
                    gameObject.getY() + gameObject.getHeight(),
                    50,
                    50,
                    0,
                    DragonBullet.DEFAULT_DAMAGE
            );
            GameVector gameVector = new GameVector(-2, 2);
            DragonBulletController enemyBulletController = new DragonBulletController(
                    enemyBullet,
                    imageDrawer,
                    gameVector
            );
            this.dragonBulletControllerManager.add(enemyBulletController);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        if (gameObject.isALive()) {
            super.paint(g);
            dragonBulletControllerManager.paint(g);
        }
    }

    public static DragonController create(int x, int y) {
        Dragon dragon = new Dragon(
                x,
                y,
                Utils.loadImage("DRAGON", "frame", 4).get(0).getWidth(),
                Utils.loadImage("DRAGON", "frame", 4).get(0).getHeight(),
                Dragon.DEFAULT_HP,
                0
        );

        HPbar hPbar = new HPbar(dragon.getX() , dragon.getY() - 5, dragon.getWidth(), 10 );
        dragon.sethPbar(hPbar);

        DragonDrawer dragonDrawer = new DragonDrawer(Utils.loadImage("DRAGON", "frame", 4));
        DragonHPDrawer dragonHPDrawer = new DragonHPDrawer(Utils.loadHPBarImages_1());
        DragonComboDrawer dragonComboDrawer = new DragonComboDrawer(dragonDrawer, dragonHPDrawer);
        DragonController dragonController = new DragonController(
                dragon,
                dragonComboDrawer,
                new GameVector(-Dragon.DEFAULT_SPEED, 0)
        );
        return dragonController;
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof KunaiController) {
            c.getGameObject().setALive(false);
        }
    }
}
