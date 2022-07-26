package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.io.MyInputProcessor;
import com.avengers.rpgame.graphics.store.Store;
import com.avengers.rpgame.graphics.text.FontFactory;
import com.avengers.rpgame.graphics.text.Text;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.utils.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import static com.avengers.rpgame.utils.FileManager.loadMusic;
import static com.avengers.rpgame.utils.Resources.resourceStoreScreen;
import static com.avengers.rpgame.utils.Resources.resourceStoreThemeMusic;

public class StoreScreen implements Screen {
    final RPGame game;
    private Store storeElements;
    private GameConfig config;
    private final Music backgroundMusic;
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

    ShapeRenderer _Border;

    public StoreScreen(final RPGame game, Party playerParty) {
        this.game = game;
        this.config = GameConfig.getInstance();
        input = new MyInputProcessor();
        config = GameConfig.getInstance();
        this.character = playerParty.getPartyMember1();
        storeElements = new Store(this.character);

        ScreenWidth = config.getResolutionHorizontal();
        ScreenHeight = config.getResolutionVertical();
        this.resolution = new Vector2(ScreenWidth, ScreenHeight);

        backgroundImage = new Texture(Gdx.files.internal(resourceStoreScreen));

        backgroundMusic = loadMusic(resourceStoreThemeMusic);
        backgroundMusic.setLooping(true);

        menuOptions = new ArrayList<Text>();
    }

    @Override
    public void show() {
        generateMenu();

        backgroundMusic.play();
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
        backgroundMusic.dispose();
        backgroundImage.dispose();
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
            this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Armaduras",true));
            this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Joyeria Magica",true));
            this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Armas",true));
            this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Pociones",true));
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
        for (Text mTemp: this.menuOptions)
            mTemp.setColor(Color.WHITE);
        if(pId>=0)
            this.menuOptions.get(pId).setColor(Color.FIREBRICK);
    }

    private void validateKeys() {
        try{
            int mTime = 300;
            if(this.input.isMoveRight()){
                this.itemSelected++;
                if (this.itemSelected > storeElements.getItemsSelected().size) {
                    this.itemSelected = 1;
                }
                storeElements.updateItemSelected(this.itemSelected);
                storeElements.changeCoinsColor();
                Thread.sleep(mTime);
            }
            if(this.input.isMoveLeft()){
                this.itemSelected--;
                if (this.itemSelected < 1) {
                    this.itemSelected = storeElements.getItemsSelected().size;
                }
                storeElements.updateItemSelected(this.itemSelected);
                storeElements.changeCoinsColor();
                Thread.sleep(mTime);
            }
            if(this.input.isMoveDown()){
                if(!this.actionSelected) {
                    this.actionOption++;
                    if(this.actionOption >2)
                        this.actionOption = 0;
                    changeOptionColor(this.actionOption);
                } else {
                    this.itemType++;
                    if(this.itemType >4)
                        this.itemType =0;
                    changeOptionColor(this.itemType);
                }
                Thread.sleep(mTime);
            }
            if(this.input.isMoveUp()){
                if(!this.actionSelected) {
                    this.actionOption--;
                    if(this.actionOption < 0)
                        this.actionOption = 2;
                    changeOptionColor(this.actionOption);
                } else  {
                    this.itemType--;
                    if(this.itemType < 0)
                        this.itemType = 4;
                    changeOptionColor(this.itemType);
                }
                Thread.sleep(mTime);
            }
            if(this.input.isEnter()){
                if(!this.actionSelected) {
                    executeAction();
                } else {
                    executeBuyActions();
                }
                Thread.sleep(mTime);
            }
            if(this.input.isBuyItem()) {
                storeElements.cleanMessage();
                if (this.itemSelected != 0 && this.actionOption == 0) {
                    if (storeElements.checkPurchase()) {
                        if (storeElements.buyItem()) {
                            storeElements.setConfirmationMessage(true);
                        } else {
                            storeElements.setConfirmationMessage(false);
                        }
                    } else {
                        storeElements.setConfirmationMessage(false);
                    }
                }
                Thread.sleep(mTime);
            }
            if(this.input.isSellItem()) {
                storeElements.cleanMessage();
                if (this.itemSelected != 0 && this.actionOption == 1) {
                    if (storeElements.sellItem()) {
                        storeElements.setConfirmationMessage(true);
                    } else {
                        storeElements.setConfirmationMessage(false);
                    }
                }
                Thread.sleep(mTime);
            }
        } catch (InterruptedException e){
            game.print(e.toString());
        }
    }

    private void executeAction() {
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
                this.actionSelected = false;
                storeElements.update(itemType, actionOption, itemSelected);
                break;
            case 2:
                GameStatus.getInstance().updateLocation();
                //GameStatus.getInstance().setSaveSlot(1);
                //GameStatus.getInstance().saveOnDB();
                GameStatus.getInstance().setStatus("gameInProgress");
                this.actionSelected = false;
                game.setScreen(new OverworldScreen(game));
                dispose();
                break;
        }
    }

    private void executeBuyActions() {
        if (this.itemType == 4) {
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
