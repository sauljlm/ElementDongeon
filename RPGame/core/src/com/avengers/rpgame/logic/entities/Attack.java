package com.avengers.rpgame.logic.entities;

public class Attack {

    private int id;
    private String name;
    private String description;
    private int price;
    private int unlockLevel;
    private String imagePath;
    private int itemType;
    private int mPCost; //MP cost of using this attack
    private int HPEffect;

    public Attack() {
    }

    public Attack(int id, String name, String description, int price, int unlockLevel, String imagePath, int itemType, int HPEffect , int mPCost) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.unlockLevel = unlockLevel;
        this.imagePath = imagePath;
        this.itemType = itemType;
        this.mPCost = mPCost;
        this.HPEffect = HPEffect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getHPEffect() {
        return HPEffect;
    }

    public void setHPEffect(int HPEffect) {
        this.HPEffect = HPEffect;
    }

    public int getmPCost() {
        return mPCost;
    }

    public void setmPCost(int mPCost) {
        this.mPCost = mPCost;
    }

    public String toString() {
        return "Attack{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", unlockLevel=" + unlockLevel +
                ", imagePath='" + imagePath + '\'' +
                ", itemType=" + itemType +
                ", mPCost=" + mPCost +
                ", HPEffect=" + HPEffect +
                '}';
    }
}
