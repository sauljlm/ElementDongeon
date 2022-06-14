package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.io.MyInputProcessor;
import com.avengers.rpgame.graphics.text.FontFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import static com.avengers.rpgame.utils.Resources.*;

public class MainMenuScreen implements Screen {

    final RPGame game;
    private final GameConfig config;
    private final Music backgroundMusic;
    private final Texture backgroundImage;
    private final ArrayList<String> menuStrings;
    private final BitmapFont gameFont;
    private final float menuPosX;
    private final float menuPosY;
    private MyInputProcessor input;
    private int selected;

    OrthographicCamera camera;

    public MainMenuScreen(final RPGame game) {
        this.game = game;
        input = new MyInputProcessor();
        config = GameConfig.getInstance();

        menuPosX = config.getResolutionHorizontal()/10;
        menuPosY = config.getResolutionHorizontal()/10;

        backgroundImage = new Texture(Gdx.files.internal(resourceMainScreenBackground));

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(resourceThemeMusic));
        backgroundMusic.setLooping(true);
        gameFont = FontFactory.createBitMapFont(Gdx.files.internal(resourcePixelFont), 100, Color.GREEN, true, Color.BLACK);

        menuStrings = new ArrayList<String>();
        menuStrings.add("EXIT");
        menuStrings.add("SETTINGS");
        menuStrings.add("NEW GAME");

    }

    @Override
    public void show() {
        backgroundMusic.play();
//        MyInputProcessor inputProcessor = new MyInputProcessor();
        Gdx.input.setInputProcessor(input);

//        Gdx.input.setInputProcessor(new InputAdapter() {
////            @Override
//            public boolean touchDown (int x, int y, int pointer, int button) {
//                // your touch down code here
//                return true; // return true to indicate the event was handled
//            }
//
////            @Override
//            public boolean touchUp (int x, int y, int pointer, int button) {
//                // your touch up code here
//                return true; // return true to indicate the event was handled
//            }
//        });

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 1, 0, 1);

        game.batch.begin();
        game.batch.draw(backgroundImage, 0, 0);
        for( int i = 0; i < menuStrings.size(); i++){
            gameFont.draw(game.batch, menuStrings.get(i), menuPosX, menuPosY+ +100*i);
        }
        game.batch.end();

        if(input.isClickTouch()){
            System.out.println("CLICK");
        }
//        if(input.isEnter()){
//            System.out.println("ENTER");
//            game.setScreen(new GameScreenEasterEgg(game));
//            dispose();
//        }
        if(input.isEnter()){
            System.out.println("Let the game begin");
            game.setScreen(new OverworldScreen(game));
            dispose();
        }
//        if (Gdx.input.scur) {
//            dispose();
//        }
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
        gameFont.dispose();
        //TODO-EDUARDO
    }
}
