package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.io.MyInputProcessor;
import com.avengers.rpgame.graphics.text.Text;
import com.avengers.rpgame.utils.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import static com.avengers.rpgame.utils.Resources.*;
import static com.avengers.rpgame.utils.Resources.resourceThemeMusic;


public class NewGameScreen implements Screen {
    final RPGame game;
    private final GameConfig config;
    private final Music backgroundMusic;
    private final Texture backgroundImage;
    private final ArrayList<Text> story;

    private MyInputProcessor input;

    public NewGameScreen(final RPGame game) {
        this.game = game;

        input = new MyInputProcessor();
        config = GameConfig.getInstance();

        backgroundImage = new Texture(Gdx.files.internal(resourceNewGameBackground));

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(resourceThemeMusic));
        backgroundMusic.setLooping(true);

        story = new ArrayList<Text>();
    }

    @Override
    public void show() {
        generateStory();

        backgroundMusic.play();
        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        game.batch.begin();
        game.batch.draw(backgroundImage, 0, 0);

        for(Text tTemp : this.story){
            tTemp.draw();
        }

        game.batch.end();

        if(this.input.isEnter() || this.input.isClickTouch()){
            GameStatus.getInstance().setStatus("newGame");
            game.setScreen(new OverworldScreen(game));
            dispose();
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

    private void generateStory(){
        int mFontSize = emphasisFontSize;
        float mNextY = 0;
        int mRest = Resources.emphasisFontSize;

        this.story.add(new Text(Resources.resourceMainFont,mFontSize, "Aqui se puede incluir la historia",true));
        this.story.add(new Text(Resources.resourceMainFont,mFontSize, "y los objetivos del juego",true));
        this.story.add(new Text(Resources.resourceMainFont,mFontSize, "para ganar.",true));
        this.story.add(new Text(Resources.resourceMainFont,mFontSize, "Presione enter o haga click para continuar.",true));

        this.story.get(0).centerTextScreen();
        mNextY = this.story.get(0).getY();

        for(Text mTemp : this.story){
            mTemp.centerTextScreen();
            mNextY -=mRest;
            mTemp.setY(mNextY);
        }

    }
}
