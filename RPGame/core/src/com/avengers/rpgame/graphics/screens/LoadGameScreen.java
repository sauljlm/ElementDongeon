package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.audio.SoundEffectsManager;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.io.MyInputProcessor;
import com.avengers.rpgame.graphics.text.Text;
import com.avengers.rpgame.utils.Resources;
import com.avengers.rpgame.utils.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.Resources.*;

public class LoadGameScreen implements Screen {
    final RPGame game;
    private GameStatus gameStatus;
    private final GameConfig config;
    private final Texture backgroundImage;

    private final ArrayList<Text> loadOptions;
    private int actualSelection =0;
    private final float ScreenWidth;
    private final float ScreenHeight;
    private Text inputTitle;

    private int selectionMemento;
    private MyInputProcessor input;

    public LoadGameScreen() {
        this.game = RPGame.getInstance();
        input = new MyInputProcessor();
        config = GameConfig.getInstance();

        ScreenWidth = config.getResolutionHorizontal();
        ScreenHeight = config.getResolutionVertical();
        backgroundImage = loadTexture(resourceBlurBackground);

        loadOptions = new ArrayList<Text>();
        this.inputTitle = new Text(Resources.resourceMainFont, Resources.screenTitleFontSize,"Juegos guardados",true);
        this.inputTitle.centerTextScreenInX( ScreenHeight-150);
    }

    @Override
    public void show() {
        generateLoadGames();
        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        game.batch.begin();
        game.batch.draw(backgroundImage, 0, 0);
        for(Text mTemp : this.loadOptions){
            mTemp.draw();
        }
        this.inputTitle.draw();
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
    }

    private void generateLoadGames(){
        int mFontSize = Resources.emphasisFontSize;
        float mNextY = 0;
        int mRest = Resources.emphasisFontSize;

        this.loadOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Juego #1",true));
        this.loadOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Juego #2",true));
        this.loadOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Juego #3",true));
        this.loadOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Regresar",true));

        this.loadOptions.get(0).centerTextScreen();
        mNextY = this.loadOptions.get(0).getY()+100;

        for(Text mTemp : this.loadOptions){
            mTemp.centerTextScreen();
            mNextY -=mRest;
            mTemp.setY(mNextY);
        }
        this.loadOptions.get(3).setY(mNextY-150);

        changeOptionColor(0);
    }

    private void changeOptionColor(int pId){
        playSelectionSound();
        for (Text mTemp: this.loadOptions)
            mTemp.setColor(Color.WHITE);
        if(pId>=0)
            this.loadOptions.get(pId).setColor(Color.FIREBRICK);
        this.actualSelection = pId;
    }

    private void validateKeys() {
        int mTime = (int) (config.getFrameRate()*3.5);
        if(this.input.isMoveDown() && Utils.getInstance().skipFrames()){
            this.actualSelection++;
            if(this.actualSelection >3)
                this.actualSelection =0;
            changeOptionColor(this.actualSelection);
        }
        if(this.input.isMoveUp() && Utils.getInstance().skipFrames()){
            this.actualSelection--;
            if(this.actualSelection <0)
                this.actualSelection =3;
            changeOptionColor(this.actualSelection);
        }
        if(this.input.isEnter()){
            executeAction();
        }
    }

    private void validateMouse() {
        for (int i = 0; i < this.loadOptions.size(); i++) {
            float mX = this.input.getMouseX(), mY = this.input.getMouseY();
            Text mTemp = this.loadOptions.get(i);
            if (mX >= mTemp.getX() && mX <= (mTemp.getX() + mTemp.getWidth()))
                if (mY >= (mTemp.getY() - mTemp.getHeight()) && mY <= mTemp.getY()){
                    changeOptionColor(i);
                    if(this.input.isClickTouch())
                        executeAction();
                }
        }
    }

    private void playSelectionSound(){
        if(actualSelection != selectionMemento){
            SoundEffectsManager.getInstance().play(menuSoundEffect, false);
            selectionMemento = actualSelection;
        }
    }

    private void executeAction() {
        playSelectionSound();
        GameStatus.getInstance().setStatus("loadedGame");
        try {
            switch (this.actualSelection) {
                case 0:
                    GameStatus.getInstance().loadFromDB(1);
                    GameStatus.getInstance().setStatus("loadedGame");
                    ScreeenManager.getInstance().changeScreen("OverworldScreen");
                    break;
                case 1:
                    GameStatus.getInstance().loadFromDB(2);
                    GameStatus.getInstance().setStatus("loadedGame");
                    ScreeenManager.getInstance().changeScreen("OverworldScreen");
                    break;
                case 2:
                    GameStatus.getInstance().loadFromDB(3);
                    GameStatus.getInstance().setStatus("loadedGame");
                    ScreeenManager.getInstance().changeScreen("OverworldScreen");
                    break;
                case 3:
                    ScreeenManager.getInstance().changeScreen("MainMenuScreen");
                    break;
            }
        } catch (Exception e){
            ScreeenManager.getInstance().changeScreen("MainMenuScreen");
        }
    }

}
