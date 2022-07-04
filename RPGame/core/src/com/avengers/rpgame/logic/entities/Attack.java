package com.avengers.rpgame.logic.entities;

public class Attack {
    private String name;
    private String description;
    private int unlockLevel;
    private int mPCost; //MP cost of using this attack
    private int HPEffect;

    public Attack() {
    }

    public Attack(String name, String description, int unlockLevel, int mPCost, int HPEffect) {
        this.name = name;
        this.description = description;
        this.unlockLevel = unlockLevel;
        this.mPCost = mPCost;
        this.HPEffect = HPEffect;
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

    public int getHPEffect() {
        return HPEffect;
    }

    public void setHPEffect(int HPEffect) {
        this.HPEffect = HPEffect;
    }

    @Override
    public String toString() {
        return "Attack{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", unlockLevel=" + unlockLevel +
                ", mPCost=" + mPCost +
                ", HPEffect=" + HPEffect +
                '}';
    }
}
