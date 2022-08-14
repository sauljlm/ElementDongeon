package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.audio.SoundEffectsManager;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.io.MyInputProcessor;
import com.avengers.rpgame.graphics.text.Text;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
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

public class SaveSlotSelectionScreen implements Screen {

    final RPGame game;
    private final GameConfig config;
    private final Texture backgroundImage;

    private final float ScreenWidth;
    private final float ScreenHeight;

    private final ArrayList<Text> menuOptions;
    private int actualSelection =0;
    private Text title;
    private Text subTitleSave;

    private MyInputProcessor input;
    private AbstractCharacter character;
    private int selectionMemento;

    public SaveSlotSelectionScreen() {
        this.game = RPGame.getInstance();
        this.input = new MyInputProcessor();
        this.config = GameConfig.getInstance();

        ScreenWidth = config.getResolutionHorizontal();
        ScreenHeight = config.getResolutionVertical();
        backgroundImage = loadTexture(resourceBlurBackground);

        menuOptions = new ArrayList<Text>();
        this.title = new Text(Resources.resourceMainFont, Resources.screenTitleFontSize+20,"LA AVENTURA COMIENZA",true);
        this.title.centerTextScreenInX( ScreenHeight-150);

        this.subTitleSave = new Text(Resources.resourceMainFont, Resources.screenTitleFontSize-20,"Guardar juego en:",true);
        this.subTitleSave.centerTextScreenInX( ScreenHeight-400);
    }

    @Override
    public void show() {
        generateMenu();
        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        game.batch.begin();
        game.batch.draw(backgroundImage, 0, 0);
        for(Text mTemp : this.menuOptions){
            mTemp.draw();
        }
        this.title.draw();
        this.subTitleSave.draw();
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

    private void generateMenu(){
        int mFontSize = Resources.emphasisFontSize;
        float mNextY = 0;
        int mRest = Resources.emphasisFontSize;

        this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Juego #1",true));
        this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Juego #2",true));
        this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Juego #3",true));
        this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Regresar",true));

        this.menuOptions.get(0).centerTextScreen();
        mNextY = this.menuOptions.get(0).getY()+150;


        for(int i = 0; i < this.menuOptions.size(); i++){
            Text mTemp = this.menuOptions.get(i);
            if(i<=2){
                mNextY -=mRest;
            } else if (i==3) {
                mNextY -= mRest+70;
            } else {
                mNextY -= mRest;
            }
            mTemp.centerTextScreen();
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

    private void playSelectionSound(){
        if(actualSelection != selectionMemento){
            SoundEffectsManager.getInstance().play(menuSoundEffect, false);
            selectionMemento = actualSelection;
        }
    }

    private void executeAction() {
        playSelectionSound();
        switch (this.actualSelection){
            case 0:
                GameStatus.getInstance().setSaveSlot(1);
                GameStatus.getInstance().setStatus("newGame");
                ScreeenManager.getInstance().changeScreen("CharacterSelectionScreen");
                dispose();
                break;
            case 1:
                GameStatus.getInstance().setSaveSlot(2);
                GameStatus.getInstance().setStatus("newGame");
                ScreeenManager.getInstance().changeScreen("CharacterSelectionScreen");
                dispose();
                break;
            case 2:
                GameStatus.getInstance().setSaveSlot(3);
                GameStatus.getInstance().setStatus("newGame");
                ScreeenManager.getInstance().changeScreen("CharacterSelectionScreen");
                dispose();
                break;
            case 3:
                ScreeenManager.getInstance().changeScreen("MainMenuScreen");
                dispose();
                break;
        }
    }
}
