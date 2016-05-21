package com.kienvu.Model;

/**
 * Created by vukien on 14/05/2016.
 */
public class Ninja extends GameObjectWithHP {
    public static final int DEFAULT_HP = 600;
    public static final int DEFAULT_DAMAGE = 50;
    public static final int SPEED = 5;
    public static final int DEFAULT_ARMOR = 600;
    private HPbar hPbar;
    private ArmorBar armorBar;
    private int armor = 0;
    public Ninja(int x, int y, int width, int height, int hp, int damage, HPbar hPbar, ArmorBar armorBar) {
        super(x, y, width, height, hp, damage);
        this.setLifeState(LifeState.STAND_RIGHT);
        this.hPbar = hPbar;
        this.armorBar = armorBar;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public HPbar gethPbar() {
        return hPbar;
    }
    public ArmorBar getArmorBar(){
        return armorBar;
    }

    public void decreaseArmor(int a){
        this.armor -= a;
    }
}
