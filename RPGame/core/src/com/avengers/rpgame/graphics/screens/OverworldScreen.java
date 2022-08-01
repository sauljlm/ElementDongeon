package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.ai.AIManager;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.io.IOManager;
import com.avengers.rpgame.graphics.graphicManagerMediador.Mediador;
import com.avengers.rpgame.graphics.hud.HUD;
import com.avengers.rpgame.graphics.npc.EnemiesManager;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.factory.CharacterFactory;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import static com.avengers.rpgame.utils.FileManager.loadMusic;
import static com.avengers.rpgame.utils.Resources.resourceAndTheJourneyBeginsMusic;
import static com.avengers.rpgame.utils.Resources.resourcePortalTexture;

public class OverworldScreen implements Screen {
    final RPGame game;
    private GameStatus gameStatus;
    private GameConfig config;
    private Music backgroundMusic;

    private IOManager ioManager;
    private AIManager aiManager;
    private Party playerParty;
    private CharacterFactory characterFactory;

    //Graphic manager mediator
    Mediador graphicMediator;

    // End HUD values
    private HUD hudElements;

    //Interactive map objects
    private ArrayList<Vector2> interactiveObjVectors;
    private Sprite accessPortal;

    //Monsters
    EnemiesManager enemiesManager;


    public OverworldScreen(final RPGame game) {
        this.game = game;
        gameStatus = GameStatus.getInstance();
        config = GameConfig.getInstance();

        backgroundMusic = loadMusic(resourceAndTheJourneyBeginsMusic);

        ioManager = new IOManager(game, backgroundMusic);
        aiManager = AIManager.getInstance();

        //Graphic manager mediator
        graphicMediator = new Mediador(game);

        playerParty = GameStatus.getInstance().getParty();
        characterFactory = new CharacterFactory(graphicMediator.getWorld(), game);
        characterFactory.createParty();

        hudElements = new HUD(gameStatus.getParty());

        // Monsters
        enemiesManager = new EnemiesManager();
        enemiesManager.createEnemies();

        //Interactive objects
        interactiveObjVectors = new ArrayList<Vector2>();

        interactiveObjVectors = aiManager.getInteractiveObjectsV();

        this.accessPortal = new Sprite(new Texture(resourcePortalTexture));
        accessPortal.setCenter(interactiveObjVectors.get(0).x,interactiveObjVectors.get(0).y);
        gameStatus.saveOnDB();
    }

    @Override
    public void show() {
        backgroundMusic.play();
        backgroundMusic.setVolume(config.getMusicVolume());
    }

    //This method holds the game logic
    public void  logic(float delta){
//        monsterMovement.chaseActivePlayer(enemyCharacter);
        aiManager.moveAllies(playerParty.getActivePartyMember(), playerParty.getInactivePartyMember(1), 1);
        aiManager.moveAllies(playerParty.getInactivePartyMember(1), playerParty.getInactivePartyMember(2), 1);
        hudElements.update();
    }


    @Override
    public void render(float delta) {
        logic(delta);// Separate the game logic from rendering for clarity

        ScreenUtils.clear(0, 0, 0, 1f);

        //Graphic manager mediator
        graphicMediator.renderGraphicManagers(delta);

        ioManager.processInput("overworld", delta, playerParty);

        game.batch.begin();//Never add game logic inside render begin, end
        playerParty.getPartyMember1().getAnimatedCharacter().draw(delta);
        playerParty.getPartyMember2().getAnimatedCharacter().draw(delta);
        playerParty.getPartyMember3().getAnimatedCharacter().draw(delta);
        enemiesManager.draw(delta);

        for (int j=0;j<=3;j++) {
            game.batch.draw(accessPortal, (interactiveObjVectors.get(j).x-1.25f) * config.getPPM(), (interactiveObjVectors.get(j).y-1.25f) * config.getPPM());
        }

        game.batch.end();

        // hudElements.update(this.userHealth, this.playerLevel, this.magicLevel, this.experiencePoints);
        graphicMediator.changeProjectionMatrix();
        game.batch.begin();//We can stop render, do something and start again
        hudElements.draw(game.batch);
        game.batch.end();
        aiManager.monitorSurroundings(playerParty.getActivePartyMember());
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
        game.dispose();
        graphicMediator.dispose();
        backgroundMusic.dispose();
        ioManager.dispose();
    }
}
