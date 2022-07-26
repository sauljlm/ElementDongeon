package com.avengers.rpgame.graphics.cutScenes;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.screens.MainMenuScreen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static com.avengers.rpgame.utils.FileManager.loadSound;
import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.Resources.*;

public class GameOverCutScene implements ICutScene{
    private RPGame game;
    private final Sprite gameOverTexture;
    private final GameConfig config;
    private final float _sum;
    private final Sound gameOverSound;
    private float _alpha, _wait;
    private boolean loadedR, loadedG, loadedB;

    public GameOverCutScene(RPGame game) {
        this.game = game;
        this.config = GameConfig.getInstance();
        _wait = 0; //Sets a time to wait before showing company logo
        _alpha = 0; //Variable alpha to fade in the sprite
        _sum = 0.005f; //Speed to change alpha
        this.loadedR = false;
        this.loadedG = false;
        this.loadedB = false;
        gameOverSound = loadSound((resourceIntroSound));

        gameOverTexture = new Sprite();
        gameOverTexture.setTexture(loadTexture(resourceGameOverBackground));
        gameOverTexture.setSize(config.getResolutionHorizontal(), config.getResolutionVertical());
        gameOverTexture.setRegion(0,0,config.getResolutionHorizontal(), config.getResolutionVertical());
        gameOverTexture.setCenter(config.getResolutionHorizontal()/2,config.getResolutionVertical()/2);
        gameOverTexture.setAlpha(0);
    }

    @Override
    public void show() {
        gameOverSound.play();
    }

    @Override
    public void draw(float delta) {
        game.batch.begin();
        gameOverTexture.draw(game.batch);
        game.batch.end();
        fadeIn();
        //When company logo has been loaded and after a sintetic delay, redirect to main screen
        if (loadedR && _wait > 3) {
//            game.setScreen(new MainMenuScreen(game));
            dispose();
        }

    }

    private void fadeIn(){
        _wait += _sum;
        if(!loadedR && _wait > 0){
            _alpha += _sum;
            if(_alpha>=1){
                loadedR = true;
            }
        }
        if(loadedR && _wait <3){
            _alpha =1;
        }
//        if(loaded && _wait >2.5){
//            _alpha =0;
//        }
        Color color = new Color(_alpha,_alpha,_alpha, 1);
        gameOverTexture.setColor(color);
//        gameOverTexture.s
//        gameOverTexture.setAlpha(_alpha);
    }

    @Override
    public void dispose() {
    }
}
