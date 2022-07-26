package com.avengers.rpgame.graphics.cutScenes;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.screens.MainMenuScreen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static com.avengers.rpgame.utils.FileManager.loadSound;
import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.Resources.resourceCompanyLogo;
import static com.avengers.rpgame.utils.Resources.resourceIntroSound;

public class GameIntroCutScene implements ICutScene{
    private RPGame game;
    private final Sprite companyLogo;
    private final GameConfig config;
    private final float _sum;
    private final Sound introSound;
    private float _alpha, _wait;
    private boolean loaded;

    public GameIntroCutScene(RPGame game) {
        this.game = game;
        this.config = GameConfig.getInstance();
        _wait = -1.3f; //Sets a time to wait before showing company logo
        _alpha = 0; //Variable alpha to fade in the sprite
        _sum = 0.005f; //Speed to change alpha
        this.loaded = false;

        introSound = loadSound((resourceIntroSound));

        companyLogo = new Sprite();
        companyLogo.setTexture(loadTexture(resourceCompanyLogo));
        companyLogo.setSize(407, 504);
        companyLogo.setRegion(0,0,407,504);
        companyLogo.setCenter(config.getResolutionHorizontal()/2,config.getResolutionVertical()/2);
        companyLogo.setAlpha(0);
    }

    @Override
    public void show() {
        introSound.play();
    }

    @Override
    public void draw(float delta) {
        game.batch.begin();
        companyLogo.draw(game.batch);
        game.batch.end();
        fadeIn();

        //When company logo has been loaded and after a sintetic delay, redirect to main screen
        if (loaded && _wait > 3) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
    }

    @Override
    public void dispose() {
        introSound.dispose();
    }

    private void fadeIn(){
        _wait += _sum;
        if(!loaded && _wait > 0){
            _alpha += _sum;
            if(_alpha>=1){
                loaded = true;
            }
        }
        if(loaded && _wait <3){
            _alpha =1;
        }
        if(loaded && _wait >2.5){
            _alpha =0;
        }
        companyLogo.setAlpha(_alpha);
    }
}
