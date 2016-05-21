package com.kienvu.GameScene;

import com.kienvu.Controller.EnemyBullet.EnemyBulletControllerManager;
import com.kienvu.Controller.EnemyWarriorController.EnemyWarriorControllerManager;
import com.kienvu.Controller.GiftController.DragonGiftControllerManager;
import com.kienvu.GameConfig;
import com.kienvu.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Trà Đá on 5/21/2016.
 */
public class GameChoose extends GameScene {
    Image backgroundImage;
    public GameChoose() {
        this.backgroundImage = Utils.loadImage("resources/background/gamechoose.jpg");
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(this.backgroundImage, 0, 20,
                GameConfig.getInst().getScreenWidth(), GameConfig.getInst().getScreenHeight(), null);
    }

    @Override
    public void run() {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void onKeyPressed(KeyEvent e) {
        EnemyWarriorControllerManager.setNULL();
        DragonGiftControllerManager.setNULL();
        EnemyBulletControllerManager.setNULL();
        switch (e.getKeyCode()){

            case KeyEvent.VK_RIGHT:
                changeScene(SceneType.PLAY_2);
                break;
            case KeyEvent.VK_LEFT:
                changeScene(SceneType.PLAY);
                break;

        }
    }

    @Override
    public void onKeyReleased(KeyEvent e) {

    }
}
