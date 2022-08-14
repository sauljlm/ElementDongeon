package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.audio.SoundEffectsManager;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.io.MyInputProcessor;
import com.avengers.rpgame.graphics.store.Store;
import com.avengers.rpgame.graphics.text.FontFactory;
import com.avengers.rpgame.graphics.text.Text;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.utils.Resources;
import com.avengers.rpgame.utils.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.Resources.*;

public class StoreScreen implements Screen {
    final RPGame game;
    private Store storeElements;
    private GameConfig config;
    private GameStatus gameStatus;
    private final Texture backgroundImage;
    private BitmapFont gameFont = FontFactory.createBitMapFont(Gdx.files.internal(Resources.resourceMainFont), Resources.generalHUDFontSize, Color.WHITE, false, Color.BLACK);
    private final float ScreenWidth;
    private final float ScreenHeight;
    private Vector2 resolution;
    private ArrayList<Text> menuOptions;
    private int actionOption = 0;
    private boolean actionSelected = false;
    private int itemType = 0;
    private int itemSelected = 0;
    private MyInputProcessor input;
    private AbstractCharacter character;
    private int selectionMemento;

    ShapeRenderer _Border;

    public StoreScreen() {
        this.game = RPGame.getInstance();
        this.config = GameConfig.getInstance();
        gameStatus = GameStatus.getInstance();
        input = new MyInputProcessor();
        this.character = gameStatus.getPlayerParty().getActivePartyMember();
        storeElements = new Store(this.character);

        ScreenWidth = config.getResolutionHorizontal();
        ScreenHeight = config.getResolutionVertical();
        this.resolution = new Vector2(ScreenWidth, ScreenHeight);

        backgroundImage = loadTexture(resourceStoreScreen);

        menuOptions = new ArrayList<Text>();
    }

    @Override
    public void show() {
        generateMenu();
        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        game.batch.begin();
        game.batch.draw(backgroundImage, 0, 0);

        for(Text mTemp : this.menuOptions){
            mTemp.draw();
        }

        storeElements.draw(game.batch);
        game.batch.end();

        validateKeys();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    }

    private void generateMenu(){
        int mFontSize = Resources.storeMenuOptionsFontSize;
        float mNextY = 0;
        int mRest = Resources.storeMenuOptionsFontSize;

        if (!this.actionSelected) {
            this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Comprar",true));
            this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Vender",true));
            this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Regresar al mapa",true));
        } else {
            this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Ataques",true));
            this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Habilidades",true));
            this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Usables",true));
            this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Regresar al menu",true));
        }

        this.menuOptions.get(0).customPositionTextScreen(ScreenWidth/1.38f, ScreenHeight/2.7f);
        mNextY = this.menuOptions.get(0).getY();

        for(Text mTemp : this.menuOptions){
            mTemp.customPositionTextScreen(ScreenWidth/1.38f, ScreenHeight/2.7f);
            mNextY -=mRest;
            mTemp.setY(mNextY);
        }

        changeOptionColor(0);
    }

    private void changeOptionColor(int pId){
        playSelectionSound();
        for (Text mTemp: this.menuOptions)
            mTemp.setColor(Color.WHITE);
        if(pId>=0)
            this.menuOptions.get(pId).setColor(Color.FIREBRICK);
    }

    private void validateKeys() {
        int mTime = 300;
        if(this.input.isMoveRight() && Utils.getInstance().skipFrames()){
            this.itemSelected++;
            if (this.itemSelected > storeElements.getItemsSelected().size) {
                this.itemSelected = 1;
            }
            storeElements.updateItemSelected(this.itemSelected);
            storeElements.changeCoinsColor();
        }
        if(this.input.isMoveLeft() && Utils.getInstance().skipFrames()){
            this.itemSelected--;
            if (this.itemSelected < 1) {
                this.itemSelected = storeElements.getItemsSelected().size;
            }
            storeElements.updateItemSelected(this.itemSelected);
            storeElements.changeCoinsColor();
        }
        if(this.input.isMoveDown() && Utils.getInstance().skipFrames()){
            if(!this.actionSelected) {
                this.actionOption++;
                if(this.actionOption >2)
                    this.actionOption = 0;
                changeOptionColor(this.actionOption);
            } else {
                this.itemType++;
                if(this.itemType >3)
                    this.itemType =0;
                changeOptionColor(this.itemType);
            }
        }
        if(this.input.isMoveUp() && Utils.getInstance().skipFrames()){
            if(!this.actionSelected) {
                this.actionOption--;
                if(this.actionOption < 0)
                    this.actionOption = 2;
                changeOptionColor(this.actionOption);
            } else  {
                this.itemType--;
                if(this.itemType < 0)
                    this.itemType = 3;
                changeOptionColor(this.itemType);
            }
        }
        if(this.input.isEnter() && Utils.getInstance().skipFrames()){
            if(!this.actionSelected) {
                executeAction();
            } else {
                executeBuyActions();
            }
        }
        if(this.input.isBuyItem() && Utils.getInstance().skipFrames()) {
            storeElements.cleanMessage();
            if (this.itemSelected != 0 && this.actionOption == 0) {
                if (storeElements.checkPurchase()) {
                    if (storeElements.buyItem()) {
                        storeElements.setConfirmationMessage(true);
                        SoundEffectsManager.getInstance().play(coinSound, false);
                    } else {
                        storeElements.setConfirmationMessage(false);
                    }
                } else {
                    storeElements.setConfirmationMessage(false);
                }
            }
        }
        if(this.input.isSellItem() && Utils.getInstance().skipFrames()) {
            storeElements.cleanMessage();
            if (this.itemSelected != 0 && this.actionOption == 1) {
                if (storeElements.sellItem()) {
                    storeElements.setConfirmationMessage(true);
                    SoundEffectsManager.getInstance().play(coinSound, false);
                } else {
                    storeElements.setConfirmationMessage(false);
                }
            }
        }
    }
    private void playSelectionSound(){
        if(actionOption != selectionMemento){
            SoundEffectsManager.getInstance().play(menuSoundEffect, false);
            selectionMemento = actionOption;
        }
    }

    private void executeAction() {
        playSelectionSound();
        switch (this.actionOption){
            case 0:
                this.itemType = 0;
                this.itemSelected = 0;
                this.actionSelected = true;
                menuOptions = new ArrayList<Text>();
                storeElements.update(itemType, actionOption, itemSelected);
                this.generateMenu();
                break;
            case 1:
                this.itemSelected = 1;
                this.actionSelected = false;
                storeElements.update(itemType, actionOption, itemSelected);
                break;
            case 2:
                GameStatus.getInstance().setStatus("gameInProgress");
                this.actionSelected = false;
                ScreeenManager.getInstance().changeScreen("OverworldScreen");
                dispose();
                break;
        }
    }

    private void executeBuyActions() {
        if (this.itemType == 3) {
            menuOptions = new ArrayList<Text>();
            this.itemSelected = 0;
            this.itemType = 0;
            this.actionOption = 0;
            this.actionSelected = false;
            storeElements.update(itemType, actionOption, itemSelected);
            this.generateMenu();
        } else {
            this.itemSelected = 1;
            storeElements.update(itemType, actionOption, this.itemSelected);
        }
    }
}
