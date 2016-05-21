package com.kienvu.Controller.EnemyWarriorController;

import com.kienvu.Controller.Colliable.Colliable;
import com.kienvu.Controller.Colliable.ColliablePool;
import com.kienvu.Controller.Kunai.KunaiController;
import com.kienvu.Controller.Ninja.NinjaController;
import com.kienvu.Controller.SingleController;
import com.kienvu.GameConfig;
import com.kienvu.Model.*;
import com.kienvu.Utils;
import com.kienvu.View.AnimationEnemyWarrior.*;
import com.kienvu.View.GameDrawer;

import java.awt.*;

/**
 * Created by vukien on 21/05/2016.
 */
public class EnemyWarriorController extends SingleController implements Colliable {
    private int count = 0;
    private EnemyWarriorController(GameObject gameObject, GameDrawer gameDrawer, GameVector gameVector) {
        super(gameObject, gameDrawer, gameVector);
        ColliablePool.getInst().add(this);
        //HPbar hPbar = new HPbar()
    }

    @Override
    public void run() {
        if (gameObject.isALive()) {
            super.run();
//            if (!GameConfig.getInst().isScreen(gameObject.getRectangle())) {
//                gameObject.setALive(false);
//            }
        }
    }

    @Override
    public void paint(Graphics g) {
        if (gameObject.isALive()) {
            super.paint(g);
        }

    }

    public static EnemyWarriorController create(int x ,int  y, EnemyType enemyType) {
        EnemyWarrior enemyWarrior;
        HPbar hPbar;
        GameVector gameVector;
        EnemyWarriorController enemyWarriorController = null;
        LeftEnemyDrawer leftEnemyDrawer;
        AttackEnemyDrawer attackEnemyDrawer;
        DyingEnemyDrawer dyingEnemyDrawer;
        EnemyDrawer enemyDrawer;
        EnemyHPDrawer enemyHPDrawer;
        EnemyComboDrawer enemyComboDrawer;
        switch (enemyType) {
            case WARRIOR:
                enemyWarrior = new EnemyWarrior(
                        x,
                        y,
                        Utils.loadImage("enemywarrior", "Walk", 10).get(0).getWidth(),
                        Utils.loadImage("enemywarrior", "Walk", 10).get(0).getHeight(),
                        EnemyWarrior.DEFAULT_HP,
                        EnemyWarrior.DEFAULT_DAMAGE
                );

                hPbar = new HPbar(enemyWarrior.getX(), enemyWarrior.getY() - 5, 80, 10);
                enemyWarrior.sethPbar(hPbar);

                //WALK
                leftEnemyDrawer = new LeftEnemyDrawer(Utils.loadImage("enemywarrior", "Walk", 10));

                //ATTACK
                attackEnemyDrawer = new AttackEnemyDrawer(Utils.loadImage("enemywarrior", "Attack", 10));

                //DYING
                dyingEnemyDrawer = new DyingEnemyDrawer(Utils.loadImage("enemywarrior", "Dead", 10));

                //EnemyDrawer
                enemyDrawer = new EnemyDrawer( attackEnemyDrawer, dyingEnemyDrawer, leftEnemyDrawer);

                //EnemyHPDrawer
                enemyHPDrawer = new EnemyHPDrawer(Utils.loadHPBarImages_1());

                //EnemyComboDrawer
                enemyComboDrawer = new EnemyComboDrawer(enemyDrawer, enemyHPDrawer);

                gameVector = new GameVector(-EnemyWarrior.DEFAULT_SPEED, 0);

                enemyWarriorController = new EnemyWarriorController(
                        enemyWarrior,
                        enemyComboDrawer,
                        gameVector
                );
                break;
            case BOSS:
                enemyWarrior = new EnemyWarrior(
                        x,
                        y,
                        Utils.loadImage("boss", "Run", 8).get(0).getWidth(),
                        Utils.loadImage("boss", "Run", 8).get(0).getHeight(),
                        EnemyWarrior.BOSS_HP,
                        EnemyWarrior.BOSS_DEAMAGE
                );

                hPbar = new HPbar(enemyWarrior.getX(), enemyWarrior.getY() - 5, 160, 10);
                enemyWarrior.sethPbar(hPbar);

                //WALK
                leftEnemyDrawer = new LeftEnemyDrawer(Utils.loadImage("boss", "Run", 8));

                //ATTACK
                attackEnemyDrawer = new AttackEnemyDrawer(Utils.loadImage("boss", "Attack", 8));

                //DYING
                dyingEnemyDrawer = new DyingEnemyDrawer(Utils.loadImage("boss", "Dead", 10));

                //EnemyDrawer
                enemyDrawer = new EnemyDrawer( attackEnemyDrawer, dyingEnemyDrawer, leftEnemyDrawer);

                //EnemyHPDrawer
                enemyHPDrawer = new EnemyHPDrawer(Utils.loadHPBarImages_2());

                //EnemyComboDrawer
                enemyComboDrawer = new EnemyComboDrawer(enemyDrawer, enemyHPDrawer);

                gameVector = new GameVector(-EnemyWarrior.DEFAULT_SPEED, 0);

                enemyWarriorController = new EnemyWarriorController(
                        enemyWarrior,
                        enemyComboDrawer,
                        gameVector
                );
                break;
        }

        return enemyWarriorController;
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof NinjaController) {
            GameObjectWithHP g = (GameObjectWithHP)c.getGameObject();
            Ninja ninja = (Ninja)g;
            this.gameVector.dx = 0;
            if (((GameObjectWithHP)gameObject).getLifeState() != LifeState.DYING_LEFT) {
                ((GameObjectWithHP)gameObject).setLifeState(LifeState.ATTACK_LEFT);
            }
            if(((GameObjectWithHP)c.getGameObject()).getLifeState() != LifeState.ATTACK_LEFT || ((GameObjectWithHP)c.getGameObject()).getLifeState() != LifeState.ATTACK_RIGHT) {
                count ++;
                if (GameConfig.getInst().durationInSeconds(count) > 0.5) {
                    count = 0;
                    if ((c.getGameObject()).isALive()) {
                        if(ninja.getArmor() >=0){
                            ninja.decreaseArmor(((GameObjectWithHP)gameObject).getDamage());
                        }else{
                            ((GameObjectWithHP)c.getGameObject()).descrease(((GameObjectWithHP)gameObject).getDamage());
                        }

                    }

                }
                if (((GameObjectWithHP)c.getGameObject()).getHp() <= 0) {
                    Utils.playSound("resources/Sound/idie.wav", false);
                    ((GameObjectWithHP)gameObject).setHp(0);
                    ((GameObjectWithHP)c.getGameObject()).setLifeState(LifeState.DYING_RIGHT);
                    ((GameObjectWithHP)gameObject).setDamage(0);
                    ((GameObjectWithHP)gameObject).setLifeState(LifeState.RUN_LEFT);
                }
            }
        } else if (c instanceof KunaiController) {
            c.getGameObject().setALive(false);
        }
    }

    public void die(){
        ((GameObjectWithHP)gameObject).setLifeState(LifeState.DYING_LEFT);
    }

}
