package com.avengers.rpgame.game.io;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
//PATRON OBSERVER PATTERN This is a listener interface
public class MyInputProcessor implements InputProcessor {
    private boolean moveUp;
    private boolean moveDown;
    private boolean moveLeft;
    private boolean moveRight;
    private boolean clickTouch;
    private boolean pause;
    private boolean action1;
    private boolean enter;

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

    public boolean keyDown (int keycode) {
        if(keycode == Input.Keys.W || keycode == Input.Keys.UP){
            moveUp = true;
        }
        if(keycode == Input.Keys.S || keycode == Input.Keys.DOWN){
            moveDown = true;
        }
        if(keycode == Input.Keys.A || keycode == Input.Keys.LEFT){
            moveLeft = true;
        }
        if(keycode == Input.Keys.D || keycode == Input.Keys.RIGHT){
            moveRight = true;
        }
        if(keycode == Input.Keys.SPACE){
            action1 = true;
        }
        if(keycode == Input.Keys.ENTER){
            enter = true;
        }
        return false;
    }

    public boolean keyUp (int keycode) {
        if(keycode == Input.Keys.W || keycode == Input.Keys.UP){
            moveUp = false;
        }
        if(keycode == Input.Keys.S || keycode == Input.Keys.DOWN){
            moveDown = false;
        }
        if(keycode == Input.Keys.A || keycode == Input.Keys.LEFT){
            moveLeft = false;
        }
        if(keycode == Input.Keys.D || keycode == Input.Keys.RIGHT){
            moveRight = false;
        }
        if(keycode == Input.Keys.SPACE){
            action1 = false;
        }
        if(keycode == Input.Keys.ENTER){
            enter = false;
        }
        return false;
    }

    public boolean keyTyped (char character) {
        return false;
    }

    public boolean touchDown (int x, int y, int pointer, int button) {
        clickTouch = true;
        return false;
    }

    public boolean touchUp (int x, int y, int pointer, int button) {
        clickTouch = false;
        return false;
    }

    public boolean touchDragged (int x, int y, int pointer) {
        return false;
    }

    public boolean mouseMoved (int x, int y) {
        return false;
    }

    public boolean scrolled (float amountX, float amountY) {
        return false;
    }
}
