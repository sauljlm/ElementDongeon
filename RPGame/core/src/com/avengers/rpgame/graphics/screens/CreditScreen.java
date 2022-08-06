package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.io.MyInputProcessor;
import com.avengers.rpgame.graphics.text.Text;
import com.avengers.rpgame.utils.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.Resources.*;

public class CreditScreen implements Screen {
    final RPGame game;
    private final GameConfig config;
    private final Texture backgroundImage;

    private final float ScreenWidth;
    private final float ScreenHeight;

    private final ArrayList<Text> screenTitle;
    private final ArrayList<Text> credits;
    private MyInputProcessor input;

    public CreditScreen() {
        this.game = RPGame.getInstance();

        input = new MyInputProcessor();
        config = GameConfig.getInstance();

        ScreenWidth = config.getResolutionHorizontal();
        ScreenHeight = config.getResolutionVertical();

        backgroundImage = loadTexture(resourceFireworksBackground);

        screenTitle = new ArrayList<Text>();
        credits = new ArrayList<Text>();
    }

    @Override
    public void show() {
        generateTitle();
        generateCredits();
        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        game.batch.begin();
        game.batch.draw(backgroundImage, 0, 0);
        for(Text tTemp : this.screenTitle){
            tTemp.draw();
        }
        for(Text tTemp : this.credits){
            tTemp.draw();
        }
        game.batch.end();

        if(this.input.isEnter() || this.input.isClickTouch()){
            ScreeenManager.getInstance().changeScreen("MainMenuScreen");
            dispose();
        }
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

    private void generateTitle(){
        float mNextY2 = 0;
        int mRest2 = Resources.screenTitleFontSize+50;

        this.screenTitle.add(new Text(Resources.resourceMainFont,Resources.mainTitleFontSize,Color.YELLOW, "Felicidades",true));
        this.screenTitle.add(new Text(Resources.resourceMainFont,Resources.screenTitleFontSize,Color.YELLOW, "Has salvado al reino",true));

        this.screenTitle.get(0).centerTextScreenInX(ScreenHeight+50);
        mNextY2 = this.screenTitle.get(0).getY();

        for(Text tTemp : this.screenTitle){
            tTemp.centerTextScreenInX(ScreenHeight+50);
            mNextY2 -=mRest2;
            tTemp.setY(mNextY2);
        }
    }

    private void generateCredits(){
        int mFontSize = storeMenuOptionsFontSize;
        float mNextY = 0;
        int mRest = Resources.storeMenuOptionsFontSize;

        this.credits.add(new Text(Resources.resourceMainFont,mFontSize, "Desarrollado por:",true));
        this.credits.add(new Text(Resources.resourceMainFont,mFontSize, "Eduardo Romaguera Solano",true));
        this.credits.add(new Text(Resources.resourceMainFont,mFontSize,"Tatiana Araya Muñoz",true));
        this.credits.add(new Text(Resources.resourceMainFont,mFontSize, "Saúl López Molina",true));
        this.credits.add(new Text(Resources.resourceMainFont,mFontSize, " ",true));
        this.credits.add(new Text(Resources.resourceMainFont,mFontSize, "Universidad Cenfotec",true));
        this.credits.add(new Text(Resources.resourceMainFont,mFontSize, "BISOFT-12 Programación con Patrones",true));
        this.credits.add(new Text(Resources.resourceMainFont,mFontSize, "Profesor: Erick Brenes",true));
        this.credits.add(new Text(Resources.resourceMainFont,mFontSize,"Agosto, 2022",true));
        this.credits.add(new Text(Resources.resourceMainFont,mFontSize, " ",true));
        this.credits.add(new Text(Resources.resourceMainFont,mFontSize, "Presione enter o haga click",true));

        this.credits.get(0).centerTextScreenInX(ScreenHeight-400);
        mNextY = this.credits.get(0).getY();

        for(Text mTemp : this.credits){
            mTemp.centerTextScreen();
            mNextY -=mRest;
            mTemp.setY(mNextY);
        }

    }
}
