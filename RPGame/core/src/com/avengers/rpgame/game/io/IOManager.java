package com.avengers.rpgame.game.io;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.screens.FightScreen;
import com.avengers.rpgame.graphics.screens.StoreScreen;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.Queue;

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

    //the idea for this method is to process different request of IO processInput differently acording to the screen
    public void processInput(String type, float delta, AbstractCharacter playerCharacter, AbstractCharacter ally1Character, AbstractCharacter ally2Character){
        if(type.equals("overworld")){
            overworldUpdate(delta, playerCharacter, ally1Character, ally2Character);

        }
        if(type.equals("pauseMenu")){

        }
        if(type.equals("mainScreen")){

        }
    }

    private void overworldUpdate(float delta, AbstractCharacter playerCharacter, AbstractCharacter ally1Character, AbstractCharacter ally2Character) {
        float horizontalForce = 0;
        float verticalForce = 0;
        float movementSpeed = 1.5f;
        Vector2 velocity = new Vector2();
        if(config.isGodMode()){
            movementSpeed = 4;
        }
        if(inputProcessor.isMoveLeft()) {
            playerCharacter.getAnimatedCharacter().setAction("runningLeft");
            horizontalForce = -movementSpeed;
            verticalForce = 0;
        }
        if(inputProcessor.isMoveRight()){
            playerCharacter.getAnimatedCharacter().setAction("runningRight");
            horizontalForce = movementSpeed;
            verticalForce = 0;
        }
        if(inputProcessor.isMoveUp()) {
            playerCharacter.getAnimatedCharacter().setAction("runningUp");
            horizontalForce = 0;
            verticalForce = movementSpeed;
        }
        if(inputProcessor.isMoveDown()){
            playerCharacter.getAnimatedCharacter().setAction("runningDown");
            verticalForce = -movementSpeed;
            horizontalForce = 0;
        }
        if(inputProcessor.isStoreOpened()){
            game.setScreen(new StoreScreen(game));
            this.backgroundMusic.dispose();
        }
        if(inputProcessor.isAction1()){
            if(playerCharacter.getAnimatedCharacter().getPlayer().getLinearVelocity().y == 0){
                playerCharacter.getAnimatedCharacter().getPlayer().applyLinearImpulse(0, 6, 0, 6, false);
            }
        }
        velocity.x = horizontalForce * 5;
        velocity.y = verticalForce * 5;

        playerCharacter.getAnimatedCharacter().getPlayer().setLinearVelocity(velocity.x,velocity.y);

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit(); //TODO Improve this

        if(inputProcessor.isEnterFightMode()){
            game.setScreen(new FightScreen(game));
            System.out.println("FIGHT !");
        }
    }

    public void dispose(){

    }

}

