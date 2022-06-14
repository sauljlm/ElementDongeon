package com.avengers.rpgame.game.io;

import com.avengers.rpgame.logic.entities.Character;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
//TODO Improve this and create different controls for different screens
public class IOManager {
    private MyInputProcessor inputProcessor;


    public IOManager() {
        inputProcessor = new MyInputProcessor();
        Gdx.input.setInputProcessor(inputProcessor);
    }

    //the idea for this method is to process different request of IO processInput differently acording to the screen
    public void processInput(String type, float delta, Character character){
        if(type.equals("overworld")){
            inputUpdate(delta, character);
        }
        if(type.equals("pauseMenu")){

        }
        if(type.equals("mainScreen")){

        }
    }

    private void inputUpdate(float delta, Character character) {
        int horizontalForce = 0;
        int verticalForce = 0;
        Vector2 velocity = new Vector2();
        if(inputProcessor.isMoveLeft()){
            System.out.println("LEFT!");
            horizontalForce = -2;
        }
        if(inputProcessor.isMoveRight()){
            horizontalForce = 2;
            System.out.println("RIGHT!");
        }
        if(inputProcessor.isMoveUp()){
            verticalForce = 2;
            System.out.println("UP!");
        }
        if(inputProcessor.isMoveDown()){
            verticalForce = -2;
            System.out.println("DOWN!");
        }
        if(inputProcessor.isAction1()){
            if(character.getPlayer().getLinearVelocity().y == 0){
                character.getPlayer().applyLinearImpulse(0, 6, 0, 6, false);
            }
            System.out.println("RIGHT!");
            System.out.println("Jump!");
        }
        velocity.x = horizontalForce * 5;
        velocity.y = verticalForce * 5;

        character.getPlayer().setLinearVelocity(velocity.x,velocity.y);

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit(); //TODO Improve this

    }
    public void dispose(){

    }

}

