package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.ai.RefereeBattleAI;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.io.IOManager;
import com.avengers.rpgame.logic.entities.Party;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.avengers.rpgame.utils.FileManager.loadMusic;
import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.Resources.*;

public class FightScreen implements Screen {
    final RPGame game;

    private final GameConfig config;
    private final Music backgroundMusic;
    private IOManager ioManager;
    private Texture backgroundImage;
    private RefereeBattleAI refereeBattleAI;
    private Party playerParty;
    private Party enemyParty;

    public FightScreen(final RPGame game, Party playerParty) {
        this.game = game;
        this.config = GameConfig.getInstance();
        this.playerParty = playerParty;
        Party enemyPartyNew = new Party();
        enemyPartyNew = playerParty;
        this.enemyParty = enemyPartyNew;
        ioManager = new IOManager(game);

        backgroundMusic = loadMusic(resourceFightMusic);;
        backgroundMusic.setLooping(true);
        backgroundImage = loadTexture(resourceFightBackgroundForest);

        refereeBattleAI = new RefereeBattleAI();
    }

    @Override
    public void show() {
        backgroundMusic.play();
    }

    //This method holds the game logic
    public void  logic(float delta){
        refereeBattleAI.manageBattle(playerParty, enemyParty);
    }

    @Override
    public void render(float delta) {
        logic(delta);// Separate the game logic from rendering for clarity
        ScreenUtils.clear(0, 0, 0, 1);

        ioManager.processInput("battle", delta, playerParty);

        game.batch.begin();
        game.batch.draw(backgroundImage, 0, 0);
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
        backgroundMusic.play();
    }

    @Override
    public void hide() {
        backgroundMusic.pause();
    }

    @Override
    public void dispose() {
        this.pause();
        this.hide();
    }
}
