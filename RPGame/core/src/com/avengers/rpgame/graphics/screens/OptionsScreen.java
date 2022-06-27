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
import static com.avengers.rpgame.utils.Resources.*;

public class OptionsScreen implements Screen {
    final RPGame game;

    private final GameConfig gameConfig;
    private final Music backgroundMusic;
    private final Music soundEffectsMusic;
    private final Texture backgroundImage;
    private final float ScreenWidth;
    private final float ScreenHeight;

    private final ArrayList<Text> menuOptions;
    private int actualSelection =0;
    private final ArrayList<Text> screenTitle;
    private MyInputProcessor input;

    private Slider musicVolumeSlider;
    private Slider soundEffectsSlider;
    private Slider.SliderStyle sliderStyle;



    public OptionsScreen(final RPGame game) {
        this.game = game;
        gameConfig = GameConfig.getInstance();
        input = new MyInputProcessor();

        ScreenWidth = gameConfig.getResolutionHorizontal();
        ScreenHeight = gameConfig.getResolutionVertical();
        menuOptions = new ArrayList<Text>();
        screenTitle = new ArrayList<Text>();
        backgroundImage = new Texture(Gdx.files.internal(resourceOptionsScreenBackground));

        backgroundMusic = loadMusic(resourceFightMusic);;
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(gameConfig.getMusicVolume());

        soundEffectsMusic = loadMusic(resourceAndTheJourneyBeginsMusic);
        soundEffectsMusic.setVolume(gameConfig.getSoundEffectsVolume());


        sliderStyle = new Slider.SliderStyle();//Posiblemente haya que asignarle atributos a este sliderStyle o crearlo con un constructor no vacio
        Skin skin = new Skin(Gdx.files.internal(resourceSkin2));

        musicVolumeSlider = new Slider(0f, 1f, 0.1f, false, skin);
        musicVolumeSlider.setSize(350f, 150f);
        musicVolumeSlider.setPosition(gameConfig.getResolutionHorizontal()/2.9f, gameConfig.getResolutionVertical()/4.5f );
        musicVolumeSlider.setValue(backgroundMusic.getVolume());
        musicVolumeSlider.addListener(new EventListener() {
            public boolean handle(Event event) {
                gameConfig.setMusicVolume(musicVolumeSlider.getValue());
                return false;
            }
        });


        soundEffectsSlider = new Slider(0f, 1f, 0.1f, false, skin);
        soundEffectsSlider.setSize(350f, 150f);
        soundEffectsSlider.setPosition(gameConfig.getResolutionHorizontal()/1.9f, gameConfig.getResolutionVertical()/7f );
        soundEffectsSlider.setValue(backgroundMusic.getVolume());
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
        int mRest = Resources.emphasisFontSize2;

        this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Volumen Música",true));
        this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Volumen Efectos de Sonido",true));
        this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Regresar",true));


        this.menuOptions.get(0).customPositionTextScreen(ScreenWidth/10, ScreenHeight/3+this.screenTitle.get(0).getHeight());
        mNextY = this.menuOptions.get(0).getY();

        for(Text mTemp : this.menuOptions){
            mTemp.customPositionTextScreen(ScreenWidth/10, ScreenHeight/3+this.screenTitle.get(0).getHeight());
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
            int mTime = 300;
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
                executeAction();

        } catch (InterruptedException e){
            game.print(e.toString());
        }
    }

    @Override
    public void show() {
        backgroundMusic.play();
        generateGameTitle();
        generateMenu();
        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        game.batch.begin();

        game.batch.draw(backgroundImage, 0, 0);


        for(Text tTemp : this.screenTitle){
            tTemp.draw();
        }

        for(Text mTemp : this.menuOptions){
            mTemp.draw();
        }
        musicVolumeSlider.draw(game.batch, 1f);
        soundEffectsSlider.draw(game.batch,1f);
        validateKeys();
        game.batch.end();
    }

    private void generateGameTitle(){
        float mNextY2 = 0;
        int mRest2 = Resources.screenTitleFontSize+50;


        this.screenTitle.add(new Text(Resources.resourceMainFont,Resources.screenTitleFontSize, "Menu de Opciones",true));

        this.screenTitle.get(0).customPositionTextScreen(ScreenWidth-this.screenTitle.get(0).getWidth()-50, ScreenHeight-50);
        mNextY2 = this.screenTitle.get(0).getY();

        for(Text tTemp : this.screenTitle){
            tTemp.customPositionTextScreen(ScreenWidth-this.screenTitle.get(0).getWidth()-50, ScreenHeight-50);
            mNextY2 -=mRest2;
            tTemp.setY(mNextY2);
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
        backgroundMusic.dispose();
        backgroundImage.dispose();
    }

    public void validateOption(int opcion)  {
         float volumenMusica = gameConfig.getMusicVolume();
         float volumenEfectosdeSonido = gameConfig.getSoundEffectsVolume();

         int mTime = 300;

        if(opcion == 0 ){

           if(this.input.isMoveRight()){

               if(volumenMusica < 1) {
                    volumenMusica = backgroundMusic.getVolume() + 0.1f;
                   backgroundMusic.setVolume(volumenMusica);

                   gameConfig.setMusicVolume(musicVolumeSlider.getValue() + 0.1f);
                   musicVolumeSlider.setValue(musicVolumeSlider.getValue()+ 0.1f);
               }

               try {
                   Thread.sleep(mTime);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

           }else if(this.input.isMoveLeft()){

                   if(volumenMusica > 0.1) {
                       volumenMusica = backgroundMusic.getVolume() - 0.1f;
                       backgroundMusic.setVolume(volumenMusica);

                       gameConfig.setMusicVolume(musicVolumeSlider.getValue() - 0.1f);
                       musicVolumeSlider.setValue(musicVolumeSlider.getValue() - 0.1f);
                   }else {

                       gameConfig.setMusicVolume(0f);
                   }

               try {
                   Thread.sleep(mTime);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }

        }


        if(opcion == 1){

            if(this.input.isMoveRight()){

                if(volumenEfectosdeSonido < 1) {
                    volumenEfectosdeSonido = gameConfig.getSoundEffectsVolume() + 0.1f;


                    gameConfig.setSoundEffectsVolume(soundEffectsSlider.getValue() + 0.1f);
                    soundEffectsSlider.setValue(soundEffectsSlider.getValue()+ 0.1f);
                }

                try {
                    Thread.sleep(mTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }else if(this.input.isMoveLeft()){

                if(volumenEfectosdeSonido > 0.1) {
                    volumenEfectosdeSonido = gameConfig.getSoundEffectsVolume() - 0.1f;


                    gameConfig.setSoundEffectsVolume(soundEffectsSlider.getValue() - 0.1f);
                    soundEffectsSlider.setValue(soundEffectsSlider.getValue() - 0.1f);
                }else {

                    gameConfig.setSoundEffectsVolume(0f);
                }

                try {
                    Thread.sleep(mTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void executeAction() {

        switch (this.actualSelection){
            case 0:
                validateOption(0);

                break;
            case 1:
                validateOption(1);

                break;
            case 2:

                if(input.isEnter()) {
                    game.setScreen(new MainMenuScreen(game));
                    dispose();
                }

                break;
        }
    }
}
