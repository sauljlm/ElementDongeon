package com.avengers.rpgame;

import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.assetManager.MyAssetManager;
import com.avengers.rpgame.graphics.screens.LoadScreen;
import com.avengers.rpgame.graphics.screens.LoadingOverworldScreen;
import com.avengers.rpgame.graphics.screens.MainMenuScreen;
import com.avengers.rpgame.graphics.screens.ScreeenManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RPGame extends Game {
    private static RPGame instance;

    public SpriteBatch batch;
    private GameConfig gameConfig;

    private RPGame() {
    }

    public void create() {
        batch = new SpriteBatch();

        gameConfig = GameConfig.getInstance();
        if(gameConfig.isSkipIntro()){
            ScreeenManager.getInstance().changeScreen("MainMenuScreen");
        } else{
            ScreeenManager.getInstance().changeScreen("LoadScreen");
        }
    }


    public void render() {
        super.render(); // important!
    }

    //Clean memory
    public void dispose() {
        batch.dispose();
    }

    public void print(String pMes){
//        System.out.println(pMes);
    }

    public void close(){
        Gdx.app.exit();
    }

    public static RPGame getInstance() {
        if (instance == null) {
            instance = new RPGame();
        }
        return instance;
    }
}
