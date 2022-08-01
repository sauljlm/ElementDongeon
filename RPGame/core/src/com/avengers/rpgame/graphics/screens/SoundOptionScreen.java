package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.io.MyInputProcessor;
import com.avengers.rpgame.graphics.text.Text;
import com.avengers.rpgame.utils.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import static com.avengers.rpgame.utils.FileManager.loadMusic;
import static com.avengers.rpgame.utils.FileManager.loadUISkin;
import static com.avengers.rpgame.utils.Resources.*;

public class SoundOptionScreen implements Screen {
    final RPGame game;

    private final GameConfig gameConfig;
    private final Music backgroundMusic;
    private final Music soundEffectsMusic;
    private final Texture backgroundImage;

    private final float ScreenWidth;
    private final float ScreenHeight;

    private final ArrayList<Text> menuOptions;
    private int actualSelection =0;
    private Text screenTitle;
    private MyInputProcessor input;

    private Slider musicVolumeSlider;
    private Slider soundEffectsSlider;

    public SoundOptionScreen(final RPGame game) {
        this.game = game;
        gameConfig = GameConfig.getInstance();
        input = new MyInputProcessor();

        ScreenWidth = gameConfig.getResolutionHorizontal();
        ScreenHeight = gameConfig.getResolutionVertical();
        backgroundImage = new Texture(Gdx.files.internal(resourceOptionsScreenBackground));

        menuOptions = new ArrayList<Text>();
        this.screenTitle = new Text(Resources.resourceMainFont, 100,"Opciones de sonido",true);
        this.screenTitle.centerTextScreenInX( ScreenHeight-150);

        backgroundMusic = loadMusic(resourceFightMusic);;
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(gameConfig.getMusicVolume());

        soundEffectsMusic = loadMusic(resourceSwordAttackSound);
        soundEffectsMusic.play();
        soundEffectsMusic.setVolume(gameConfig.getSoundEffectsVolume());

        Skin skin = loadUISkin(resourceSkin);

        musicVolumeSlider = new Slider(0f, 1f, 0.1f, false, skin);

        musicVolumeSlider.setSize(350f, 100f);
        musicVolumeSlider.setPosition(gameConfig.getResolutionHorizontal()-1200, gameConfig.getResolutionVertical()-800);
        musicVolumeSlider.setValue(backgroundMusic.getVolume());
        musicVolumeSlider.addListener(new EventListener() {
            public boolean handle(Event event) {
                gameConfig.setMusicVolume(musicVolumeSlider.getValue());
                return false;
            }
        });

        soundEffectsSlider = new Slider(0f, 1f, 0.1f, false, skin);
        soundEffectsSlider.setSize(350f, 100f);
        soundEffectsSlider.setPosition(gameConfig.getResolutionHorizontal()-850, gameConfig.getResolutionVertical()-870);
        soundEffectsSlider.setValue(soundEffectsMusic.getVolume());

        soundEffectsSlider.addListener(new EventListener() {
            public boolean handle(Event event) {
                gameConfig.setSoundEffectsVolume(soundEffectsSlider.getValue());
                return false;
            }
        });
    }


    private void generateMenu(){
        int mFontSize = Resources.emphasisFontSize;
        float mNextY = 0;
        int mRest = Resources.emphasisFontSize;

        this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Volumen Música",true));
        this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Volumen Efectos de Sonido",true));
        this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Regresar",true));

        this.menuOptions.get(0).customPositionTextScreen(ScreenWidth/10, ScreenHeight/3+70);
        mNextY = this.menuOptions.get(0).getY();

        for(Text mTemp : this.menuOptions){
            mTemp.customPositionTextScreen(ScreenWidth/10, ScreenHeight/3+70);
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

    @Override
    public void show() {
        generateMenu();

        backgroundMusic.play();
        soundEffectsMusic.play();
        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        game.batch.begin();
        game.batch.draw(backgroundImage, 0, 0);
        this.screenTitle.draw();
        for(Text mTemp : this.menuOptions){
            mTemp.draw();
        }
        musicVolumeSlider.draw(game.batch, 1f);
        soundEffectsSlider.draw(game.batch,1f);
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
        soundEffectsMusic.dispose();
        backgroundImage.dispose();
    }


    public void adjustVolume(String option){
        try{
            float volumen;
            int mTime = (int) (gameConfig.getFrameRate()*3.5);

            if(option.equalsIgnoreCase("music")){
                volumen = gameConfig.getMusicVolume();
                if(this.input.isMoveRight() && volumen<1) {
                    volumen = backgroundMusic.getVolume() + 0.1f;
                }
                if(this.input.isMoveLeft() && volumen>0.01) {
                    volumen = backgroundMusic.getVolume() - 0.1f;
                }
                if(volumen<0.01){
                    volumen=0;
                }
                backgroundMusic.setVolume(volumen);
                gameConfig.setMusicVolume(volumen);
                musicVolumeSlider.setValue(volumen);
                Thread.sleep(mTime);
            }
            else if (option.equalsIgnoreCase("soundEffect")){
                volumen = gameConfig.getSoundEffectsVolume();
                if(this.input.isMoveRight() && volumen<1) {
                    volumen = soundEffectsMusic.getVolume() + 0.1f;
                }
                if(this.input.isMoveLeft() && volumen>0.01) {
                    volumen = soundEffectsMusic.getVolume() - 0.1f;
                }
                if(volumen<0.01){
                    volumen=0;
                }
                soundEffectsMusic.setVolume(volumen);
                gameConfig.setMusicVolume(volumen);
                soundEffectsSlider.setValue(volumen);
                soundEffectsMusic.play();
                Thread.sleep(mTime);
            }

        } catch (InterruptedException e){
            game.print(e.toString());
        }
    }

    private void validateKeys() {
        try{
            int mTime = (int) (gameConfig.getFrameRate()*3.5);
            if(this.input.isMoveDown()){
                this.actualSelection++;
                if(this.actualSelection >2)
                    this.actualSelection =0;
                Thread.sleep(mTime);
                changeOptionColor(this.actualSelection);
            }
            if(this.input.isMoveUp()){
                this.actualSelection--;
                if(this.actualSelection <0)
                    this.actualSelection = 2;
                Thread.sleep(mTime);
                changeOptionColor(this.actualSelection);
            }
            if(this.input.isEnter()){
                executeAction();
            }
            if(this.input.isMoveRight()){
                executeAction();
            }
            if(this.input.isMoveLeft()){
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
                adjustVolume("music");
                break;
            case 1:
                adjustVolume("soundEffect");
                break;
            case 2:
                game.setScreen(new MainMenuScreen(game));
                dispose();
                break;
        }
    }
}
