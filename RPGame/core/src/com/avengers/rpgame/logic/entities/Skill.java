package com.avengers.rpgame.logic.entities;

public class Skill {
    private int id;
    private String name;
    private String description;
    private int price;
    private int unlockLevel;
    private String imagePath;
    private int itemType;
    private int mPCost; //MP cost of using this skill
    //ALL effects must be created as 10 for a 10% increase, -50 for 50% decrease.
    private int strengthEffect; //Effects can be positives for Buff or negative for Nerf/Debuff
    private int speedEffect;
    private int magicEffect;
    private int resistanceEffect;
    private int luckEffect;
    private int mPEffect;
    private int hPEffect;
    private String type;

    public Skill() {
    }

    public Skill(int id, String name, String description, int price, int unlockLevel, String imagePath, int itemType, int mPCost, int strengthEffect, int speedEffect, int magicEffect, int resistanceEffect, int luckEffect, int mPEffect, int hPEffect, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.unlockLevel = unlockLevel;
        this.imagePath = imagePath;//new
        this.itemType = itemType;//new
        this.mPCost = mPCost;
        this.strengthEffect = strengthEffect;
        this.speedEffect = speedEffect;
        this.magicEffect = magicEffect;
        this.resistanceEffect = resistanceEffect;
        this.luckEffect = luckEffect;
        this.mPEffect = mPEffect;
        this.hPEffect = hPEffect;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUnlockLevel() {
        return unlockLevel;
    }

    public void setUnlockLevel(int unlockLevel) {
        this.unlockLevel = unlockLevel;
    }

    public int getmPCost() {
        return mPCost;
    }

    public void setmPCost(int mPCost) {
        this.mPCost = mPCost;
    }

    public int getStrengthEffect() {
        return strengthEffect;
    }

    public void setStrengthEffect(int strengthEffect) {
        this.strengthEffect = strengthEffect;
    }

    public int getSpeedEffect() {
        return speedEffect;
    }

    public void setSpeedEffect(int speedEffect) {
        this.speedEffect = speedEffect;
    }

    public int getMagicEffect() {
        return magicEffect;
    }

    public void setMagicEffect(int magicEffect) {
        this.magicEffect = magicEffect;
    }

    public int getResistanceEffect() {
        return resistanceEffect;
    }

    public void setResistanceEffect(int resistanceEffect) {
        this.resistanceEffect = resistanceEffect;
    }

    public int getLuckEffect() {
        return luckEffect;
    }

    public void setLuckEffect(int luckEffect) {
        this.luckEffect = luckEffect;
    }

    public int getmPEffect() {
        return mPEffect;
    }

    public void setmPEffect(int mPEffect) {
        this.mPEffect = mPEffect;
    }

    public int gethPEffect() {
        return hPEffect;
    }

    public void sethPEffect(int hPEffect) {
        this.hPEffect = hPEffect;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", unlockLevel=" + unlockLevel +
                ", mPCost=" + mPCost +
                ", strengthEffect=" + strengthEffect +
                ", speedEffect=" + speedEffect +
                ", magicEffect=" + magicEffect +
                ", resistanceEffect=" + resistanceEffect +
                ", luckEffect=" + luckEffect +
                ", mPEffect=" + mPEffect +
                ", hPEffect=" + hPEffect +
                ", type=" + type +
                '}';
    }
}
