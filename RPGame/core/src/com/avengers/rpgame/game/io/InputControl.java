package com.avengers.rpgame.game.io;

public class InputControl {
    private boolean moveUp;
    private boolean moveDown;
    private boolean moveLeft;
    private boolean moveRight;
    private boolean clickTouch;
    private boolean pause;
    private boolean action1;
    private boolean enter;

    public InputControl() {
    }

    public boolean isMoveUp() {
        return moveUp;
    }

    public boolean isMoveDown() {
        return moveDown;
    }

    public boolean isMoveLeft() {
        return moveLeft;
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public boolean isClickTouch() {
        return clickTouch;
    }

    public boolean isPause() {
        return pause;
    }

    public boolean isAction1() {
        return action1;
    }

    public boolean isEnter() {
        return enter;
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    public void setMoveDown(boolean moveDown) {
        this.moveDown = moveDown;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public void setClickTouch(boolean clickTouch) {
        this.clickTouch = clickTouch;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public void setAction1(boolean action1) {
        this.action1 = action1;
    }

    public void setEnter(boolean enter) {
        this.enter = enter;
    }
}
