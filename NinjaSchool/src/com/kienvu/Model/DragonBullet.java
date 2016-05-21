package com.kienvu.Model;

/**
 * Created by vukien on 21/05/2016.
 */
public class DragonBullet extends GameObjectWithHP {
    public static int DEFAULT_DAMAGE = 10;
    public DragonBullet(int x, int y, int width, int height, int hp, int damage) {
        super(x, y, width, height, hp, damage);
    }
}
