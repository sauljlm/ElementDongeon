package com.avengers.rpgame.logic.entities.character.components.skin;

//A Skin contains the necessary information to change the graphical appearance of a character, each AnimationAsset contains all the information for a particular state/action
public class Skin {
    private AnimationAssets up;
    private AnimationAssets down;
    private AnimationAssets left;
    private AnimationAssets right;
    private AnimationAssets waiting;

    public Skin() {
    }

    public Skin(AnimationAssets up, AnimationAssets down, AnimationAssets left, AnimationAssets right, AnimationAssets waiting) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.waiting = waiting;
    }

    public AnimationAssets getUp() {
        return up;
    }

    public void setUp(AnimationAssets up) {
        this.up = up;
    }

    public AnimationAssets getDown() {
        return down;
    }

    public void setDown(AnimationAssets down) {
        this.down = down;
    }

    public AnimationAssets getLeft() {
        return left;
    }

    public void setLeft(AnimationAssets left) {
        this.left = left;
    }

    public AnimationAssets getRight() {
        return right;
    }

    public void setRight(AnimationAssets right) {
        this.right = right;
    }

    public AnimationAssets getWaiting() {
        return waiting;
    }

    public void setWaiting(AnimationAssets waiting) {
        this.waiting = waiting;
    }
}
