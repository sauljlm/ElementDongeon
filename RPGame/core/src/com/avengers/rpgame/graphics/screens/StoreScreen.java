package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.game.GameInformation;
import com.avengers.rpgame.graphics.store.Store;
import com.avengers.rpgame.graphics.text.Text;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.io.MyInputProcessor;
import com.avengers.rpgame.utils.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import static com.avengers.rpgame.utils.Resources.*;

public class StoreScreen implements Screen {
    final RPGame game;
    private Store storeElements = new Store();
    private final GameConfig config;
    private final Music backgroundMusic;
    private final Texture backgroundImage;
    private final float ScreenWidth;
    private final float ScreenHeight;
    private ArrayList<Text> menuOptions;
    private int actionOption = 0;
    private int itemType = 0;
    private int itemSelected = 0;
    private MyInputProcessor input;
    private GameInformation gameInfo = GameInformation.getInstance();

    ShapeRenderer _Border;

    public StoreScreen(final RPGame game) {
        this.game = game;
        input = new MyInputProcessor();
        config = GameConfig.getInstance();

        ScreenWidth = config.getResolutionHorizontal();
        ScreenHeight = config.getResolutionVertical();

        backgroundImage = new Texture(Gdx.files.internal(resourceStoreScreen));

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(resourceStoreThemeMusic));
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

        if (this.itemType == 0) {
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
                Thread.sleep(mTime);
            }
            if(this.input.isMoveLeft()){
                this.itemSelected--;
                if (this.itemSelected < 1) {
                    this.itemSelected = storeElements.getItemsSelected().size;
                }
                storeElements.updateItemSelected(this.itemSelected);
                Thread.sleep(mTime);
            }
            if(this.input.isMoveDown()){
                this.actionOption++;
                if(this.itemType != 1) {
                    if(this.actionOption >2)
                        this.actionOption =0;
                } else {
                    if(this.actionOption >4)
                        this.actionOption =0;
                }
                Thread.sleep(mTime);
                changeOptionColor(this.actionOption);
            }
            if(this.input.isMoveUp()){
                this.actionOption--;
                if(this.itemType != 1) {
                    if(this.actionOption < 0)
                        this.actionOption = 2;
                } else  {
                    if(this.actionOption < 0)
                        this.actionOption = 4;
                }
                Thread.sleep(mTime);
                changeOptionColor(this.actionOption);
            }
            if(this.input.isEnter()){
                if(this.itemType != 1) {
                    executeAction();
                } else {
                    executeBuyActions();
                }
            }
        } catch (InterruptedException e){
            game.print(e.toString());
        }
    }

    private void executeAction() {
        switch (this.actionOption){
            case 0:
                this.itemType = 1;
                menuOptions = new ArrayList<Text>();
                storeElements.update(500, actionOption, itemType, 0);
                this.generateMenu();
                break;
            case 1:
                this.itemType = 2;
                storeElements.update(500, actionOption, itemType, 0);
                break;
            case 2:
                this.itemType = 3;
                gameInfo.updateLocation();
                game.setScreen(new OverworldScreen(game, GameInformation.getInstance()));
                dispose();
                break;
        }
    }

    private void executeBuyActions() {
        if (this.actionOption == 4) {
            menuOptions = new ArrayList<Text>();
            storeElements.update(500, actionOption, itemType, 0);
            this.itemType = 0;
            this.generateMenu();
        } else {
            this.itemSelected = 1;
            storeElements.update(500, actionOption, itemType, this.itemSelected);
        }
    }
}
