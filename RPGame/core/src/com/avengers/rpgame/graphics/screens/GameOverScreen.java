package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.audio.SoundEffectsManager;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.io.MyInputProcessor;
import com.avengers.rpgame.graphics.assetManager.MyAssetManager;
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

public class GameOverScreen implements Screen {
        private RPGame game;
        private GameStatus gameStatus;
        private GameConfig config;
        private final Texture backgroundImage;

        private final float ScreenWidth;
        private final float ScreenHeight;

        private final ArrayList<Text> menuOptions;
        private int actualSelection =0;
        private final ArrayList<Text> gameTitle;

        private MyInputProcessor input;
        private MyAssetManager assetManager;

        private int selectionMemento;

        public GameOverScreen() {
            game = RPGame.getInstance();
            config = GameConfig.getInstance();
            gameStatus = GameStatus.getInstance();
            assetManager = MyAssetManager.getInstance();
            input = new MyInputProcessor();

            ScreenWidth = config.getResolutionHorizontal();
            ScreenHeight = config.getResolutionVertical();
            backgroundImage = loadTexture(resourceBlackWhiteBackground);

            menuOptions = new ArrayList<Text>();
            gameTitle = new ArrayList<Text>();

        }

        @Override
        public void show() {
            generateGameTitle();
            generateMenu();
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
        }


        private void generateGameTitle(){
            float mNextY2 = 0;
            int mRest2 = Resources.screenTitleFontSize+50;

            this.gameTitle.add(new Text(Resources.resourceMainFont,Resources.mainTitleFontSize, "GAME OVER",true));

            this.gameTitle.get(0).customPositionTextScreen(ScreenWidth/2 -gameTitle.get(0).getWidth(), ScreenHeight/3*2);
            mNextY2 = this.gameTitle.get(0).getY();

            for(Text tTemp : this.gameTitle){
                tTemp.customPositionTextScreen(ScreenWidth/2-gameTitle.get(0).getWidth()/2, ScreenHeight/3*2);
//                mNextY2 -=mRest2;
//                tTemp.setY(mNextY2);
            }
        }

        private void generateMenu(){
            int mFontSize = Resources.emphasisFontSize;
            float mNextY = 0;
            int mRest = Resources.emphasisFontSize;

            this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Intentarlo otra vez",true));
            this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Salir",true));

            this.menuOptions.get(0).customPositionTextScreen(ScreenWidth/2 -menuOptions.get(0).getWidth()/2, ScreenHeight/3+this.gameTitle.get(0).getHeight());
            mNextY = this.menuOptions.get(0).getY();
            for(Text mTemp : this.menuOptions){
                mTemp.customPositionTextScreen(ScreenWidth/2-menuOptions.get(0).getWidth()/2, ScreenHeight/3+this.gameTitle.get(0).getHeight());
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
            this.actualSelection = pId;
        }

        private void validateKeys() {
            int mTime = (int) (config.getFrameRate()*3.5);
            //int mTime = 180;
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
                    gameStatus.loadFromDB(GameStatus.getInstance().getSaveSlot());
                    gameStatus.setStatus("defeated");
                    ScreeenManager.getInstance().changeScreen("OverworldScreen");
                    dispose();
                    break;
                case 1:
                    ScreeenManager.getInstance().changeScreen("MainMenuScreen");
                    break;
            }
        }
    }
