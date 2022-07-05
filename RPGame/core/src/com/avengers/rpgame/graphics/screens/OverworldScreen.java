package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.ai.AIManager;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.io.IOManager;
import com.avengers.rpgame.graphics.camera.CameraManager;
import com.avengers.rpgame.graphics.hud.HUD;
import com.avengers.rpgame.graphics.map.MapManager;
import com.avengers.rpgame.graphics.physics.PhysicsManager;
import com.avengers.rpgame.game.GameInformation;
import com.avengers.rpgame.json.DataStorage;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.builder.CharacterBuilder;
import com.avengers.rpgame.logic.entities.EntitiesBuilderDirector;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import static com.avengers.rpgame.utils.FileManager.*;
import static com.avengers.rpgame.utils.Resources.*;

public class OverworldScreen implements Screen {
    final RPGame game;
    private GameInformation gameInfo;
    private GameConfig config;
    private Music backgroundMusic;
    private MapManager mapManager;
    private PhysicsManager physicsManager;
    private EntitiesBuilderDirector director;
    private CharacterBuilder characterBuilder;
    private AbstractCharacter playerCharacter;
    private AbstractCharacter ally1Character;
    private AbstractCharacter ally2Character;
    private CameraManager cameraManager;
    private IOManager ioManager;
    private AIManager aiManager;
    private Party playerParty;

    private DataStorage dataStorage; //Temporal

    //HUD default values
    private int userHealth = 100;
    private int playerLevel = 34;
    private int magicLevel = 12;
    private int experiencePoints = 0;
    private int characterClass = 2;
    // End HUD values
    private HUD hudElements;

    public OverworldScreen(final RPGame game, GameInformation information) {
        this.game = game;
        this.gameInfo = information;
        config = GameConfig.getInstance();

        backgroundMusic = loadMusic(resourceAndTheJourneyBeginsMusic);
        director = new EntitiesBuilderDirector();
        characterBuilder = new CharacterBuilder();

        ioManager = new IOManager(game, backgroundMusic);
        cameraManager = new CameraManager(game);
        mapManager = new MapManager(resourceOverworldMap, cameraManager.getCamera(), game);
        physicsManager = new PhysicsManager(new Vector2(0, 0f), mapManager, cameraManager.getCamera(), true);
        aiManager = new AIManager();

        playerParty = new Party();

        //TODO encapsulate this into an external class that takes care of creating the characters
        if(information.getIdCharacterClass()==1){
            director.buildKnight(characterBuilder, physicsManager.getWorld(), game, information.getUsername());
            playerCharacter = characterBuilder.getResult();
            director.buildMage(characterBuilder, physicsManager.getWorld(), game, "Merlin");
            ally1Character = characterBuilder.getResult();
            director.buildArcher(characterBuilder, physicsManager.getWorld(), game, "Robin");
            ally2Character = characterBuilder.getResult();
        }
        if(information.getIdCharacterClass()==2){
            director.buildArcher(characterBuilder, physicsManager.getWorld(), game, information.getUsername());
            playerCharacter = characterBuilder.getResult();
            director.buildKnight(characterBuilder, physicsManager.getWorld(), game, "Lancelot");
            ally1Character = characterBuilder.getResult();
            director.buildMage(characterBuilder, physicsManager.getWorld(), game, "Merlin");
            ally2Character = characterBuilder.getResult();
        }
        if(information.getIdCharacterClass()==3){
            director.buildMage(characterBuilder, physicsManager.getWorld(), game, information.getUsername());
            playerCharacter = characterBuilder.getResult();
            director.buildKnight(characterBuilder, physicsManager.getWorld(), game, "Lancelot");
            ally1Character = characterBuilder.getResult();
            director.buildArcher(characterBuilder, physicsManager.getWorld(), game, "Robin");
            ally2Character = characterBuilder.getResult();
        }
        playerParty.setPartyMember1(playerCharacter);
        playerParty.setPartyMember2(ally1Character);
        playerParty.setPartyMember3(ally2Character);
        gameInfo.setPlayerParty(playerParty);

        hudElements = new HUD(this.userHealth, this.playerLevel, this.magicLevel, this.experiencePoints,this.characterClass);
        System.out.println(playerCharacter);
        System.out.println(ally1Character);
        System.out.println(ally2Character);

        dataStorage=new DataStorage(); //Temporal
        System.out.println(dataStorage.getData());

    }

    @Override
    public void show() {
        backgroundMusic.play();
        backgroundMusic.setVolume(config.getMusicVolume());
    }

    //This method holds the game logic
    public void  logic(float delta){
        aiManager.moveAllies(playerCharacter, ally1Character, 1);
        aiManager.moveAllies(ally1Character, ally2Character, 1);
    }


    @Override
    public void render(float delta) {
        logic(delta);// Separate the game logic from rendering for clarity

        ScreenUtils.clear(0, 0, 0, 1f);

        mapManager.render();//Render the map first!
        physicsManager.simulate();
        ioManager.processInput("overworld", delta, playerParty);
        cameraManager.action(delta, playerCharacter);

        game.batch.begin();//Never add game logic inside render begin, end
        playerParty.getPartyMember3().getAnimatedCharacter().draw(delta);
        playerParty.getPartyMember2().getAnimatedCharacter().draw(delta);
        playerParty.getPartyMember1().getAnimatedCharacter().draw(delta);
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
        backgroundMusic.play();
    }

    @Override
    public void hide() {
        backgroundMusic.pause();
    }

    @Override
    public void dispose() {
        game.dispose();
        mapManager.dispose();
        physicsManager.dispose();
        backgroundMusic.dispose();
        cameraManager.dispose();
        ioManager.dispose();
    }
}
