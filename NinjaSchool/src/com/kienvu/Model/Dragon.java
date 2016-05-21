package com.kienvu.Model;


/**
 * Created by vukien on 21/05/2016.
 */
public class Dragon extends GameObjectWithHP {
    public static int DEFAULT_HP = 50;
    public static int DEFAULT_SPEED = 2;
    private HPbar hPbar;
    public Dragon(int x, int y, int width, int height, int hp, int damage) {
        super(x, y, width, height, hp, damage);
    }

    public HPbar gethPbar() {
        return hPbar;
    }

    public void sethPbar(HPbar hPbar) {
        this.hPbar = hPbar;
    }

    @Override
    public void move(GameVector gameVector) {
        super.move(gameVector);
        hPbar.setX(getX() + getWidth() / 2 - hPbar.getWidth()/2);
        hPbar.setY(getY());
    }
}
