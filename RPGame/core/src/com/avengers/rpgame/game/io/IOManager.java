package com.avengers.rpgame.game.io;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.screens.FightScreen;
import com.avengers.rpgame.graphics.screens.OverworldScreen;
import com.avengers.rpgame.graphics.screens.PauseScreen;
import com.avengers.rpgame.logic.entities.EntitiesBuilderDirector;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.graphics.screens.StoreScreen;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.builder.CharacterBuilder;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.DynamicAnimatedCharacter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;

//TODO Improve this and create different controls for different screens
public class IOManager {
    private MyInputProcessor inputProcessor;
    private GameConfig config;
    private RPGame game;
    private Music backgroundMusic;

    public IOManager(RPGame game, Music backgroundMusic) {
        inputProcessor = new MyInputProcessor();
        Gdx.input.setInputProcessor(inputProcessor);
        config = GameConfig.getInstance();
        this.game = game;
        this.backgroundMusic = backgroundMusic;
    }

    public IOManager(RPGame game) {
        inputProcessor = new MyInputProcessor();
        Gdx.input.setInputProcessor(inputProcessor);
        config = GameConfig.getInstance();
        this.game = game;
    }

    //the idea for this method is to process different request of IO processInput differently acording to the screen
    public void processInput(String type, float delta, Party playerParty){
//        playerParty.getPartyMember1().setPosition(playerParty.getPartyMember1().getAnimatedCharacter().getPlayer().getPosition());
        if(type.equals("overworld")){
            overworldUpdate(delta, playerParty);

        }
        if(type.equals("battle")){
            battleUpdate(delta, playerParty);
        }
        if(type.equals("pauseMenu")){

        }
        if(type.equals("mainScreen")){

        }
    }

