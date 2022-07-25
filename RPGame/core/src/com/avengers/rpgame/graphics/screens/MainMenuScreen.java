package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.graphics.text.Text;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.io.MyInputProcessor;
import com.avengers.rpgame.utils.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import static com.avengers.rpgame.utils.FileManager.loadMusic;
import static com.avengers.rpgame.utils.Resources.*;

public class MainMenuScreen implements Screen {
    final RPGame game;
    private final GameConfig config;
    private final Music backgroundMusic;
    private final Texture backgroundImage;

    private final float ScreenWidth;
    private final float ScreenHeight;

    private final ArrayList<Text> menuOptions;
    private int actualSelection =0;
    private final ArrayList<Text> gameTitle;

    private MyInputProcessor input;

    public MainMenuScreen(final RPGame game) {
        this.game = game;
        input = new MyInputProcessor();
        config = GameConfig.getInstance();

        ScreenWidth = config.getResolutionHorizontal();
        ScreenHeight = config.getResolutionVertical();

        backgroundImage = new Texture(Gdx.files.internal(resourceMainScreenBackground));

        backgroundMusic = loadMusic(resourceThemeMusic);
        backgroundMusic.setLooping(true);

        menuOptions = new ArrayList<Text>();
        gameTitle = new ArrayList<Text>();

    }

    @Override
    public void show() {
        generateGameTitle();
        generateMenu();

        backgroundMusic.play();
        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        game.batch.begin();
        game.batch.draw(backgroundImage, 0, 0);

        for(Text tTemp : this.gameTitle){
            tTemp.draw();
        }

        for(Text mTemp : this.menuOptions){
            mTemp.draw();
        }

        game.batch.end();

        validateMouse();
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


    private void generateGameTitle(){
        float mNextY2 = 0;
        int mRest2 = Resources.screenTitleFontSize+50;

        this.gameTitle.add(new Text(Resources.resourceMainFont,Resources.mainTitleFontSize, "Element Dungeon",true));
        this.gameTitle.add(new Text(Resources.resourceMainFont,Resources.screenTitleFontSize, "The Awakening",true));

        this.gameTitle.get(0).customPositionTextScreen(ScreenWidth-this.gameTitle.get(0).getWidth()-50, ScreenHeight-50);
        mNextY2 = this.gameTitle.get(0).getY();

        for(Text tTemp : this.gameTitle){
            tTemp.customPositionTextScreen(ScreenWidth-this.gameTitle.get(0).getWidth()-50, ScreenHeight-50);
            mNextY2 -=mRest2;
            tTemp.setY(mNextY2);
        }
    }

    private void generateMenu(){
        int mFontSize = Resources.emphasisFontSize;
        float mNextY = 0;
        int mRest = Resources.emphasisFontSize;

        this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Nuevo Juego",true));
        this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Cargar Juego",true));
        this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Opciones",true));
        this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Salir",true));

        this.menuOptions.get(0).customPositionTextScreen(ScreenWidth/10, ScreenHeight/3+this.gameTitle.get(0).getHeight());
        mNextY = this.menuOptions.get(0).getY();

        for(Text mTemp : this.menuOptions){
            mTemp.customPositionTextScreen(ScreenWidth/10, ScreenHeight/3+this.gameTitle.get(0).getHeight());
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
        this.actualSelection = pId;
    }

    private void validateKeys() {
        try{
            int mTime = (int) (config.getFrameRate()*3.5);
            //int mTime = 180;
            if(this.input.isMoveDown()){
                this.actualSelection++;
                if(this.actualSelection >3)
                    this.actualSelection =0;
                Thread.sleep(mTime);
                changeOptionColor(this.actualSelection);
            }
            if(this.input.isMoveUp()){
                this.actualSelection--;
                if(this.actualSelection <0)
                    this.actualSelection =3;
                Thread.sleep(mTime);
                changeOptionColor(this.actualSelection);
            }
            if(this.input.isEnter()){
                executeAction();
            }
        } catch (InterruptedException e){
            game.print(e.toString());
        }
    }

    private void validateMouse() {
        for (int i = 0; i < this.menuOptions.size(); i++) {
            float mX = this.input.getMouseX(), mY = this.input.getMouseY();
            Text mTemp = this.menuOptions.get(i);
            if (mX >= mTemp.getX() && mX <= (mTemp.getX() + mTemp.getWidth()))
                if (mY >= (mTemp.getY() - mTemp.getHeight()) && mY <= mTemp.getY()){
                    changeOptionColor(i);
                    if(this.input.isClickTouch())
                        executeAction();
                }
        }
    }

    private void executeAction() {
        switch (this.actualSelection){
            case 0:
                game.setScreen(new CharacterSelectionScreen(game));
                dispose();
                break;
            case 1:
                game.setScreen(new LoadGameScreen(game));
                dispose();
                break;
            case 2:
                game.setScreen(new SoundOptionScreen(game));
                dispose();
                break;
            case 3:
                game.close();
                break;
        }
    }
}
