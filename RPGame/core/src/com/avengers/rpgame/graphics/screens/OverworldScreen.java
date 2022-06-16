package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.io.IOManager;
import com.avengers.rpgame.graphics.camera.CameraManager;
import com.avengers.rpgame.graphics.hud.HUD;
import com.avengers.rpgame.graphics.map.MapManager;
import com.avengers.rpgame.graphics.physics.PhysicsManager;
import com.avengers.rpgame.logic.entities.Character;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import static com.avengers.rpgame.utils.FileManager.*;
import static com.avengers.rpgame.utils.Resources.*;

public class OverworldScreen implements Screen {
    final RPGame game;
    private GameConfig config;
    private Music backgroundMusic;
    private MapManager mapManager;
    private PhysicsManager physicsManager;
    private Character character;
    private CameraManager cameraManager;
    private IOManager ioManager;

    //HUD default values
    private int userHealth = 100;
    private int playerLevel = 34;
    private int magicLevel = 12;
    private int experiencePoints = 0;
    private int characterClass = 2;
    // End HUD values
    private HUD hudElements;

    public OverworldScreen(final RPGame game) {
        this.game = game;
        config = GameConfig.getInstance();

        backgroundMusic = loadMusic(resourceAndTheJourneyBeginsMusic);

        ioManager = new IOManager();
        cameraManager = new CameraManager(game);
        mapManager = new MapManager(resourceOverworldMap, cameraManager.getCamera(), game);
        physicsManager = new PhysicsManager(new Vector2(0, 0f), mapManager, cameraManager.getCamera(), true);
        character = new Character("graphics/sprites/actors/player/tile000.png", physicsManager.getWorld(), game);
        hudElements = new HUD(this.userHealth, this.playerLevel, this.magicLevel, this.experiencePoints,this.characterClass);
    }

    @Override
    public void show() {
        backgroundMusic.play();
        backgroundMusic.setVolume(config.getMusicVolume());
    }

    //This method holds the game logic
    public void  logic(float delta){

    }



    @Override
    public void render(float delta) {
        logic(delta);// Separate the game logic from rendering for clarity

        ScreenUtils.clear(0, 0, 0, 1f);

        mapManager.render();//Render the map first!
        physicsManager.simulate();
        ioManager.processInput("overworld", delta, character);
        cameraManager.action(delta, character);

        game.batch.begin();//Never add game logic inside render begin, end
        character.draw(delta);
//        hudElements.draw(game.batch, cameraManager);
        game.batch.end();

        hudElements.update(this.userHealth, this.playerLevel, this.magicLevel, this.experiencePoints,this.characterClass);
        cameraManager.changeProjectionMatrix();
        game.batch.begin();//We can stop render, do something and start again
        hudElements.draw(game.batch);
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
        game.dispose();
        mapManager.dispose();
        physicsManager.dispose();
        cameraManager.dispose();
        ioManager.dispose();
    }
}
