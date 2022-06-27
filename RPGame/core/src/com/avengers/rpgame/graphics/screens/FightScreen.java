package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.game.GameConfig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.avengers.rpgame.utils.FileManager.loadMusic;
import static com.avengers.rpgame.utils.Resources.*;

public class FightScreen implements Screen {
    final RPGame game;

    private final GameConfig config;
    private final Music backgroundMusic;

    public FightScreen(final RPGame game) {
        this.game = game;
        this.config = GameConfig.getInstance();

        backgroundMusic = loadMusic(resourceFightMusic);;
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(config.getMusicVolume());

    }

    @Override
    public void show() {
        backgroundMusic.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        game.batch.begin();

        game.batch.end();
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
    }
}
