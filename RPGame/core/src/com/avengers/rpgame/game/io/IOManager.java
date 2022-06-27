package com.avengers.rpgame.game.io;

import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
//TODO Improve this and create different controls for different screens
public class IOManager {
    private MyInputProcessor inputProcessor;
    private GameConfig config;


    public IOManager() {
        inputProcessor = new MyInputProcessor();
        Gdx.input.setInputProcessor(inputProcessor);
        config = GameConfig.getInstance();
    }

    //the idea for this method is to process different request of IO processInput differently acording to the screen
    public void processInput(String type, float delta, AbstractCharacter character){
        if(type.equals("overworld")){
            inputUpdate(delta, character);
        }
        if(type.equals("pauseMenu")){

        }
        if(type.equals("mainScreen")){

        }
    }

    private void inputUpdate(float delta, AbstractCharacter character) {
        float horizontalForce = 0;
        float verticalForce = 0;
        float movementSpeed = 1.5f;
        Vector2 velocity = new Vector2();
        if(config.isGodMode()){
            movementSpeed = 4;
        }
        if(inputProcessor.isMoveLeft()) {
            character.getAnimatedCharacter().setAction("runningLeft");
            System.out.println("LEFT!");
            horizontalForce = -movementSpeed;
            verticalForce = 0;
        }
        if(inputProcessor.isMoveRight()){
            character.getAnimatedCharacter().setAction("runningRight");
            horizontalForce = movementSpeed;
            verticalForce = 0;
            System.out.println("RIGHT!");
        }
        if(inputProcessor.isMoveUp()) {
            character.getAnimatedCharacter().setAction("runningUp");
            horizontalForce = 0;
            verticalForce = movementSpeed;
            System.out.println("UP!");
        }
        if(inputProcessor.isMoveDown()){
            character.getAnimatedCharacter().setAction("runningDown");
            verticalForce = -movementSpeed;
            horizontalForce = 0;
            System.out.println("DOWN!");
        }



        if(inputProcessor.isAction1()){
            if(character.getAnimatedCharacter().getPlayer().getLinearVelocity().y == 0){
                character.getAnimatedCharacter().getPlayer().applyLinearImpulse(0, 6, 0, 6, false);
            }
            System.out.println("RIGHT!");
            System.out.println("Jump!");
        }
        velocity.x = horizontalForce * 5;
        velocity.y = verticalForce * 5;

        character.getAnimatedCharacter().getPlayer().setLinearVelocity(velocity.x,velocity.y);

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit(); //TODO Improve this

    }
    public void dispose(){

    }

}

