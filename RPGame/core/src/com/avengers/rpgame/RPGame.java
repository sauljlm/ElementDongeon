package com.avengers.rpgame;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.screens.LoadScreen;
import com.avengers.rpgame.graphics.screens.MainMenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class RPGame extends Game {
    public static SpriteBatch batch;
    private GameConfig gameConfig;

    public void create() {
        batch = new SpriteBatch();

        gameConfig = GameConfig.getInstance();
        if(gameConfig.isSkipIntro()){
            this.setScreen(new MainMenuScreen(this));
        } else{
            this.setScreen(new LoadScreen(this));
        }
    }


    public void render() {
        super.render(); // important!
    }

    //Clean memory
    public void dispose() {
        batch.dispose();
    }

    public static void print(String pMes){
        System.out.println(pMes);
    }

    public static void close(){
        Gdx.app.exit();
    }
}
