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
    private boolean action2;
    private boolean select1;
    private boolean select2;
    private boolean select3;
    private boolean select4;
    private boolean select5;
    private boolean select6;
    private boolean select7;
    private boolean select8;
    private boolean select9;
    private boolean select0;
    private boolean enter;
    private boolean moveUpMenu;
    private boolean moveDownMenu;
    private boolean moveLeftMenu;
    private boolean moveRightMenu;
    private boolean enterCreditMode;
    private boolean storeOpened;
    private boolean buyItem;
    private boolean sellItem;

    private int _MouseX, _MouseY;
    private final GameConfig config = GameConfig.getInstance();

    public boolean isCreditMode() {
        return enterCreditMode;
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
    public boolean isAction2() {
        return action1;
    }

    public boolean isSelect1() {
        return select1;
    }

    public boolean isSelect2() {
        return select2;
    }

    public boolean isSelect3() {
        return select3;
    }

    public boolean isSelect4() {
        return select4;
    }

    public boolean isSelect5() {
        return select5;
    }

    public boolean isSelect6() {
        return select6;
    }

    public boolean isSelect7() {
        return select7;
    }

    public boolean isSelect8() {
        return select8;
    }

    public boolean isSelect9() {
        return select9;
    }

    public boolean isSelect0() {
        return select0;
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

    public boolean isStoreOpened() { return storeOpened; }

    public boolean isBuyItem() { return buyItem; }

    public boolean isSellItem() { return sellItem; }

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
        if(keycode == Input.Keys.T) {
            storeOpened = true;
        }
        if(keycode == Input.Keys.C) {
            buyItem = true;
        }
        if(keycode == Input.Keys.V) {
            sellItem = true;
        }
        if(keycode == Input.Keys.SPACE){
            action1 = true;
        }
        if(keycode == Input.Keys.ESCAPE){
            pause = true;
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
        if(keycode == Input.Keys.F){
            enterCreditMode = true;
        }
        if(keycode == Input.Keys.NUM_1){
            select1 = true;
        }
        if(keycode == Input.Keys.NUM_2){
            select2 = true;
        }
        if(keycode == Input.Keys.NUM_3){
            select3 = true;
        }
        if(keycode == Input.Keys.NUM_4){
            select4 = true;
        }
        if(keycode == Input.Keys.NUM_5){
            select5 = true;
        }
        if(keycode == Input.Keys.NUM_6){
            select6 = true;
        }
        if(keycode == Input.Keys.NUM_7){
            select7 = true;
        }
        if(keycode == Input.Keys.NUM_8){
            select8 = true;
        }
        if(keycode == Input.Keys.NUM_9){
            select9 = true;
        }
        if(keycode == Input.Keys.NUM_0){
            select0 = true;
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
        if(keycode == Input.Keys.C) {
            buyItem = false;
        }
        if(keycode == Input.Keys.V) {
            sellItem = false;
        }
        if(keycode == Input.Keys.SPACE){
            action1 = false;
        }
        if(keycode == Input.Keys.ESCAPE){
            pause = false;
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
        if(keycode == Input.Keys.F){
            enterCreditMode = false;
        }
        if(keycode == Input.Keys.NUM_1){
            select1 = false;
        }
        if(keycode == Input.Keys.NUM_2){
            select2 = false;
        }
        if(keycode == Input.Keys.NUM_3){
            select3 = false;
        }
        if(keycode == Input.Keys.NUM_4){
            select4 = false;
        }
        if(keycode == Input.Keys.NUM_5){
            select5 = false;
        }
        if(keycode == Input.Keys.NUM_6){
            select6 = false;
        }
        if(keycode == Input.Keys.NUM_7){
            select7 = false;
        }
        if(keycode == Input.Keys.NUM_8){
            select8 = false;
        }
        if(keycode == Input.Keys.NUM_9){
            select9 = false;
        }
        if(keycode == Input.Keys.NUM_0){
            select0 = false;
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
