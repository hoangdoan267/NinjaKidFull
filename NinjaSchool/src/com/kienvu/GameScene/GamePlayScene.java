package com.kienvu.GameScene;

import com.kienvu.Controller.Colliable.ColliablePool;
import com.kienvu.Controller.Controller;

import com.kienvu.Controller.DragonController.DragonControllerManager;
import com.kienvu.Controller.EnemyBullet.EnemyBulletControllerManager;
import com.kienvu.Controller.GiftController.DragonGiftControllerManager;
import com.kienvu.Controller.EnemyWarriorController.EnemyWarriorControllerManager;
import com.kienvu.Controller.Ninja.NinjaController;
import com.kienvu.Controller.Ninja.NinjaControllerType;
import com.kienvu.GameConfig;
import com.kienvu.Model.LifeState;
import com.kienvu.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by Trà Đá on 5/18/2016.
 */
public class GamePlayScene extends GameScene {

    private BufferedImage backgroundImage;
    private NinjaController ninjaController;
    private Vector<Controller> controllerVector;
    private GameConfig gameConfig;
    private EnemyWarriorControllerManager enemyWarriorControllerManager;
    private DragonGiftControllerManager dragonGiftControllerManager;

    private DragonControllerManager dragonControllerManager;
    private int bgX =0;
    public boolean stop = true;
    private int count;


    public GamePlayScene() {
        Utils.playSound("resources/Sound/back.wav", true);
        gameConfig = GameConfig.getInst();
        controllerVector = new Vector<Controller>();
        enemyWarriorControllerManager = EnemyWarriorControllerManager.getInst();

        controllerVector.add(NinjaController.getNinjaController());

        controllerVector.add(enemyWarriorControllerManager);


        this.ninjaController = NinjaController.getNinjaController();
        this.dragonControllerManager = DragonControllerManager.getInst();
        controllerVector.add(dragonControllerManager);
        this.dragonGiftControllerManager = DragonGiftControllerManager.getInst();
        controllerVector.add(dragonGiftControllerManager);


        this.backgroundImage = Utils.loadImage("resources/background/background.jpg");
//        try {
//            backgroundImage = ImageIO.read(new File("resources/background/background.jpg"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        if(enemyWarriorControllerManager.size() >= 5){
            this.backgroundImage = Utils.loadImage("resources/background/background2.jpg");
        }

    }

    public void resetGameScene(){
        EnemyWarriorControllerManager.setNULL();
        EnemyBulletControllerManager.setNULL();
        DragonGiftControllerManager.setNULL();
        NinjaController.setNULL();

    }



    Timer t1;

    {
        t1 = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ninjaController.getNinjaControllerType() == NinjaControllerType.RIGHT) {
                    ninjaController.move(LifeState.STAND_RIGHT);
                } else {
                    ninjaController.move(LifeState.STAND_LEFT);
                }

            }
        });

        t1.setRepeats(false);
    }


    @Override
    public void onKeyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                ninjaController.setNinjaControllerType(NinjaControllerType.RIGHT);
                ninjaController.move(LifeState.RUN_RIGHT);
                break;
            case KeyEvent.VK_LEFT:
                ninjaController.setNinjaControllerType(NinjaControllerType.LEFT);
                ninjaController.move(LifeState.RUN_LEFT);
                break;
            case KeyEvent.VK_UP:
//                        if(ninjaController.isJump) {
//                            ninjaControllerType = NinjaControllerType.JUMP_UP;
//                            ninjaController.move(ninjaControllerType);
//                            ninjaController.isJump = false;
//                        }
                if (ninjaController.getNinjaControllerType() == NinjaControllerType.RIGHT) {
                    ninjaController.move(LifeState.JUMP_RIGHT);
                } else  {
                    ninjaController.move(LifeState.JUMP_LEFT);
                }
                break;
            case KeyEvent.VK_SPACE:
                Utils.playSound("resources/Sound/chem.wav", false);
                if (ninjaController.getNinjaControllerType() == NinjaControllerType.RIGHT) {
                    ninjaController.move(LifeState.ATTACK_RIGHT);
                } else  {
                    ninjaController.move(LifeState.ATTACK_LEFT);
                }
                break;
            case  KeyEvent.VK_P:
                if(stop){
                    stop = false;
                }else{
                    stop = true;
                }
                break;


        }
    }

    @Override
    public void onKeyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                ninjaController.move(LifeState.STAND_LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                ninjaController.move(LifeState.STAND_RIGHT);
                break;
            case KeyEvent.VK_UP:
                if (ninjaController.getNinjaControllerType() == NinjaControllerType.RIGHT) {
                    ninjaController.move(LifeState.STAND_RIGHT);
                } else  {
                    ninjaController.move(LifeState.STAND_LEFT);
                }
                break;
            case KeyEvent.VK_SPACE:
                t1.restart();
                //System.out.println("2");


                break;

        }
    }

    Timer t2;

    {
        t2 = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ninjaController.getNinjaControllerType() == NinjaControllerType.RIGHT) {
                    ninjaController.move(LifeState.STAND_RIGHT);
                } else if (ninjaController.getNinjaControllerType() == NinjaControllerType.LEFT) {
                    ninjaController.move(LifeState.STAND_LEFT);
                }
            }
        });

        t2.setRepeats(false);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getX() > ninjaController.getGameObject().getX() + ninjaController.getGameObject().getWidth()) {
            Utils.playSound("resources/Sound/tieu.wav", false);
            ninjaController.setNinjaControllerType(NinjaControllerType.RIGHT);
            ninjaController.move(LifeState.THROW_RIGHT);
            ninjaController.shot(e.getX(), e.getY());
        } else if (e.getX() < ninjaController.getGameObject().getX()) {
            Utils.playSound("resources/Sound/tieu.wav", false);
            ninjaController.setNinjaControllerType(NinjaControllerType.LEFT);
            ninjaController.move(LifeState.THROW_LEFT);
            ninjaController.shot(e.getX(), e.getY());
        }
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        t2.restart();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(backgroundImage, bgX, 10, backgroundImage.getWidth(),
                backgroundImage.getHeight(), null);

        for(Controller controller : controllerVector){
            controller.paint(g);
        }
    }

    @Override
    public void run() {
        count++;
        if(stop){
            ColliablePool.getInst().run();
            for(Controller controller : controllerVector){
                controller.run();
            }
        }


        if(GameConfig.getInst().durationInSeconds(count) >=20){
            if(enemyWarriorControllerManager.size() == 0){
                count = 0;
                EnemyWarriorControllerManager.setNULL();
                DragonGiftControllerManager.setNULL();
                EnemyBulletControllerManager.setNULL();
                changeScene(SceneType.PLAY_1_LEVEL_2);
            }
        }


        if(!ninjaController.getGameObject().isALive()){
            resetGameScene();
            changeScene(SceneType.GAMEOVER);
        }

    }
}