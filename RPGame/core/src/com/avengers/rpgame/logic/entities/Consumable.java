package com.avengers.rpgame.logic.entities;

public class Consumable extends Item{

    public Consumable() {
    }

    public Consumable(int id, String name, String description, int price, int unlockLevel, String imagePath, int itemType, int strengthEffect, int speedEffect, int magicEffect, int resistanceEffect, int luckEffect, int mPEffect, int hPEffect) {
        super(id, name, description, price, unlockLevel, imagePath, itemType, strengthEffect, speedEffect, magicEffect, resistanceEffect, luckEffect, mPEffect, hPEffect);
    }
}
