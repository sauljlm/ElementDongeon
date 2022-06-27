package com.avengers.rpgame.game.io;

import com.avengers.rpgame.game.GameConfig;
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
    private boolean moveUpMenu;
    private boolean moveDownMenu;
    private boolean moveLeftMenu;
    private boolean moveRightMenu;

    private int _MouseX, _MouseY;
    private final GameConfig config = GameConfig.getInstance();

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

    public boolean isMoveUpMenu() {
        return moveUpMenu;
    }

    public boolean isMoveDownMenu() {
        return moveDownMenu;
    }

    public boolean isMoveLeftMenu() {
        return moveLeftMenu;
    }

    public boolean isMoveRightMenu() {
        return moveRightMenu;
    }

    public int getMouseX() {
        return _MouseX;
    }

    public int getMouseY() {
        return _MouseY;
    }

    private void setMouseX(int pMouseX) {
        this._MouseX = pMouseX;
    }

    private void setMouseY(int pMouseY) {
        this._MouseY = config.getResolutionVertical()-pMouseY;
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
        if(keycode == Input.Keys.UP){
            moveUpMenu = true;
        }
        if(keycode == Input.Keys.DOWN){
            moveDownMenu = true;
        }
        if(keycode == Input.Keys.LEFT){
            moveLeftMenu = true;
        }
        if(keycode == Input.Keys.RIGHT){
            moveRightMenu = true;
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
        if(keycode == Input.Keys.UP){
            moveUpMenu = false;
        }
        if(keycode == Input.Keys.DOWN){
            moveDownMenu = false;
        }
        if(keycode == Input.Keys.LEFT){
            moveLeftMenu = false;
        }
        if(keycode == Input.Keys.RIGHT){
            moveRightMenu = false;
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
        this.setMouseX(x);
        this.setMouseY(y);
        return false;
    }

    public boolean scrolled (float amountX, float amountY) {
        return false;
    }

}
