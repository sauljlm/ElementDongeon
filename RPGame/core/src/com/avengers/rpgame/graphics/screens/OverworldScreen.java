package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.io.IOManager;
import com.avengers.rpgame.graphics.camera.CameraManager;
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

    public OverworldScreen(final RPGame game) {
        this.game = game;
        config = GameConfig.getInstance();

        backgroundMusic = loadMusic(resourceAndTheJourneyBeginsMusic);

        ioManager = new IOManager();
        cameraManager = new CameraManager(game);
        mapManager = new MapManager(resourceOverworldMap, cameraManager.getCamera(), game);
        physicsManager = new PhysicsManager(new Vector2(0, 0f), mapManager, cameraManager.getCamera(), true);
        character = new Character("graphics/sprites/actors/player/tile000.png", physicsManager.getWorld(), game);
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
