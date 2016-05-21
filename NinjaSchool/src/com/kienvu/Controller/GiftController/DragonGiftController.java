package com.kienvu.Controller.GiftController;

import com.kienvu.Controller.Colliable.Colliable;
import com.kienvu.Controller.EnemyBullet.EnemyBulletController;
import com.kienvu.Controller.EnemyBullet.EnemyBulletControllerManager;
import com.kienvu.Controller.SingleController;
import com.kienvu.GameConfig;
import com.kienvu.Model.EnemyBullet;
import com.kienvu.Model.GameObject;
import com.kienvu.Model.GameVector;
import com.kienvu.Model.Gift;
import com.kienvu.Utils;
import com.kienvu.View.DragonDrawer;
import com.kienvu.View.GameDrawer;
import com.kienvu.View.ImageDrawer;

import java.awt.*;
import java.util.Random;

/**
 * Created by vukien on 21/05/2016.
 */
public class DragonGiftController extends SingleController implements Colliable {
    private EnemyBulletControllerManager enemyBulletControllerManager;
    private static int count = 0;
    private Random r;
    public DragonGiftController(GameObject gameObject, GameDrawer gameDrawer, GameVector gameVector) {
        super(gameObject, gameDrawer, gameVector);
        enemyBulletControllerManager = new EnemyBulletControllerManager();

    }

    @Override
    public void paint(Graphics g) {
        if (gameObject.isALive()) {
            super.paint(g);
            //System.out.println("paint");
            if (enemyBulletControllerManager.size() != 0) {
                enemyBulletControllerManager.paint(g);
            }
        }
    }

    @Override
    public void run() {
        if (gameObject.isALive()) {
            super.run();
            enemyBulletControllerManager.run();

            if (gameObject.getX() == GameConfig.getInst().getScreenWidth()/2) {
                ImageDrawer imageDrawer;
                EnemyBullet enemyBullet = new EnemyBullet(
                        gameObject.getX() + gameObject.getWidth()/2 - 20,
                        gameObject.getY() + gameObject.getHeight(),
                        40, 40, 0, 0
                );
                EnemyBulletController enemyBulletController = null;
                GameVector gameVector;

                count++;

                if (count % 3 == 0) {
                    r = new Random();
                    int ran = r.nextInt(10 - (-5)) + (-5);
                    enemyBullet.setGiftType(GiftType.GIFT_2);
                    imageDrawer = new ImageDrawer(Utils.loadImage("resources/characters/Gift/Gift_2.png"));
                    enemyBulletController = new EnemyBulletController(
                            enemyBullet,
                            imageDrawer,
                            gameVector = new GameVector(ran ,4)
                    );

                }else{
                    r = new Random();
                    int ran = r.nextInt(10 - 1)+1;
                    enemyBullet.setGiftType(GiftType.GIFT_1);
                    imageDrawer = new ImageDrawer(Utils.loadImage("resources/characters/Gift/Gift_1.png"));

                    enemyBulletController = new EnemyBulletController(
                            enemyBullet,
                            imageDrawer,
                            gameVector = new GameVector(ran,3)
                    );
                }

                enemyBulletControllerManager.add(enemyBulletController);
                System.out.println(count);
            }

        }
    }

    @Override
    public void onCollide(Colliable c) {

    }

    public static DragonGiftController create(int x, int  y) {
        Gift gift = new Gift(
                x, y,
                Utils.loadImage("DRAGON", "frame", 4).get(0).getWidth(),
                Utils.loadImage("DRAGON", "frame", 4).get(0).getHeight()
        );

        DragonDrawer dragonDrawer = new DragonDrawer(Utils.loadImage("DRAGON", "frame", 4));
        GameVector gameVector = new GameVector(-3,0);
        DragonGiftController dragonGift = new DragonGiftController(
                gift,
                dragonDrawer,
                gameVector
        );
        return dragonGift;
    }
}