    //Process input for overworld gameplay
    private void overworldUpdate(float delta, Party playerParty) {
        float horizontalForce = 0;
        float verticalForce = 0;
        float movementSpeed = 1.5f;
        Vector2 velocity = new Vector2();
        if(config.isGodMode()){
            movementSpeed = 4;
        }
        if(inputProcessor.isMoveLeft()) {
            playerParty.getPartyMember1().getAnimatedCharacter().setAction("runningLeft");
            horizontalForce = -movementSpeed;
            verticalForce = 0;
        }
        if(inputProcessor.isMoveRight()){
            playerParty.getPartyMember1().getAnimatedCharacter().setAction("runningRight");
            horizontalForce = movementSpeed;
            verticalForce = 0;
        }
        if(inputProcessor.isMoveUp()) {
            playerParty.getPartyMember1().getAnimatedCharacter().setAction("runningUp");
            horizontalForce = 0;
            verticalForce = movementSpeed;
        }
        if(inputProcessor.isMoveDown()){
            playerParty.getPartyMember1().getAnimatedCharacter().setAction("runningDown");
            verticalForce = -movementSpeed;
            horizontalForce = 0;
        }
        if(inputProcessor.isStoreOpened()){
            game.setScreen(new StoreScreen(game, playerParty));
            this.backgroundMusic.dispose();
        }

//        if(inputProcessor.isAction1()){
//            if(playerCharacter.getAnimatedCharacter().getPlayer().getLinearVelocity().y == 0){
//                playerCharacter.getAnimatedCharacter().getPlayer().applyLinearImpulse(0, 6, 0, 6, false);
//            }
//        }
        velocity.x = horizontalForce * 5;
        velocity.y = verticalForce * 5;

        ((DynamicAnimatedCharacter)playerParty.getPartyMember1().getAnimatedCharacter()).getPlayer().setLinearVelocity(velocity.x,velocity.y);

//        System.out.println("Player real position");
//        System.out.println(playerParty.getPartyMember1().getPosition());
//
//        System.out.println("Player animated position");
//        System.out.println(playerParty.getPartyMember1().getAnimatedCharacter().getPlayer().getPosition());
        if(inputProcessor.isEnterFightMode()){
            playerParty.getPartyMember1().setPosition(((DynamicAnimatedCharacter)playerParty.getPartyMember1().getAnimatedCharacter()).getPlayer().getPosition());
//            GameInformation.getInstance().setPlayerParty(playerParty);
            //This is just for battle testing
            EntitiesBuilderDirector director = new EntitiesBuilderDirector();
            CharacterBuilder characterBuilder = new CharacterBuilder();
//            director.buildFireSkeleton(characterBuilder, GameStatus.getInstance().getWorld(), game, "Enemigo");

            //TODO implementar logica para la seleccion de enemigo segun nivel de personaje, tambien resolver aparicion de enemigo
            //director.buildWaterSkeleton(characterBuilder, playerParty.getActivePartyMember().getAnimatedCharacter().getWorld(), game, "FireSkeletonTest");
            //director.buildEarthSkeleton(characterBuilder, playerParty.getActivePartyMember().getAnimatedCharacter().getWorld(), game, "FireSkeletonTest");
            //director.buildFireSkeleton(characterBuilder, playerParty.getActivePartyMember().getAnimatedCharacter().getWorld(), game, "FireSkeletonTest");
            //director.buildWindSkeleton(characterBuilder, playerParty.getActivePartyMember().getAnimatedCharacter().getWorld(), game, "FireSkeletonTest");

            //director.buildChiefEarth(characterBuilder, playerParty.getActivePartyMember().getAnimatedCharacter().getWorld(), game, "FireSkeletonTest");
            //director.buildChiefFire(characterBuilder, playerParty.getActivePartyMember().getAnimatedCharacter().getWorld(), game, "FireSkeletonTest");
            //director.buildChiefWater(characterBuilder, playerParty.getActivePartyMember().getAnimatedCharacter().getWorld(), game, "FireSkeletonTest");
            //director.buildChiefWind(characterBuilder, playerParty.getActivePartyMember().getAnimatedCharacter().getWorld(), game, "FireSkeletonTest");


            AbstractCharacter enemyCharacter = characterBuilder.getResult();
            Party enemyParty = new Party();
            enemyParty.setPartyMember1(enemyCharacter);
            enemyParty.setPartyMember2(enemyCharacter);
            enemyParty.setPartyMember3(enemyCharacter);

//            game.setScreen(new FightScreen(game, enemyParty));
            System.out.println("FIGHT !");
        }

        if(inputProcessor.isPause()){
            game.setScreen(new PauseScreen(game, playerParty));
            this.backgroundMusic.dispose();
        }
    }

    //Process input for overworld gameplay
    private void battleUpdate(float delta, Party playerParty) {
//        System.out.println("HEY IT'S BATTLE TIME!");
        float horizontalForce = 0;
        float verticalForce = 0;
        float movementSpeed = 1.5f;
        Vector2 velocity = new Vector2();
        if(config.isGodMode()){
            movementSpeed = 4;
        }
        if(inputProcessor.isMoveLeft()) {
            playerParty.getPartyMember1().getAnimatedCharacter().setAction("runningLeft");
            horizontalForce = -movementSpeed;
            verticalForce = 0;
        }
        if(inputProcessor.isMoveRight()){
            playerParty.getPartyMember1().getAnimatedCharacter().setAction("runningRight");
            horizontalForce = movementSpeed;
            verticalForce = 0;
        }
        if(inputProcessor.isMoveUp()) {
            playerParty.getPartyMember1().getAnimatedCharacter().setAction("runningUp");
            horizontalForce = 0;
            verticalForce = movementSpeed;
        }
        if(inputProcessor.isMoveDown()){
            playerParty.getPartyMember1().getAnimatedCharacter().setAction("runningDown");
            verticalForce = -movementSpeed;
            horizontalForce = 0;
        }

        velocity.x = horizontalForce * 5;
        velocity.y = verticalForce * 5;

        ((DynamicAnimatedCharacter)playerParty.getPartyMember1().getAnimatedCharacter()).getPlayer().setLinearVelocity(velocity.x,velocity.y);

        if(inputProcessor.isEnterFightMode()){
//            System.out.println("EXIT!");
//            game.setScreen(new OverworldScreen(game));
        }
    }

    public void dispose(){

    }

}